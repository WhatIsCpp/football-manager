package com.app.demo;

import com.app.demo.exceptions.EntityNotFoundException;
import com.app.demo.mapper.DtoMapper;
import com.app.demo.model.dto.FootballTeamInsertOrUpdateDto;
import com.app.demo.model.dto.FootballTeamResponseDto;
import com.app.demo.model.entity.FootballTeam;
import com.app.demo.repository.CountryDictionaryRepository;
import com.app.demo.repository.CurrencyDictionaryRepository;
import com.app.demo.repository.FootballTeamRepository;
import com.app.demo.service.implementations.FootballTeamServiceImplementation;
import com.app.demo.service.interfaces.FootballTeamService;
import com.app.demo.util.MyBeansUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class FootballTeamServiceImplementationTests {

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
    private FootballTeamRepository footballTeamRepository;

    @MockBean
    private CountryDictionaryRepository countryDictionaryRepository;

    @MockBean
    private CurrencyDictionaryRepository currencyDictionaryRepository;

    @MockBean
    private DtoMapper dtoMapper;

    @MockBean
    private MyBeansUtils myBeansUtils;

    private FootballTeamService footballTeamService;

    @Before
    public void setUp(){
        footballTeamService = new FootballTeamServiceImplementation(footballTeamRepository, countryDictionaryRepository, currencyDictionaryRepository, dtoMapper, myBeansUtils);

        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setName(TEST_NAME);

        FootballTeamInsertOrUpdateDto footballTeamInsertOrUpdateDto = new FootballTeamInsertOrUpdateDto();
        footballTeamInsertOrUpdateDto.setCountryName(TEST_NAME);

        Mockito.when(dtoMapper.asFootballTeam(footballTeamInsertOrUpdateDto))
                .thenReturn(footballTeam);

        Mockito.when(countryDictionaryRepository.findByNameAndIsActive(TEST_NAME, TEST_BOOLEAN_TRUE))
                .thenReturn(Optional.empty());

        Mockito.when(footballTeamRepository.existsByUniqueFootballTeamIdentifierAndIsActive(TEST_UFTI, TEST_BOOLEAN_TRUE))
                .thenReturn(TEST_BOOLEAN_FALSE);

        List<FootballTeam> footballTeamList = Collections.singletonList(footballTeam);
        Page<FootballTeam> footballTeamPage = new PageImpl<>(footballTeamList);

        Mockito.when(footballTeamRepository.findAll(TEST_UNPAGED_PAGEABLE))
                .thenReturn(footballTeamPage);

        FootballTeamResponseDto footballTeamResponseDto = new FootballTeamResponseDto();

        List<FootballTeamResponseDto> footballTeamResponseDtoList = Collections.singletonList(footballTeamResponseDto);
        Mockito.when(dtoMapper.asFootballTeamResponseDto(footballTeamList))
                .thenReturn(footballTeamResponseDtoList);

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenSaveOrUpdate_expectEntityNotFoundException(){
        FootballTeamInsertOrUpdateDto footballTeamInsertOrUpdateDto = new FootballTeamInsertOrUpdateDto();
        footballTeamService.saveOrUpdate(footballTeamInsertOrUpdateDto);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenDeleteByUniqueFootballTeamIdentifier_expectEntityNotFoundException(){
        footballTeamService.deleteByUniqueFootballTeamIdentifier(TEST_UFTI);
    }

    @Test
    public void whenGetAll_thenReturnFootballTeamResponseDtoList(){
        List<FootballTeamResponseDto> footballTeamResponseDtoList = footballTeamService.getAll(TEST_UNPAGED_PAGEABLE);

        Assert.assertFalse(footballTeamResponseDtoList.isEmpty());
    }

}
