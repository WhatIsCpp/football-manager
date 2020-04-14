package com.app.demo.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "currency_dictionary")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "id_sequence", sequenceName = "currency_dictionary_sequence", allocationSize = 1)
public class CurrencyDictionary extends DictionaryBaseEntity {


}
