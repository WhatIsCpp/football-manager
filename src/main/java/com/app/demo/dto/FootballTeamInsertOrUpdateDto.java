package com.app.demo.dto;

import com.app.demo.dto.base.FootballTeamBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class FootballTeamInsertOrUpdateDto extends FootballTeamBaseDto {

    private Boolean isActive;
    private LocalDate createDate;

}
