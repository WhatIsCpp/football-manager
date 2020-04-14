package com.app.demo.repository;

import com.app.demo.model.entity.FootballPlayerFootballTeamHistory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FootballPlayerFootballTeamHistoryRepository extends PagingAndSortingRepository<FootballPlayerFootballTeamHistory, Long> {

    Optional<FootballPlayerFootballTeamHistory> findById(Long id);

    Optional<FootballPlayerFootballTeamHistory> findByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate(String uFPI, LocalDate exitDate);

    boolean existsByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate(String uFPI, LocalDate exitDate);

    List<FootballPlayerFootballTeamHistory> findAllByFootballPlayer_UniqueFootballPlayerIdentifier(String uFPI);

    List<FootballPlayerFootballTeamHistory> findAllByFootballTeam_UniqueFootballTeamIdentifierAndJoinDateIsBeforeAndExitDateIsAfter(String uFTI, LocalDate joindate, LocalDate exitDate);

}
