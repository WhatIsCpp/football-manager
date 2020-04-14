package com.app.demo.controller.v1;

import com.app.demo.model.dto.FootballPlayerResponseDto;
import com.app.demo.model.dto.FootballPlayerTransferRequestDto;
import com.app.demo.model.dto.FootballPlayerTransferResponseDto;
import com.app.demo.service.interfaces.TransferService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/transfer")
@RequiredArgsConstructor
@Slf4j
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    @ApiOperation(value = "Transfer the player with given inputs",
            response = FootballPlayerResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<FootballPlayerResponseDto> transferPlayer(@RequestBody FootballPlayerTransferRequestDto footballPlayerTransferRequestDto) {

        FootballPlayerResponseDto footballPlayerResponseDto = transferService.transferPlayer(footballPlayerTransferRequestDto);
        log.info("TransferPlayer is completed");
        return ResponseEntity.ok(footballPlayerResponseDto);
    }

    @GetMapping("/calculateFee")
    @ApiOperation(value = "Calculate transferFee with given inputs",
            response = FootballPlayerTransferResponseDto.class,
            responseContainer = "List")
    public ResponseEntity<FootballPlayerTransferResponseDto> calculateTransferFee(@RequestParam String uFTI,
                                                                                  @RequestParam String uFPI,
                                                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                                  @RequestParam Long teamCommissionPercentage) {

        FootballPlayerTransferResponseDto footballPlayerTransferResponseDto = transferService.getTransferFee(uFTI, uFPI, startDate, teamCommissionPercentage);
        log.info("CalculateTransferFee is completed");
        return ResponseEntity.ok(footballPlayerTransferResponseDto);
    }

}
