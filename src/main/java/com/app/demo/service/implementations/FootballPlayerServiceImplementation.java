package com.app.demo.service.implementations;

import com.app.demo.exceptions.EntityNotFoundException;
import com.app.demo.exceptions.FootballPlayerNotLegalException;
import com.app.demo.mapper.DtoMapper;
import com.app.demo.model.dto.FootballPlayerHistoryInsertOrUpdateDto;
import com.app.demo.model.dto.FootballPlayerInsertOrUpdateDto;
import com.app.demo.model.dto.FootballPlayerResponseDto;
import com.app.demo.model.entity.FootballPlayer;
import com.app.demo.repository.CountryDictionaryRepository;
import com.app.demo.repository.FootballPlayerRepository;
import com.app.demo.repository.FootballTeamRepository;
import com.app.demo.service.interfaces.FootballPlayerFootballTeamHistoryService;
import com.app.demo.service.interfaces.FootballPlayerService;
import com.app.demo.util.CalculationUtils;
import com.app.demo.util.MyBeansUtils;
import com.app.demo.util.StaticsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FootballPlayerServiceImplementation implements FootballPlayerService {

    private final FootballPlayerRepository footballPlayerRepository;
    private final CountryDictionaryRepository countryDictionaryRepository;
    private final FootballTeamRepository footballTeamRepository;
    private final FootballPlayerFootballTeamHistoryService footballPlayerFootballTeamHistoryService;

    private final DtoMapper dtoMapper;
    private final MyBeansUtils myBeansUtils;

    @Override
    public void saveOrUpdate(FootballPlayerInsertOrUpdateDto footballPlayerInsertOrUpdateDto) {
        long legalAge = 18L;
        if (CalculationUtils.calculateYearsBetween(footballPlayerInsertOrUpdateDto.getBirthDate(), LocalDate.now()) < legalAge)
            throw new FootballPlayerNotLegalException("FootballPlayer is below age " + legalAge + " and cannot play ");

        FootballPlayer footballPlayer = dtoMapper.asFootballPlayer(footballPlayerInsertOrUpdateDto);

        String countryName = footballPlayerInsertOrUpdateDto.getCountryName();
        footballPlayer.setCountryDictionary(countryDictionaryRepository.findByNameAndIsActive(countryName, Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Country with name: " + countryName + StaticsUtils.DOES_NOT_EXIST)));

        String uFTI = footballPlayerInsertOrUpdateDto.getTeamUFTI();
        footballPlayer.setFootballTeam(footballTeamRepository.findByUniqueFootballTeamIdentifierAndIsActive(uFTI, Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Football team with uFTI : " + uFTI + StaticsUtils.DOES_NOT_EXIST)));

        FootballPlayer footballPlayerFromDb = footballPlayerRepository
                .findByUniqueFootballPlayerIdentifierAndIsActive(footballPlayer.getUniqueFootballPlayerIdentifier(), Boolean.TRUE)
                .orElse(new FootballPlayer());
        myBeansUtils.copyNonNullProperties(footballPlayerFromDb, footballPlayer);
        footballPlayerRepository.save(footballPlayerFromDb);

        List<FootballPlayerHistoryInsertOrUpdateDto> footballPlayerHistoryInsertOrUpdateDtoList = footballPlayerInsertOrUpdateDto.getFootballPlayerHistoryInsertOrUpdateDtoList();
        footballPlayerHistoryInsertOrUpdateDtoList.forEach(footballPlayerFootballTeamHistoryService::saveOrUpdate);

        log.info("{} player has been successfully saved to database", footballPlayerFromDb.getName());
    }

    @Override
    public void deleteByUniqueFootballPlayerIdentifier(String uFPI) {

        if (footballPlayerRepository.existsByUniqueFootballPlayerIdentifierAndIsActive(uFPI, Boolean.TRUE)) {

            footballPlayerRepository.deleteByUniqueFootballPlayerIdentifier(uFPI);
            log.info("Player with uFPI:{} has been successfully deleted from database", uFPI);
        } else
            throw new EntityNotFoundException("Football player with uFPI : " + uFPI + StaticsUtils.DOES_NOT_EXIST);

    }

    @Override
    public List<FootballPlayerResponseDto> getAll(Pageable pageable) {

        return dtoMapper.asFootballPlayerResponseDto(footballPlayerRepository.findAllByIsActive(Boolean.TRUE, pageable));
    }
}
