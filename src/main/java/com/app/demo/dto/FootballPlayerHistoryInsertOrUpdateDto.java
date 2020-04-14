package com.app.demo.dto;

import com.app.demo.dto.base.FootballPlayerHistoryBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FootballPlayerHistoryInsertOrUpdateDto extends FootballPlayerHistoryBaseDto {

    private String uFTI;
    private String uFPI;
    private Boolean isActive;
}
