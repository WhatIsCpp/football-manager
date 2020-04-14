package com.app.demo.controller.v1;

import com.app.demo.dto.FootballPlayerInsertOrUpdateDto;
import com.app.demo.dto.FootballPlayerResponseDto;
import com.app.demo.service.interfaces.FootballPlayerService;
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

    @GetMapping
    @ApiOperation(value = "Get All footballPlayer's from DB",
            response = FootballPlayerResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<List<FootballPlayerResponseDto>> getAll(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                  @RequestParam(defaultValue = "0") @Min(0) int size,
                                                                  @RequestParam(required = false) String sortField) {

        List<FootballPlayerResponseDto> footballPlayerResponseDtoList = footballPlayerService.getAll(ControllerUtils.createPageable(size, page, sortField));
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
