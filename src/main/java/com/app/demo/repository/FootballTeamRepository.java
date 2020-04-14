package com.app.demo.repository;

import com.app.demo.model.entity.FootballTeam;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface FootballTeamRepository extends PagingAndSortingRepository<FootballTeam, Long> {

    Optional<FootballTeam> findByUniqueFootballTeamIdentifierAndIsActive(String uFTI, Boolean aTrue);

    boolean existsByUniqueFootballTeamIdentifierAndIsActive(String uFTI, Boolean aTrue);

    void deleteByUniqueFootballTeamIdentifier(String uFTI);
}
