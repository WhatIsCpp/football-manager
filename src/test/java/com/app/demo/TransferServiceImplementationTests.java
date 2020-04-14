package com.app.demo;

import com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto;
import com.app.demo.dto.FootballPlayerHistoryResponseDto;
import com.app.demo.dto.FootballPlayerTransferRequestDto;
import com.app.demo.exceptions.EntityNotFoundException;
import com.app.demo.mapper.DtoMapper;
import com.app.demo.model.entity.FootballPlayer;
import com.app.demo.repository.FootballPlayerFootballTeamHistoryRepository;
import com.app.demo.repository.FootballPlayerRepository;
import com.app.demo.repository.FootballTeamRepository;
import com.app.demo.service.implementations.TransferServiceImplementation;
import com.app.demo.service.interfaces.FootballPlayerFootballTeamHistoryService;
import com.app.demo.service.interfaces.TransferService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class TransferServiceImplementationTests {

    private static final String TRANSFER_FEE = "transferFee";
    private static final String TEAM_COMMISSION = "teamCommission";
    private static final String CONTRACT_PRICE = "contractPrice";

    private static final String TEST_NAME = "testName";
    private static final String TEST_NAME_1 = "testName1";
    private static final String TEST_SURNAME = "testSurnname";
    private static final String TEST_UFPI = "1";
    private static final String TEST_UFTI = "1";

    private static final Long TEST_TEAM_COMMISSION_PERCENTAGE = 10l;
    private static final BigDecimal TEST_TRANSFER_FEE = BigDecimal.valueOf(100L);
    private static final BigDecimal TEST_TEAM_COMMISSION = BigDecimal.valueOf(10L);
    private static final BigDecimal TEST_CONTRACT_PRICE = BigDecimal.valueOf(110L);

    private static final LocalDate TEST_JOIN_DATE = LocalDate.of(2010, 12, 10);
    private static final LocalDate TEST_EXIT_DATE = LocalDate.of(2020, 12, 10);
    private static final LocalDate TEST_BEETWEEN_DATE = LocalDate.of(2011, 12, 10);
    private static final LocalDate TEST_BIRTH_DATE = LocalDate.of(1990, 12, 10);

    private static final Boolean TEST_BOOLEAN_TRUE = Boolean.TRUE;
    private static final Boolean TEST_BOOLEAN_FALSE = Boolean.FALSE;

    private static final Pageable TEST_UNPAGED_PAGEABLE = Pageable.unpaged();

    @MockBean
    private FootballPlayerFootballTeamHistoryService footballPlayerFootballTeamHistoryService;

    @MockBean
    private FootballTeamRepository footballTeamRepository;

    @MockBean
    private FootballPlayerRepository footballPlayerRepository;

    @MockBean
    private FootballPlayerFootballTeamHistoryRepository footballPlayerFootballTeamHistoryRepository;

    @MockBean
    private DtoMapper dtoMapper;

    private TransferService transferService;

    @Before
    public void setUp() {
        transferService = new TransferServiceImplementation
                (footballPlayerFootballTeamHistoryService, footballTeamRepository, footballPlayerRepository, footballPlayerFootballTeamHistoryRepository, dtoMapper);

        Mockito.when(footballPlayerRepository.existsByUniqueFootballPlayerIdentifierAndIsActive(TEST_UFPI, TEST_BOOLEAN_TRUE))
                .thenReturn(TEST_BOOLEAN_FALSE);

        FootballPlayerTransferRequestDto footballPlayerTransferRequestDto = new FootballPlayerTransferRequestDto();
        footballPlayerTransferRequestDto.setUFPI(TEST_UFPI);

        Mockito.when(footballPlayerFootballTeamHistoryRepository.existsByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate(TEST_UFPI, null))
                .thenReturn(TEST_BOOLEAN_FALSE);

        FootballPlayerHistoryInsertOrUpdateDto footballPlayerHistoryInsertOrUpdateDto = new FootballPlayerHistoryInsertOrUpdateDto();

        Mockito.when(dtoMapper.asFootballPlayerHistoryInsertOrUpdateDto(footballPlayerTransferRequestDto))
                .thenReturn(footballPlayerHistoryInsertOrUpdateDto);

        Mockito.when(footballPlayerRepository.findByUniqueFootballPlayerIdentifierAndIsActive(TEST_UFPI, TEST_BOOLEAN_TRUE))
                .thenReturn(Optional.empty());

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenGetTransferFee_expectEntityNotFoundException() {
        transferService.getTransferFee(TEST_UFTI, TEST_UFPI, TEST_JOIN_DATE, TEST_TEAM_COMMISSION_PERCENTAGE);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenTransferPlayer_expectEntityNotFoundException() {
        FootballPlayerTransferRequestDto footballPlayerTransferRequestDto = new FootballPlayerTransferRequestDto();
        footballPlayerTransferRequestDto.setUFPI(TEST_UFPI);
        transferService.transferPlayer(footballPlayerTransferRequestDto);
    }


}
