package com.app.demo.model.dto;

import com.app.demo.model.dto.base.FootballPlayerHistoryBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class FootballPlayerHistoryResponseDto extends FootballPlayerHistoryBaseDto {

    private String footballTeamName;
    private String footballPlayerName;
}
