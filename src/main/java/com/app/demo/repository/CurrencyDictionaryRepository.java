package com.app.demo.repository;

import com.app.demo.model.entity.CurrencyDictionary;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CurrencyDictionaryRepository extends PagingAndSortingRepository<CurrencyDictionary, Long> {

    Optional<CurrencyDictionary> findByNameAndIsActive(String name, boolean isActive);
}
