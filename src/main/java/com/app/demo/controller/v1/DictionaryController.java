package com.app.demo.controller.v1;

import com.app.demo.model.dto.DictionaryResponseDto;
import com.app.demo.service.interfaces.DictionaryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("api/v1/dictionary")
@RequiredArgsConstructor
@Slf4j
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "5"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
    @GetMapping("/countries")
    @ApiOperation(value = "Returns the country values in DB",
            response = DictionaryResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<List<DictionaryResponseDto>> getAllCountries(@ApiIgnore @NonNull @PageableDefault(page = 0, size = 100) Pageable pageable) {

        List<DictionaryResponseDto> dictionaryResponseDtoList = dictionaryService.getAllCountries(pageable);
        log.info("getAllCountries is completed");
        return ResponseEntity.ok(dictionaryResponseDtoList);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "5"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
    @GetMapping("/currencies")
    @ApiOperation(value = "Returns the country values in DB",
            response = DictionaryResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<List<DictionaryResponseDto>> getAllCurrencies(@ApiIgnore @NonNull @PageableDefault(page = 0, size = 100) Pageable pageable) {

        List<DictionaryResponseDto> dictionaryResponseDtoList = dictionaryService.getAllCurrencies(pageable);
        log.info("getAllCurrencies is completed");
        return ResponseEntity.ok(dictionaryResponseDtoList);
    }
}
