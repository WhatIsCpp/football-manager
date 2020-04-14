package com.app.demo.repository;

import com.app.demo.model.entity.FootballPlayer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface FootballPlayerRepository extends PagingAndSortingRepository<FootballPlayer, Long> {

    Optional<FootballPlayer> findByUniqueFootballPlayerIdentifierAndIsActive(String uFPI, boolean isActive);

    boolean existsByUniqueFootballPlayerIdentifierAndIsActive(String uFPI, boolean isActive);

    void deleteByUniqueFootballPlayerIdentifier(String uFPI);

    List<FootballPlayer> findByNameAndSurname(String name, String surName, Pageable pageable);

    List<FootballPlayer> findAllByIsActive(boolean isActive, Pageable pageable);

}
