package com.app.demo.model.dto.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class FootballPlayerBaseDto implements Serializable {

    private String name;
    private String surname;
    private String uFPI;
    private String footballTeamName;
    private String countryName;
    private Long weight;
    private Long height;
    private LocalDate birthDate;

}
