package com.app.demo.controller.v1;

import com.app.demo.dto.FootballTeamInsertOrUpdateDto;
import com.app.demo.dto.FootballTeamResponseDto;
import com.app.demo.service.interfaces.FootballTeamService;
import com.app.demo.util.ControllerUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;


@RestController
@RequestMapping("api/v1/football-team")
@RequiredArgsConstructor
@Slf4j
public class FootballTeamController {

    private final FootballTeamService footballTeamService;

    @PostMapping
    @ApiOperation(value = "Save or update a footballTeam")
    public ResponseEntity<?> save(@RequestBody FootballTeamInsertOrUpdateDto footballTeamInsertOrUpdateDto) {

        footballTeamService.saveOrUpdate(footballTeamInsertOrUpdateDto);
        log.info("SaveOrUpdate is completed");
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation(value = "Get All footballTeam's from DB",
            response = FootballTeamResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<List<FootballTeamResponseDto>> getAll(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                @RequestParam(defaultValue = "0") @Min(0) int size,
                                                                @RequestParam(required = false) String sortField) {

        List<FootballTeamResponseDto> footballTeamResponseDtoList = footballTeamService.getAll(ControllerUtils.createPageable(size, page, sortField));
        log.info("GetAll is completed");
        return ResponseEntity.ok(footballTeamResponseDtoList);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete footballTeam with given uFTI")
    public ResponseEntity<?> deleteByUFTI(@RequestParam String uFTI) {

        footballTeamService.deleteByUniqueFootballTeamIdentifier(uFTI);
        log.info("DeleteByUFTI is completed");
        return ResponseEntity.ok().build();
    }
}
