package com.app.demo.model.dto;

import com.app.demo.model.dto.base.FootballPlayerBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FootballPlayerInsertOrUpdateDto extends FootballPlayerBaseDto {

    private String teamUFTI;
    private Boolean isActive;
    private List<FootballPlayerHistoryInsertOrUpdateDto> footballPlayerHistoryInsertOrUpdateDtoList;
}
