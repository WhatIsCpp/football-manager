package com.app.demo;

import com.app.demo.exceptions.EntityNotFoundException;
import com.app.demo.exceptions.FootballPlayerNotLegalException;
import com.app.demo.mapper.DtoMapper;
import com.app.demo.model.dto.FootballPlayerInsertOrUpdateDto;
import com.app.demo.model.dto.FootballPlayerResponseDto;
import com.app.demo.model.entity.FootballPlayer;
import com.app.demo.repository.CountryDictionaryRepository;
import com.app.demo.repository.FootballPlayerRepository;
import com.app.demo.repository.FootballTeamRepository;
import com.app.demo.service.implementations.FootballPlayerServiceImplementation;
import com.app.demo.service.interfaces.FootballPlayerFootballTeamHistoryService;
import com.app.demo.service.interfaces.FootballPlayerService;
import com.app.demo.util.MyBeansUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class FootballPlayerServiceImplementationTests {

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
    private FootballPlayerRepository footballPlayerRepository;

    @MockBean
    private CountryDictionaryRepository countryDictionaryRepository;

    @MockBean
    private FootballTeamRepository footballTeamRepository;

    @MockBean
    private DtoMapper dtoMapper;

    @MockBean
    private MyBeansUtils myBeansUtils;

    @MockBean
    private FootballPlayerFootballTeamHistoryService footballPlayerFootballTeamHistoryService;

    private FootballPlayerService footballPlayerService;

    @Before
    public void setUp() {
        footballPlayerService = new FootballPlayerServiceImplementation(footballPlayerRepository, countryDictionaryRepository, footballTeamRepository, footballPlayerFootballTeamHistoryService, dtoMapper, myBeansUtils);

        FootballPlayer footballPlayer = new FootballPlayer();

        FootballPlayerInsertOrUpdateDto footballPlayerInsertOrUpdateDto = new FootballPlayerInsertOrUpdateDto();

        Mockito.when(dtoMapper.asFootballPlayer(footballPlayerInsertOrUpdateDto))
                .thenReturn(footballPlayer);

        Mockito.when(countryDictionaryRepository.findByNameAndIsActive(TEST_NAME, TEST_BOOLEAN_TRUE))
                .thenReturn(Optional.empty());

        Mockito.when(footballPlayerRepository.existsByUniqueFootballPlayerIdentifierAndIsActive(TEST_UFPI, TEST_BOOLEAN_TRUE))
                .thenReturn(TEST_BOOLEAN_FALSE);

        FootballPlayer footballPlayer1 = new FootballPlayer();
        footballPlayer1.setName(TEST_NAME);
        footballPlayer1.setSurname(TEST_SURNAME);

        List<FootballPlayer> footballPlayerList = Collections.singletonList(footballPlayer1);

        Mockito.when(footballPlayerRepository.findAllByIsActive(TEST_BOOLEAN_TRUE, TEST_UNPAGED_PAGEABLE))
                .thenReturn(footballPlayerList);

        FootballPlayerResponseDto footballPlayerResponseDto = new FootballPlayerResponseDto();

        List<FootballPlayerResponseDto> footballPlayerResponseDtoList = Collections.singletonList(footballPlayerResponseDto);

        Mockito.when(dtoMapper.asFootballPlayerResponseDto(footballPlayerList))
                .thenReturn(footballPlayerResponseDtoList);

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenSaveOrUpdate_expectEntityNotFoundException(){
        FootballPlayerInsertOrUpdateDto footballPlayerInsertOrUpdateDto = new FootballPlayerInsertOrUpdateDto();
        footballPlayerInsertOrUpdateDto.setBirthDate(LocalDate.of(2000, 1, 1));
        footballPlayerService.saveOrUpdate(footballPlayerInsertOrUpdateDto);
    }

    @Test(expected = FootballPlayerNotLegalException.class)
    public void whenSaveOrUpdate_expectFootballPlayerNotLegalException(){
        FootballPlayerInsertOrUpdateDto footballPlayerInsertOrUpdateDto = new FootballPlayerInsertOrUpdateDto();
        footballPlayerInsertOrUpdateDto.setBirthDate(LocalDate.of(2010, 1, 1));
        footballPlayerService.saveOrUpdate(footballPlayerInsertOrUpdateDto);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenDeleteByUniqueFootballPlayerIdentifier_expectEntityNotFoundException(){
        footballPlayerService.deleteByUniqueFootballPlayerIdentifier(TEST_UFPI);
    }

    @Test
    public void whenGetAll_thenReturnFootballPlayerResponseDtoList(){
        List<FootballPlayerResponseDto> footballPlayerResponseDtoList = footballPlayerService.getAll(TEST_UNPAGED_PAGEABLE);

        Assert.assertFalse(footballPlayerResponseDtoList.isEmpty());
    }


}
