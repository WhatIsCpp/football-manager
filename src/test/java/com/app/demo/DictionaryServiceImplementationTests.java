package com.app.demo;

import com.app.demo.mapper.DtoMapper;
import com.app.demo.model.dto.DictionaryResponseDto;
import com.app.demo.model.entity.CountryDictionary;
import com.app.demo.model.entity.CurrencyDictionary;
import com.app.demo.repository.CountryDictionaryRepository;
import com.app.demo.repository.CurrencyDictionaryRepository;
import com.app.demo.service.implementations.DictionaryServiceImplementation;
import com.app.demo.service.interfaces.DictionaryService;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class DictionaryServiceImplementationTests {

    private static final String TEST_NAME = "testName";

    private static final Pageable UNPAGED_PAGEABLE = Pageable.unpaged();

    @MockBean
    public CountryDictionaryRepository countryDictionaryRepository;

    @MockBean
    public CurrencyDictionaryRepository currencyDictionaryRepository;

    @MockBean
    public DtoMapper dtoMapper;

    private DictionaryService dictionaryService;

    @Before
    public void setUp(){

        dictionaryService = new DictionaryServiceImplementation(countryDictionaryRepository, currencyDictionaryRepository, dtoMapper);

        CountryDictionary countryDictionary = new CountryDictionary();
        CountryDictionary countryDictionary1 = new CountryDictionary();

        List<CountryDictionary> countryDictionaryList = new ArrayList<>();
        countryDictionaryList.add(countryDictionary);
        countryDictionaryList.add(countryDictionary1);

        Page<CountryDictionary> countryDictionaryPage = new PageImpl<CountryDictionary>(countryDictionaryList);

        Mockito.when(countryDictionaryRepository.findAll(UNPAGED_PAGEABLE))
                .thenReturn(countryDictionaryPage);

        CurrencyDictionary currencyDictionary = new CurrencyDictionary();
        CurrencyDictionary currencyDictionary1 = new CurrencyDictionary();

        List<CurrencyDictionary> currencyDictionaryList = new ArrayList<>();
        currencyDictionaryList.add(currencyDictionary);
        currencyDictionaryList.add(currencyDictionary1);

        Page<CurrencyDictionary> currencyDictionaryPage = new PageImpl<CurrencyDictionary>(currencyDictionaryList);

        Mockito.when(currencyDictionaryRepository.findAll(UNPAGED_PAGEABLE))
                .thenReturn(currencyDictionaryPage);

        DictionaryResponseDto dictionaryResponseDto = new DictionaryResponseDto();
        dictionaryResponseDto.setName(TEST_NAME);

        DictionaryResponseDto dictionaryResponseDto1 = new DictionaryResponseDto();
        dictionaryResponseDto1.setName(TEST_NAME);

        List<DictionaryResponseDto> dictionaryResponseDtoList = new ArrayList<>();
        dictionaryResponseDtoList.add(dictionaryResponseDto);
        dictionaryResponseDtoList.add(dictionaryResponseDto1);

        Mockito.when(dtoMapper.asCountryDictionaryResponseDto(countryDictionaryRepository.findAll(UNPAGED_PAGEABLE).getContent()))
                .thenReturn(dictionaryResponseDtoList);

        Mockito.when(dtoMapper.asCurrencyDictionaryResponseDto(currencyDictionaryRepository.findAll(UNPAGED_PAGEABLE).getContent()))
                .thenReturn(dictionaryResponseDtoList);
    }

    @Test
    public void whenGetAllCountries_thenReturnDictionaryResponseDtoList(){

        List<DictionaryResponseDto> dictionaryResponseDtoList = dictionaryService.getAllCountries(UNPAGED_PAGEABLE);

        Assert.assertFalse(dictionaryResponseDtoList.isEmpty());
    }

    @Test
    public void whenGetAllCurrencies_thenReturnDictionaryResponseDtoList(){

        List<DictionaryResponseDto> dictionaryResponseDtoList = dictionaryService.getAllCurrencies(UNPAGED_PAGEABLE);

        Assert.assertFalse(dictionaryResponseDtoList.isEmpty());
    }
}
