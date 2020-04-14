package com.app.demo.controller.v1;

import com.app.demo.model.dto.FootballTeamInsertOrUpdateDto;
import com.app.demo.model.dto.FootballTeamResponseDto;
import com.app.demo.service.interfaces.FootballTeamService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "5"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
    @GetMapping
    @ApiOperation(value = "Get All footballTeam's from DB",
            response = FootballTeamResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<List<FootballTeamResponseDto>> getAll(@ApiIgnore @NonNull @PageableDefault(page = 0, size = 100) Pageable pageable) {

        List<FootballTeamResponseDto> footballTeamResponseDtoList = footballTeamService.getAll(pageable);
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
