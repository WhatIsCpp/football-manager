package com.app.demo.service.implementations;

import com.app.demo.dto.DictionaryResponseDto;
import com.app.demo.mapper.DtoMapper;
import com.app.demo.repository.CountryDictionaryRepository;
import com.app.demo.repository.CurrencyDictionaryRepository;
import com.app.demo.service.interfaces.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryServiceImplementation implements DictionaryService {

    private final CountryDictionaryRepository countryDictionaryRepository;
    private final CurrencyDictionaryRepository currencyDictionaryRepository;

    private final DtoMapper dtoMapper;

    @Override
    public List<DictionaryResponseDto> getAllCountries(Pageable pageable){

        return dtoMapper.asCountryDictionaryResponseDto(countryDictionaryRepository.findAll(pageable).getContent());
    }

    @Override
    public List<DictionaryResponseDto> getAllCurrencies(Pageable pageable){

        return dtoMapper.asCurrencyDictionaryResponseDto(currencyDictionaryRepository.findAll(pageable).getContent());
    }
}
