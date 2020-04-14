package com.app.demo.controller.v1;

import com.app.demo.dto.DictionaryResponseDto;
import com.app.demo.service.interfaces.DictionaryService;
import com.app.demo.util.ControllerUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("api/v1/dictionary")
@RequiredArgsConstructor
@Slf4j
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/countries")
    @ApiOperation(value = "Returns the country values in DB",
    response = DictionaryResponseDto.class,
    responseContainer = "List")
    public ResponseEntity<List<DictionaryResponseDto>> getAllCountries(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                       @RequestParam(defaultValue = "0") @Min(0) int size,
                                                                       @RequestParam(required = false) String sortField) {

        List<DictionaryResponseDto> dictionaryResponseDtoList = dictionaryService.getAllCountries(ControllerUtils.createPageable(size, page, sortField));
        log.info("getAllCountries is completed");
        return ResponseEntity.ok(dictionaryResponseDtoList);
    }

    @GetMapping("/currencies")
    @ApiOperation(value = "Returns the country values in DB",
            response = DictionaryResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<List<DictionaryResponseDto>> getAllCurrencies(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                                        @RequestParam(defaultValue = "0") @Min(0) int size,
                                                                        @RequestParam(required = false) String sortField) {

        List<DictionaryResponseDto> dictionaryResponseDtoList = dictionaryService.getAllCurrencies(ControllerUtils.createPageable(size, page, sortField));
        log.info("getAllCurrencies is completed");
        return ResponseEntity.ok(dictionaryResponseDtoList);
    }
}
