package com.app.demo.mapper;

import com.app.demo.model.dto.DictionaryResponseDto;
import com.app.demo.model.dto.FootballPlayerHistoryInsertOrUpdateDto;
import com.app.demo.model.dto.FootballPlayerHistoryResponseDto;
import com.app.demo.model.dto.FootballPlayerInsertOrUpdateDto;
import com.app.demo.model.dto.FootballPlayerResponseDto;
import com.app.demo.model.dto.FootballPlayerTransferRequestDto;
import com.app.demo.model.dto.FootballPlayerTransferResponseDto;
import com.app.demo.model.dto.FootballPlayerWithHistoryResponseDto;
import com.app.demo.model.dto.FootballTeamInsertOrUpdateDto;
import com.app.demo.model.dto.FootballTeamResponseDto;
import com.app.demo.model.entity.CountryDictionary;
import com.app.demo.model.entity.CurrencyDictionary;
import com.app.demo.model.entity.FootballPlayer;
import com.app.demo.model.entity.FootballPlayerFootballTeamHistory;
import com.app.demo.model.entity.FootballTeam;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;

import java.util.List;

@Mapper(withIoC = IoC.SPRING,
        withIgnoreMissing = IgnoreMissing.ALL)
public interface DtoMapper {

    List<FootballPlayerResponseDto> asFootballPlayerResponseDto(List<FootballPlayer> footballPlayerList);

    List<FootballPlayerHistoryResponseDto> asFootballPlayerHistoryResponseDto(List<FootballPlayerFootballTeamHistory> footballPlayerFootballTeamHistoryList);

    List<DictionaryResponseDto> asCountryDictionaryResponseDto(List<CountryDictionary> countryDictionaryList);

    List<DictionaryResponseDto> asCurrencyDictionaryResponseDto(List<CurrencyDictionary> currencyDictionaryList);

    List<FootballTeamResponseDto> asFootballTeamResponseDto(List<FootballTeam> footballTeamList);

    DictionaryResponseDto asCountryDictionaryResponseDto(CountryDictionary countryDictionary);

    DictionaryResponseDto asCurrencyDictionaryResponseDto(CurrencyDictionary currencyDictionary);

    @Maps(withCustomFields = {
            @Field({"footballTeamName", "footballTeam.name"}),
            @Field({"countryName", "countryDictionary.name"}),
            @Field({"uFPI", "uniqueFootballPlayerIdentifier"})
    })
    FootballPlayerResponseDto asFootballPlayerResponseDto(FootballPlayer footballPlayer);

    @Maps(withCustomFields = {
            @Field({"footballTeamName", "footballTeam.name"}),
            @Field({"countryName", "countryDictionary.name"}),
            @Field({"uFPI", "uniqueFootballPlayerIdentifier"}),
            @Field({"footballTeam.uniqueFootballTeamIdentifier", "teamUFTI"})
    })
    FootballPlayer asFootballPlayer(FootballPlayerInsertOrUpdateDto footballPlayerInsertOrUpdateDto);

    @Maps(withCustomFields = {
            @Field({"footballTeamName", "footballTeam.name"}),
            @Field({"countryName", "countryDictionary.name"}),
            @Field({"uFPI", "uniqueFootballPlayerIdentifier"}),
            @Field({"footballPlayerHistoryResponseDtoList", "footballPlayerFootballTeamHistories"})
    })
    FootballPlayerWithHistoryResponseDto asFootballPlayerWithHistoryResponseDto(FootballPlayer footballPlayer);

    @Maps(withCustomFields = {
            @Field({"footballTeamName", "footballTeam.name"}),
            @Field({"countryName", "countryDictionary.name"}),
            @Field({"uFPI", "uniqueFootballPlayerIdentifier"})
    })
    FootballPlayerTransferResponseDto asFootballPlayerTransferResponseDto(FootballPlayer footballPlayer);

    @Maps(withCustomFields = {
            @Field({"uniqueFootballTeamIdentifier", "uFTI"}),
            @Field({"countryDictionary.name", "countryName"}),
            @Field({"currencyDictionary.name", "currencyName"})
    })
    FootballTeam asFootballTeam(FootballTeamInsertOrUpdateDto footballTeamInsertOrUpdateDto);

    @Maps(withCustomFields = {
            @Field({"uniqueFootballTeamIdentifier", "uFTI"}),
            @Field({"countryDictionary.name", "countryName"}),
            @Field({"currencyDictionary.name", "currencyName"}),
            @Field({"footballPlayerBaseDtoList", "footballPlayerList"})
    })
    FootballTeamResponseDto asFootballTeamResponseDto(FootballTeam footballTeam);

    @Maps(withCustomFields = {
            @Field({"footballTeamName", "footballTeam.name"}),
            @Field({"footballPlayerName", "footballPlayer.name"})
    })
    FootballPlayerHistoryResponseDto asFootballPlayerHistoryResponseDto(FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory);

    @Maps(withCustomFields = {
            @Field({"footballTeam.uniqueFootballTeamIdentifier", "uFTI"}),
            @Field({"footballPlayer.uniqueFootballPlayerIdentifier", "uFPI"})
    })
    FootballPlayerFootballTeamHistory asFootballPlayerFootballTeamHistory(FootballPlayerHistoryInsertOrUpdateDto footballPlayerHistoryInsertOrUpdateDto);

    @Maps(withCustomFields = {
            @Field({"joinDate", "startDate"}),
            @Field({"id", "historyId"})
    })
    FootballPlayerHistoryInsertOrUpdateDto asFootballPlayerHistoryInsertOrUpdateDto(FootballPlayerTransferResponseDto footballPlayerTransferResponseDto);

    FootballPlayerHistoryInsertOrUpdateDto asFootballPlayerHistoryInsertOrUpdateDto(FootballPlayerTransferRequestDto footballPlayerTransferRequestDto);

}
