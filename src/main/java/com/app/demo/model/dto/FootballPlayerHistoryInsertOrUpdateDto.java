package com.app.demo.model.dto;

import com.app.demo.model.dto.base.FootballPlayerHistoryBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FootballPlayerHistoryInsertOrUpdateDto extends FootballPlayerHistoryBaseDto {

    private String uFTI;
    private String uFPI;
    private Boolean isActive;
}
