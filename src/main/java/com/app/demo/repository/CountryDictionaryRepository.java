package com.app.demo.repository;

import com.app.demo.model.entity.CountryDictionary;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CountryDictionaryRepository extends PagingAndSortingRepository<CountryDictionary, Long> {

    Optional<CountryDictionary> findByNameAndIsActive(String name, boolean isActive);
}
