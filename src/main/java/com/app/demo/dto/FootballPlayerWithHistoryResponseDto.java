package com.app.demo.dto;

import com.app.demo.dto.base.FootballPlayerBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FootballPlayerWithHistoryResponseDto extends FootballPlayerBaseDto {

    List<FootballPlayerHistoryResponseDto> footballPlayerHistoryResponseDtoList;
}
