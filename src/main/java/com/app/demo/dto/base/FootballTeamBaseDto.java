package com.app.demo.dto.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class FootballTeamBaseDto implements Serializable {

    private String name;
    private String uFTI;
    private String countryName;
    private String currencyName;
}
