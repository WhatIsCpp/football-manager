package com.app.demo.controller.v1;

import com.app.demo.model.dto.FootballPlayerHistoryInsertOrUpdateDto;
import com.app.demo.model.dto.FootballPlayerHistoryResponseDto;
import com.app.demo.service.interfaces.FootballPlayerFootballTeamHistoryService;
import com.app.demo.service.interfaces.TransferService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/history")
@RequiredArgsConstructor
@Slf4j
public class TeamHistoryController {

    private final TransferService transferService;
    private final FootballPlayerFootballTeamHistoryService footballPlayerFootballTeamHistoryService;

    @PostMapping
    @ApiOperation(value = "Save or update a footballPlayerFootballTeamHistory")
    public ResponseEntity<?> save(@RequestBody FootballPlayerHistoryInsertOrUpdateDto footballPlayerHistoryInsertOrUpdateDto) {

        footballPlayerFootballTeamHistoryService.saveOrUpdate(footballPlayerHistoryInsertOrUpdateDto);
        log.info("SaveOrUpdate is completed");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/footballPlayer/{footballPlayerUFPI}")
    @ApiOperation(value = "Get the History of given footballPlayeruFPI",
            response = FootballPlayerHistoryResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<List<FootballPlayerHistoryResponseDto>> getFootballPlayerHistory(@PathVariable String footballPlayerUFPI) {

        List<FootballPlayerHistoryResponseDto> footballPlayerHistoryResponseDtoList = footballPlayerFootballTeamHistoryService.getAllByUFPI(footballPlayerUFPI);
        log.info("GetFootballPlayerHistory is completed");
        return ResponseEntity.ok(footballPlayerHistoryResponseDtoList);
    }

    @GetMapping("/footballTeam/{footballTeamUFTI}")
    @ApiOperation(value = "Get the Players that played in given team in given date",
            response = FootballPlayerHistoryResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<List<FootballPlayerHistoryResponseDto>> getFootballTeamHistory(@PathVariable String footballTeamUFTI,
                                                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateToBeSearched) {

        List<FootballPlayerHistoryResponseDto> footballPlayerHistoryResponseDtoList = footballPlayerFootballTeamHistoryService.getAllByTeamAndJoinDateAndExitDate(footballTeamUFTI, dateToBeSearched);
        log.info("getFootballTeamHistory is completed");
        return ResponseEntity.ok(footballPlayerHistoryResponseDtoList);
    }


}
