package com.app.demo.dto;

import com.app.demo.dto.base.FootballPlayerHistoryBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class FootballPlayerHistoryResponseDto extends FootballPlayerHistoryBaseDto {

    private String footballTeamName;
    private String footballPlayerName;
}
