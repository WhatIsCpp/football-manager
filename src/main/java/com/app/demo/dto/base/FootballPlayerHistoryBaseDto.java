package com.app.demo.dto.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FootballPlayerHistoryBaseDto implements Serializable {

    private Long id;
    private LocalDate joinDate;
    private LocalDate exitDate;
    private BigDecimal transferFee;
    private BigDecimal teamCommission;
}
