package com.app.demo.service.interfaces;

import com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto;
import com.app.demo.dto.FootballPlayerHistoryResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface FootballPlayerFootballTeamHistoryService {

    void saveOrUpdate(FootballPlayerHistoryInsertOrUpdateDto footballPlayerHistoryInsertOrUpdateDto);

    List<FootballPlayerHistoryResponseDto> getAllByUFPI(String uFPI);

    List<FootballPlayerHistoryResponseDto> getAllByTeamAndJoinDateAndExitDate(String uFTI, LocalDate joinDate, LocalDate exitDate);
}
