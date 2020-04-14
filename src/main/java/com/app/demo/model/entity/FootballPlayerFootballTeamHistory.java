package com.app.demo.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "football_player_football_team_history")
@SequenceGenerator(name = "id_sequence", sequenceName = "football_player_football_team_history_sequence", allocationSize = 1)
public class FootballPlayerFootballTeamHistory extends BaseEntity {

    @NotNull
    private LocalDate joinDate;

    private LocalDate exitDate;

    @Column(name = "transfer_fee")
    private BigDecimal transferFee;

    @Column(name = "team_commission")
    private BigDecimal teamCommission;

    @Column(name = "contract_price")
    private BigDecimal contractPrice;

    @ManyToOne
    @JoinColumn(name = "football_player_id")
    private FootballPlayer footballPlayer;

    @ManyToOne
    @JoinColumn(name = "football_team_id")
    private FootballTeam footballTeam;
}
