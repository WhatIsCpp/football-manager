package com.app.demo.service.implementations;

import com.app.demo.exceptions.EntityNotFoundException;
import com.app.demo.mapper.DtoMapper;
import com.app.demo.model.dto.FootballPlayerHistoryInsertOrUpdateDto;
import com.app.demo.model.dto.FootballPlayerHistoryResponseDto;
import com.app.demo.model.entity.FootballPlayerFootballTeamHistory;
import com.app.demo.repository.FootballPlayerFootballTeamHistoryRepository;
import com.app.demo.repository.FootballPlayerRepository;
import com.app.demo.repository.FootballTeamRepository;
import com.app.demo.service.interfaces.FootballPlayerFootballTeamHistoryService;
import com.app.demo.util.MyBeansUtils;
import com.app.demo.util.StaticsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FootballPlayerFootballTeamHistoryServiceImplementation implements FootballPlayerFootballTeamHistoryService {

    private final FootballPlayerFootballTeamHistoryRepository footballPlayerFootballTeamHistoryRepository;
    private final FootballPlayerRepository footballPlayerRepository;
    private final FootballTeamRepository footballTeamRepository;

    private final MyBeansUtils myBeansUtils;
    private final DtoMapper dtoMapper;

    @Override
    public void saveOrUpdate(FootballPlayerHistoryInsertOrUpdateDto footballPlayerHistoryInsertOrUpdateDto) {

        FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory = dtoMapper.asFootballPlayerFootballTeamHistory(footballPlayerHistoryInsertOrUpdateDto);

        String uFPI = footballPlayerHistoryInsertOrUpdateDto.getUFPI();
        footballPlayerFootballTeamHistory.setFootballPlayer(footballPlayerRepository.findByUniqueFootballPlayerIdentifierAndIsActive(uFPI, Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Football Player with uFPI: " + uFPI + StaticsUtils.DOES_NOT_EXIST)));

        String uFTI = footballPlayerHistoryInsertOrUpdateDto.getUFTI();
        footballPlayerFootballTeamHistory.setFootballTeam(footballTeamRepository.findByUniqueFootballTeamIdentifierAndIsActive(uFTI, Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Football Team with uFTI: " + uFTI + StaticsUtils.DOES_NOT_EXIST)));

        Long id = footballPlayerFootballTeamHistory.getId();
        if (null == id) {

            footballPlayerFootballTeamHistoryRepository.save(footballPlayerFootballTeamHistory);
            log.info("Player history has been successfully saved to database");
        } else {

            FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistoryDb = footballPlayerFootballTeamHistoryRepository
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("FootballPlayerFootballTeamHistory with id: " + id + StaticsUtils.DOES_NOT_EXIST));
            myBeansUtils.copyNonNullProperties(footballPlayerFootballTeamHistoryDb, footballPlayerFootballTeamHistory);
            footballPlayerFootballTeamHistoryRepository.save(footballPlayerFootballTeamHistoryDb);
            log.info("Player history with id:{} has been successfully updated", id);
        }
    }

    @Override
    public List<FootballPlayerHistoryResponseDto> getAllByUFPI(String uFPI) {

        return dtoMapper.asFootballPlayerHistoryResponseDto(footballPlayerFootballTeamHistoryRepository.findAllByFootballPlayer_UniqueFootballPlayerIdentifier(uFPI));
    }

    @Override
    public List<FootballPlayerHistoryResponseDto> getAllByTeamAndJoinDateAndExitDate(String uFTI, LocalDate joinDate) {

        return dtoMapper.asFootballPlayerHistoryResponseDto(footballPlayerFootballTeamHistoryRepository
                .findAllByDate(uFTI, joinDate));
    }

}
