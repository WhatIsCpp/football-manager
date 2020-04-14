package com.app.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DictionaryResponseDto implements Serializable {

    private String name;
    private String definition;

}
