package com.app.demo.repository;

import com.app.demo.model.entity.FootballPlayerFootballTeamHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FootballPlayerFootballTeamHistoryRepository extends PagingAndSortingRepository<FootballPlayerFootballTeamHistory, Long> {

    Optional<FootballPlayerFootballTeamHistory> findById(Long id);

    Optional<FootballPlayerFootballTeamHistory> findByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate(String uFPI, LocalDate exitDate);

    boolean existsByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate(String uFPI, LocalDate exitDate);

    List<FootballPlayerFootballTeamHistory> findAllByFootballPlayer_UniqueFootballPlayerIdentifier(String uFPI);

    @Query("SELECT fpfth FROM FootballPlayerFootballTeamHistory fpfth LEFT OUTER JOIN FootballTeam b ON fpfth.footballTeam.id = b.id WHERE  b.uniqueFootballTeamIdentifier = ?1 AND fpfth.joinDate <?2 AND (fpfth.exitDate >?2 OR fpfth.exitDate IS NULL)")
    List<FootballPlayerFootballTeamHistory> findAllByDate(String uFTI, LocalDate joinDate);
}
