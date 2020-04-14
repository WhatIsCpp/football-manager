package com.app.demo.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DictionaryResponseDto implements Serializable {

    private String name;
    private String definition;

}
