package com.app.demo.dto;

import com.app.demo.dto.base.FootballPlayerBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class FootballPlayerTransferResponseDto extends FootballPlayerBaseDto {

    private String uFTI;
    private LocalDate startDate;
    private Long historyId;
    private BigDecimal transferFee;
    private BigDecimal teamCommission;
    private BigDecimal contractPrice;

}
