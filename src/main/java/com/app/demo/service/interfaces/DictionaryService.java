package com.app.demo.service.interfaces;

import com.app.demo.model.dto.DictionaryResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DictionaryService {
    List<DictionaryResponseDto> getAllCountries(Pageable pageable);

    List<DictionaryResponseDto> getAllCurrencies(Pageable pageable);
}
