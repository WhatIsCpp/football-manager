package com.app.demo.model.dto;

import com.app.demo.model.dto.base.FootballPlayerBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FootballPlayerWithHistoryResponseDto extends FootballPlayerBaseDto {

    private List<FootballPlayerHistoryResponseDto> footballPlayerHistoryResponseDtoList;
}
