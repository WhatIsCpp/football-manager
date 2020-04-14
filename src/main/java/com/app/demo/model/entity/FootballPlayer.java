package com.app.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "football_player")
@SequenceGenerator(name = "id_sequence", sequenceName = "football_player_sequence", allocationSize = 1)
public class FootballPlayer extends BaseEntity {

    private String name;

    private String surname;

    private Long weight;

    private Long height;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "unique_football_player_identifier")
    private String uniqueFootballPlayerIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "football_team_id")
    private FootballTeam footballTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_dictionary_id")
    private CountryDictionary countryDictionary;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "football_player_id")
    private List<FootballPlayerFootballTeamHistory> footballPlayerFootballTeamHistories;

}
