package com.app.demo.service.implementations;

import com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto;
import com.app.demo.dto.FootballPlayerHistoryResponseDto;
import com.app.demo.dto.FootballPlayerResponseDto;
import com.app.demo.dto.FootballPlayerTransferRequestDto;
import com.app.demo.dto.FootballPlayerTransferResponseDto;
import com.app.demo.mapper.DtoMapper;
import com.app.demo.model.entity.FootballPlayer;
import com.app.demo.model.entity.FootballPlayerFootballTeamHistory;
import com.app.demo.model.entity.FootballTeam;
import com.app.demo.repository.FootballPlayerFootballTeamHistoryRepository;
import com.app.demo.repository.FootballPlayerRepository;
import com.app.demo.repository.FootballTeamRepository;
import com.app.demo.service.interfaces.FootballPlayerFootballTeamHistoryService;
import com.app.demo.service.interfaces.TransferService;
import com.app.demo.util.CalculationUtils;
import com.app.demo.util.StaticsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.demo.exceptions.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImplementation implements TransferService {

    private static final String TRANSFER_FEE = "transferFee";
    private static final String TEAM_COMMISSION = "teamCommission";
    private static final String CONTRACT_PRICE = "contractPrice";

    private final FootballPlayerFootballTeamHistoryService footballPlayerFootballTeamHistoryService;

    private final FootballTeamRepository footballTeamRepository;
    private final FootballPlayerRepository footballPlayerRepository;
    private final FootballPlayerFootballTeamHistoryRepository footballPlayerFootballTeamHistoryRepository;

    private final DtoMapper dtoMapper;


    @Override
    public FootballPlayerTransferResponseDto getTransferFee(String uFTI, String uFPI, LocalDate startDate, Long teamCommissionPercentage) {
        if (!footballPlayerRepository.existsByUniqueFootballPlayerIdentifierAndIsActive(uFPI, Boolean.TRUE))
            throw new EntityNotFoundException("Football Player with uFPI: " + uFPI + StaticsUtils.DOES_NOT_EXIST);
        if (!footballTeamRepository.existsByUniqueFootballTeamIdentifierAndIsActive(uFTI, Boolean.TRUE))
            throw new EntityNotFoundException("Football team with uFTI : " + uFTI + StaticsUtils.DOES_NOT_EXIST);

        //We already know that this player exist that's why we are using get without checking it's value
        FootballPlayer footballPlayer = footballPlayerRepository.findByUniqueFootballPlayerIdentifierAndIsActive(uFPI, Boolean.TRUE).get();

        HashMap<String, BigDecimal> stringBigDecimalHashMap = calculateTransferAmounts(uFPI, teamCommissionPercentage, footballPlayer.getBirthDate());

        FootballPlayerTransferResponseDto footballPlayerTransferResponseDto = dtoMapper.asFootballPlayerTransferResponseDto(footballPlayer);
        footballPlayerTransferResponseDto.setUFTI(uFTI);
        footballPlayerTransferResponseDto.setTransferFee(stringBigDecimalHashMap.get(TRANSFER_FEE));
        footballPlayerTransferResponseDto.setTeamCommission(stringBigDecimalHashMap.get(TEAM_COMMISSION));
        footballPlayerTransferResponseDto.setContractPrice(stringBigDecimalHashMap.get(CONTRACT_PRICE));
        footballPlayerTransferResponseDto.setStartDate(startDate);

        return footballPlayerTransferResponseDto;
    }

    @Transactional
    @Override
    public FootballPlayerResponseDto transferPlayer(FootballPlayerTransferRequestDto footballPlayerTransferRequestDto) {

        String uFPI = footballPlayerTransferRequestDto.getUFPI();
        if (footballPlayerFootballTeamHistoryRepository.existsByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate(uFPI, null)) {

            FootballPlayerFootballTeamHistory lastFootballPlayerFootballTeamHistory = footballPlayerFootballTeamHistoryRepository
                    .findByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate(uFPI, null).get();

            lastFootballPlayerFootballTeamHistory.setExitDate(footballPlayerTransferRequestDto.getStartDate());
            footballPlayerFootballTeamHistoryRepository.save(lastFootballPlayerFootballTeamHistory);
            log.info("Player history with id:{} has been successfully updated", lastFootballPlayerFootballTeamHistory.getId());
        }
        FootballPlayerHistoryInsertOrUpdateDto footballPlayerHistoryInsertOrUpdateDto = dtoMapper.asFootballPlayerHistoryInsertOrUpdateDto(footballPlayerTransferRequestDto);
        footballPlayerHistoryInsertOrUpdateDto.setIsActive(Boolean.TRUE);
        footballPlayerFootballTeamHistoryService.saveOrUpdate(footballPlayerHistoryInsertOrUpdateDto);

        FootballPlayer footballPlayer = footballPlayerRepository.findByUniqueFootballPlayerIdentifierAndIsActive(uFPI, Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Football Player with uFPI: " + uFPI + StaticsUtils.DOES_NOT_EXIST));

        String uFTI = footballPlayerTransferRequestDto.getUFTI();
        FootballTeam footballTeam = footballTeamRepository.findByUniqueFootballTeamIdentifierAndIsActive(uFTI, Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Football team with uFTI : " + uFTI + StaticsUtils.DOES_NOT_EXIST));

        footballPlayer.setFootballTeam(footballTeam);
        footballPlayer = footballPlayerRepository.save(footballPlayer);

        return dtoMapper.asFootballPlayerResponseDto(footballPlayer);
    }

    private long calculateMonthsBetweenJoinDateAndExitDate(FootballPlayerHistoryResponseDto footballPlayerHistoryResponseDto) {

        LocalDate dateFrom = footballPlayerHistoryResponseDto.getJoinDate();
        //For getting last contract which is ongoing so it doesnt have an endDate
        LocalDate dateTo = footballPlayerHistoryResponseDto.getExitDate() == null ? LocalDate.now() : footballPlayerHistoryResponseDto.getExitDate();

        return ChronoUnit.MONTHS.between(dateFrom, dateTo);
    }

    private HashMap<String, BigDecimal> calculateTransferAmounts(String uFPI, Long teamCommissionPercentage, LocalDate birthDate){

        List<FootballPlayerHistoryResponseDto> footballPlayerHistoryResponseDtoList = footballPlayerFootballTeamHistoryService.getAllByUFPI(uFPI);

        long experienceInMonths = footballPlayerHistoryResponseDtoList.stream()
                .mapToLong(this::calculateMonthsBetweenJoinDateAndExitDate)
                .sum();

        BigDecimal footballPlayerAge = BigDecimal.valueOf(CalculationUtils.calculateYearsBetween(birthDate, LocalDate.now()));
        BigDecimal bigDecimalExperienceInMonths = BigDecimal.valueOf(experienceInMonths);
        BigDecimal experienceMultiplier = BigDecimal.valueOf(100.000);
        BigDecimal transferFee = bigDecimalExperienceInMonths.multiply(experienceMultiplier).divide(footballPlayerAge, RoundingMode.HALF_EVEN);
        BigDecimal commissionPercentage = BigDecimal.valueOf(teamCommissionPercentage).divide(experienceMultiplier);
        BigDecimal teamCommission = transferFee.multiply(commissionPercentage);
        BigDecimal contractPrice = transferFee.add(teamCommission);

        HashMap<String, BigDecimal> stringBigDecimalHashMap = new HashMap<>();
        stringBigDecimalHashMap.put(TRANSFER_FEE, transferFee);
        stringBigDecimalHashMap.put(TEAM_COMMISSION, teamCommission);
        stringBigDecimalHashMap.put(CONTRACT_PRICE, contractPrice);

        return stringBigDecimalHashMap;
    }

}
