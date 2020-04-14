package com.app.demo.dto;

import com.app.demo.dto.base.FootballPlayerBaseDto;
import com.app.demo.dto.base.FootballTeamBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FootballTeamResponseDto extends FootballTeamBaseDto {

    private List<FootballPlayerResponseDto> footballPlayerBaseDtoList;
}
