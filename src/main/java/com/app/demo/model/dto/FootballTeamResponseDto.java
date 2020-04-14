package com.app.demo.model.dto;

import com.app.demo.model.dto.base.FootballTeamBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FootballTeamResponseDto extends FootballTeamBaseDto {

    private List<FootballPlayerResponseDto> footballPlayerBaseDtoList;
}
