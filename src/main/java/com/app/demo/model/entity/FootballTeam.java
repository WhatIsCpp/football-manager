package com.app.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "football_team", schema = "public")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "id_sequence", sequenceName = "football_team_sequence")
public class FootballTeam extends BaseEntity {

    private String name;

    private String uniqueFootballTeamIdentifier;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "country_dictionary_id")
    private CountryDictionary countryDictionary;

    @ManyToOne
    @JoinColumn(name = "currency_dictionary_id")
    private CurrencyDictionary currencyDictionary;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "football_team_id")
    private List<FootballPlayer> footballPlayerList;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "football_team_id")
    private List<FootballPlayerFootballTeamHistory> footballPlayerFootballTeamHistoryList;

}
