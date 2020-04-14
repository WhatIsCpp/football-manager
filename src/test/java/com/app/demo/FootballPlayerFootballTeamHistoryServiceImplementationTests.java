package com.app.demo;

import com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto;
import com.app.demo.dto.FootballPlayerHistoryResponseDto;
import com.app.demo.exceptions.EntityNotFoundException;
import com.app.demo.mapper.DtoMapper;
import com.app.demo.model.entity.FootballPlayerFootballTeamHistory;
import com.app.demo.repository.FootballPlayerFootballTeamHistoryRepository;
import com.app.demo.repository.FootballPlayerRepository;
import com.app.demo.repository.FootballTeamRepository;
import com.app.demo.service.implementations.FootballPlayerFootballTeamHistoryServiceImplementation;
import com.app.demo.service.interfaces.FootballPlayerFootballTeamHistoryService;
import com.app.demo.util.MyBeansUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class FootballPlayerFootballTeamHistoryServiceImplementationTests {

    private static final String TEST_NAME = "testName";
    private static final String TEST_NAME_1 = "testName1";
    private static final String TEST_SURNAME = "testSurnname";
    private static final String TEST_UFPI = "1";
    private static final String TEST_UFTI = "1";

    private static final LocalDate TEST_JOIN_DATE = LocalDate.of(2010, 12, 10);
    private static final LocalDate TEST_EXIT_DATE = LocalDate.of(2020, 12, 10);
    private static final LocalDate TEST_BEETWEEN_DATE = LocalDate.of(2011, 12, 10);

    private static final Boolean TEST_BOOLEAN_TRUE = Boolean.TRUE;
    private static final Boolean TEST_BOOLEAN_FALSE = Boolean.FALSE;

    private static final Pageable TEST_UNPAGED_PAGEABLE = Pageable.unpaged();

    @MockBean
    private FootballPlayerFootballTeamHistoryRepository footballPlayerFootballTeamHistoryRepository;

    @MockBean
    private FootballPlayerRepository footballPlayerRepository;

    @MockBean
    private FootballTeamRepository footballTeamRepository;

    @MockBean
    private MyBeansUtils myBeansUtils;

    @MockBean
    private DtoMapper dtoMapper;

    private FootballPlayerFootballTeamHistoryService footballPlayerFootballTeamHistoryService;

    @Before
    public void setUp() {
        footballPlayerFootballTeamHistoryService = new FootballPlayerFootballTeamHistoryServiceImplementation
                (footballPlayerFootballTeamHistoryRepository, footballPlayerRepository, footballTeamRepository, myBeansUtils, dtoMapper);

        FootballPlayerHistoryInsertOrUpdateDto footballPlayerHistoryInsertOrUpdateDto = new FootballPlayerHistoryInsertOrUpdateDto();
        footballPlayerHistoryInsertOrUpdateDto.setUFPI(TEST_UFPI);

        FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory = new FootballPlayerFootballTeamHistory();

        Mockito.when(dtoMapper.asFootballPlayerFootballTeamHistory(footballPlayerHistoryInsertOrUpdateDto))
                .thenReturn(footballPlayerFootballTeamHistory);

        Mockito.when(footballPlayerRepository.findByUniqueFootballPlayerIdentifierAndIsActive(TEST_UFPI, TEST_BOOLEAN_TRUE))
                .thenReturn(Optional.empty());

        List<FootballPlayerFootballTeamHistory> footballPlayerFootballTeamHistoryList = Collections.singletonList(footballPlayerFootballTeamHistory);

        Mockito.when(footballPlayerFootballTeamHistoryRepository.findAllByFootballPlayer_UniqueFootballPlayerIdentifier(TEST_UFPI))
                .thenReturn(footballPlayerFootballTeamHistoryList);

        FootballPlayerHistoryResponseDto footballPlayerHistoryResponseDto = new FootballPlayerHistoryResponseDto();
        List<FootballPlayerHistoryResponseDto> footballPlayerHistoryResponseDtoList = Collections.singletonList(footballPlayerHistoryResponseDto);

        Mockito.when(dtoMapper.asFootballPlayerHistoryResponseDto(footballPlayerFootballTeamHistoryList))
                .thenReturn(footballPlayerHistoryResponseDtoList);

        Mockito.when(footballPlayerFootballTeamHistoryRepository
                .findAllByFootballTeam_UniqueFootballTeamIdentifierAndJoinDateIsBeforeAndExitDateIsAfter(TEST_UFTI, TEST_JOIN_DATE, TEST_EXIT_DATE))
                .thenReturn(footballPlayerFootballTeamHistoryList);

        Mockito.when(dtoMapper.asFootballPlayerHistoryResponseDto(footballPlayerFootballTeamHistoryList))
                .thenReturn(footballPlayerHistoryResponseDtoList);

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenSaveOrUpdate_ExpectEntityNotFoundException() {
        FootballPlayerHistoryInsertOrUpdateDto footballPlayerHistoryInsertOrUpdateDto = new FootballPlayerHistoryInsertOrUpdateDto();
        footballPlayerHistoryInsertOrUpdateDto.setUFPI(TEST_UFPI);
        footballPlayerFootballTeamHistoryService.saveOrUpdate(footballPlayerHistoryInsertOrUpdateDto);
    }

    @Test
    public void whenGetAllByUFPI_thenReturnFootballPlayerHistoryResponseDtoList() {
        List<FootballPlayerHistoryResponseDto> footballPlayerHistoryResponseDtoList = footballPlayerFootballTeamHistoryService.getAllByUFPI(TEST_UFPI);

        Assert.assertFalse(footballPlayerHistoryResponseDtoList.isEmpty());
    }

    @Test
    public void whenGetAllByTeamAndJoinDateAndExitDate_thenReturnFootballPlayerHistoryResponseDtoList() {
        List<FootballPlayerHistoryResponseDto> footballPlayerHistoryResponseDtoList = footballPlayerFootballTeamHistoryService.getAllByTeamAndJoinDateAndExitDate(TEST_UFTI, TEST_JOIN_DATE, TEST_EXIT_DATE);

        Assert.assertFalse(footballPlayerHistoryResponseDtoList.isEmpty());
    }

}
