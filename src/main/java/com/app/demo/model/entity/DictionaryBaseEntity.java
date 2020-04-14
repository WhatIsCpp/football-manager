package com.app.demo.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class DictionaryBaseEntity extends BaseEntity{

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private String definition;
}
