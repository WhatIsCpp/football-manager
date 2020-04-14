package com.app.demo.controller.v1;

import com.app.demo.model.dto.FootballPlayerInsertOrUpdateDto;
import com.app.demo.model.dto.FootballPlayerResponseDto;
import com.app.demo.service.interfaces.FootballPlayerService;
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
@RequestMapping("api/v1/football-player")
@RequiredArgsConstructor
@Slf4j
public class FootballPlayerController {

    private final FootballPlayerService footballPlayerService;

    @PostMapping
    @ApiOperation(value = "Save or update a footballPlayer")
    public ResponseEntity<?> save(@RequestBody FootballPlayerInsertOrUpdateDto footballPlayerInsertOrUpdateDto) {

        footballPlayerService.saveOrUpdate(footballPlayerInsertOrUpdateDto);
        log.info("SaveOrUpdate is completed");
        return ResponseEntity.ok().build();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "5"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
    @GetMapping
    @ApiOperation(value = "Get All footballPlayer's from DB",
            response = FootballPlayerResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<List<FootballPlayerResponseDto>> getAll(@ApiIgnore @NonNull @PageableDefault(page = 0, size = 100) Pageable pageable) {

        List<FootballPlayerResponseDto> footballPlayerResponseDtoList = footballPlayerService.getAll(pageable);
        log.info("GetAll is completed");
        return ResponseEntity.ok(footballPlayerResponseDtoList);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete footballPlayer with given uFPI")
    public ResponseEntity<?> deleteByUFPI(@RequestParam String uFPI) {

        footballPlayerService.deleteByUniqueFootballPlayerIdentifier(uFPI);
        log.info("DeleteByUFPI is completed");
        return ResponseEntity.ok().build();
    }
}
