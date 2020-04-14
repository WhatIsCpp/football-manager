package com.app.demo.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FootballPlayerTransferRequestDto {

    private String uFTI;
    private String uFPI;
    private LocalDate startDate;
    private BigDecimal transferFee;
    private BigDecimal teamCommission;
    private BigDecimal contractPrice;

}
