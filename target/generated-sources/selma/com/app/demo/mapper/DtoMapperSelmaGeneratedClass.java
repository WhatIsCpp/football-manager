// GENERATED BY S3LM4
package com.app.demo.mapper;


@org.springframework.stereotype.Service("")
public final class DtoMapperSelmaGeneratedClass
    implements DtoMapper {

  @Override
  public final java.util.List<com.app.demo.dto.FootballPlayerResponseDto> asFootballPlayerResponseDto(java.util.List<com.app.demo.model.entity.FootballPlayer> inFootballPlayer) {
    java.util.List<com.app.demo.dto.FootballPlayerResponseDto> out = null;
    if (inFootballPlayer != null) {
      java.util.ArrayList<com.app.demo.dto.FootballPlayerResponseDto> aoutTmpCollection = new java.util.ArrayList<com.app.demo.dto.FootballPlayerResponseDto>(inFootballPlayer.size());
      for (com.app.demo.model.entity.FootballPlayer aoutItem : inFootballPlayer) {
        aoutTmpCollection.add(asFootballPlayerResponseDto(aoutItem));
      }
      out = aoutTmpCollection;
    }
    return out;
  }


  @Override
  public final java.util.List<com.app.demo.dto.FootballPlayerHistoryResponseDto> asFootballPlayerHistoryResponseDto(java.util.List<com.app.demo.model.entity.FootballPlayerFootballTeamHistory> inFootballPlayerFootballTeamHistory) {
    java.util.List<com.app.demo.dto.FootballPlayerHistoryResponseDto> out = null;
    if (inFootballPlayerFootballTeamHistory != null) {
      java.util.ArrayList<com.app.demo.dto.FootballPlayerHistoryResponseDto> aoutTmpCollection = new java.util.ArrayList<com.app.demo.dto.FootballPlayerHistoryResponseDto>(inFootballPlayerFootballTeamHistory.size());
      for (com.app.demo.model.entity.FootballPlayerFootballTeamHistory aoutItem : inFootballPlayerFootballTeamHistory) {
        aoutTmpCollection.add(asFootballPlayerHistoryResponseDto(aoutItem));
      }
      out = aoutTmpCollection;
    }
    return out;
  }


  @Override
  public final java.util.List<com.app.demo.dto.DictionaryResponseDto> asCountryDictionaryResponseDto(java.util.List<com.app.demo.model.entity.CountryDictionary> inCountryDictionary) {
    java.util.List<com.app.demo.dto.DictionaryResponseDto> out = null;
    if (inCountryDictionary != null) {
      java.util.ArrayList<com.app.demo.dto.DictionaryResponseDto> aoutTmpCollection = new java.util.ArrayList<com.app.demo.dto.DictionaryResponseDto>(inCountryDictionary.size());
      for (com.app.demo.model.entity.CountryDictionary aoutItem : inCountryDictionary) {
        aoutTmpCollection.add(asCountryDictionaryResponseDto(aoutItem));
      }
      out = aoutTmpCollection;
    }
    return out;
  }


  @Override
  public final java.util.List<com.app.demo.dto.DictionaryResponseDto> asCurrencyDictionaryResponseDto(java.util.List<com.app.demo.model.entity.CurrencyDictionary> inCurrencyDictionary) {
    java.util.List<com.app.demo.dto.DictionaryResponseDto> out = null;
    if (inCurrencyDictionary != null) {
      java.util.ArrayList<com.app.demo.dto.DictionaryResponseDto> aoutTmpCollection = new java.util.ArrayList<com.app.demo.dto.DictionaryResponseDto>(inCurrencyDictionary.size());
      for (com.app.demo.model.entity.CurrencyDictionary aoutItem : inCurrencyDictionary) {
        aoutTmpCollection.add(asCurrencyDictionaryResponseDto(aoutItem));
      }
      out = aoutTmpCollection;
    }
    return out;
  }


  @Override
  public final java.util.List<com.app.demo.dto.FootballTeamResponseDto> asFootballTeamResponseDto(java.util.List<com.app.demo.model.entity.FootballTeam> inFootballTeam) {
    java.util.List<com.app.demo.dto.FootballTeamResponseDto> out = null;
    if (inFootballTeam != null) {
      java.util.ArrayList<com.app.demo.dto.FootballTeamResponseDto> aoutTmpCollection = new java.util.ArrayList<com.app.demo.dto.FootballTeamResponseDto>(inFootballTeam.size());
      for (com.app.demo.model.entity.FootballTeam aoutItem : inFootballTeam) {
        aoutTmpCollection.add(asFootballTeamResponseDto(aoutItem));
      }
      out = aoutTmpCollection;
    }
    return out;
  }


  @Override
  public final com.app.demo.dto.DictionaryResponseDto asCountryDictionaryResponseDto(com.app.demo.model.entity.CountryDictionary inCountryDictionary) {
    com.app.demo.dto.DictionaryResponseDto out = null;
    if (inCountryDictionary != null) {
      out = new com.app.demo.dto.DictionaryResponseDto();
      out.setDefinition(inCountryDictionary.getDefinition());
      out.setName(inCountryDictionary.getName());
    }
    return out;
  }


  @Override
  public final com.app.demo.dto.DictionaryResponseDto asCurrencyDictionaryResponseDto(com.app.demo.model.entity.CurrencyDictionary inCurrencyDictionary) {
    com.app.demo.dto.DictionaryResponseDto out = null;
    if (inCurrencyDictionary != null) {
      out = new com.app.demo.dto.DictionaryResponseDto();
      out.setDefinition(inCurrencyDictionary.getDefinition());
      out.setName(inCurrencyDictionary.getName());
    }
    return out;
  }


  @Override
  public final com.app.demo.dto.FootballPlayerResponseDto asFootballPlayerResponseDto(com.app.demo.model.entity.FootballPlayer inFootballPlayer) {
    com.app.demo.dto.FootballPlayerResponseDto out = null;
    if (inFootballPlayer != null) {
      out = new com.app.demo.dto.FootballPlayerResponseDto();
      out.setBirthDate(inFootballPlayer.getBirthDate());
      out.setHeight(inFootballPlayer.getHeight());
      out.setName(inFootballPlayer.getName());
      out.setSurname(inFootballPlayer.getSurname());
      out.setUFPI(inFootballPlayer.getUniqueFootballPlayerIdentifier());
      out.setWeight(inFootballPlayer.getWeight());
      if (inFootballPlayer.getCountryDictionary() != null) {
        out.setCountryName(inFootballPlayer.getCountryDictionary().getName());
      }
      if (inFootballPlayer.getFootballTeam() != null) {
        out.setFootballTeamName(inFootballPlayer.getFootballTeam().getName());
      }
    }
    return out;
  }


  @Override
  public final com.app.demo.model.entity.FootballPlayer asFootballPlayer(com.app.demo.dto.FootballPlayerInsertOrUpdateDto inFootballPlayerInsertOrUpdateDto) {
    com.app.demo.model.entity.FootballPlayer out = null;
    if (inFootballPlayerInsertOrUpdateDto != null) {
      out = new com.app.demo.model.entity.FootballPlayer();
      out.setBirthDate(inFootballPlayerInsertOrUpdateDto.getBirthDate());
      out.setHeight(inFootballPlayerInsertOrUpdateDto.getHeight());
      out.setIsActive(inFootballPlayerInsertOrUpdateDto.getIsActive());
      out.setName(inFootballPlayerInsertOrUpdateDto.getName());
      out.setSurname(inFootballPlayerInsertOrUpdateDto.getSurname());
      out.setUniqueFootballPlayerIdentifier(inFootballPlayerInsertOrUpdateDto.getUFPI());
      out.setWeight(inFootballPlayerInsertOrUpdateDto.getWeight());
      if (inFootballPlayerInsertOrUpdateDto.getCountryName() != null) {
        if (out.getCountryDictionary() == null) {
          out.setCountryDictionary(new com.app.demo.model.entity.CountryDictionary());
        }
        out.getCountryDictionary().setName(inFootballPlayerInsertOrUpdateDto.getCountryName());
      }
      if (inFootballPlayerInsertOrUpdateDto.getFootballTeamName() != null) {
        if (out.getFootballTeam() == null) {
          out.setFootballTeam(new com.app.demo.model.entity.FootballTeam());
        }
        out.getFootballTeam().setName(inFootballPlayerInsertOrUpdateDto.getFootballTeamName());
      }
      if (inFootballPlayerInsertOrUpdateDto.getTeamUFTI() != null) {
        if (out.getFootballTeam() == null) {
          out.setFootballTeam(new com.app.demo.model.entity.FootballTeam());
        }
        out.getFootballTeam().setUniqueFootballTeamIdentifier(inFootballPlayerInsertOrUpdateDto.getTeamUFTI());
      }
    }
    return out;
  }


  @Override
  public final com.app.demo.dto.FootballPlayerWithHistoryResponseDto asFootballPlayerWithHistoryResponseDto(com.app.demo.model.entity.FootballPlayer inFootballPlayer) {
    com.app.demo.dto.FootballPlayerWithHistoryResponseDto out = null;
    if (inFootballPlayer != null) {
      out = new com.app.demo.dto.FootballPlayerWithHistoryResponseDto();
      out.setBirthDate(inFootballPlayer.getBirthDate());
      if (inFootballPlayer.getFootballPlayerFootballTeamHistories() != null) {
        java.util.ArrayList<com.app.demo.dto.FootballPlayerHistoryResponseDto> afootballplayerfootballteamhistoriesTmpCollection = new java.util.ArrayList<com.app.demo.dto.FootballPlayerHistoryResponseDto>(inFootballPlayer.getFootballPlayerFootballTeamHistories().size());
        for (com.app.demo.model.entity.FootballPlayerFootballTeamHistory afootballplayerfootballteamhistoriesItem : inFootballPlayer.getFootballPlayerFootballTeamHistories()) {
          afootballplayerfootballteamhistoriesTmpCollection.add(asFootballPlayerHistoryResponseDto(afootballplayerfootballteamhistoriesItem));
        }
        out.setFootballPlayerHistoryResponseDtoList(afootballplayerfootballteamhistoriesTmpCollection);
      }
      else {
        out.setFootballPlayerHistoryResponseDtoList(null);
      }
      out.setHeight(inFootballPlayer.getHeight());
      out.setName(inFootballPlayer.getName());
      out.setSurname(inFootballPlayer.getSurname());
      out.setUFPI(inFootballPlayer.getUniqueFootballPlayerIdentifier());
      out.setWeight(inFootballPlayer.getWeight());
      if (inFootballPlayer.getCountryDictionary() != null) {
        out.setCountryName(inFootballPlayer.getCountryDictionary().getName());
      }
      if (inFootballPlayer.getFootballTeam() != null) {
        out.setFootballTeamName(inFootballPlayer.getFootballTeam().getName());
      }
    }
    return out;
  }


  @Override
  public final com.app.demo.dto.FootballPlayerTransferResponseDto asFootballPlayerTransferResponseDto(com.app.demo.model.entity.FootballPlayer inFootballPlayer) {
    com.app.demo.dto.FootballPlayerTransferResponseDto out = null;
    if (inFootballPlayer != null) {
      out = new com.app.demo.dto.FootballPlayerTransferResponseDto();
      out.setBirthDate(inFootballPlayer.getBirthDate());
      out.setHeight(inFootballPlayer.getHeight());
      out.setName(inFootballPlayer.getName());
      out.setSurname(inFootballPlayer.getSurname());
      out.setUFPI(inFootballPlayer.getUniqueFootballPlayerIdentifier());
      out.setWeight(inFootballPlayer.getWeight());
      if (inFootballPlayer.getCountryDictionary() != null) {
        out.setCountryName(inFootballPlayer.getCountryDictionary().getName());
      }
      if (inFootballPlayer.getFootballTeam() != null) {
        out.setFootballTeamName(inFootballPlayer.getFootballTeam().getName());
      }
    }
    return out;
  }


  @Override
  public final com.app.demo.model.entity.FootballTeam asFootballTeam(com.app.demo.dto.FootballTeamInsertOrUpdateDto inFootballTeamInsertOrUpdateDto) {
    com.app.demo.model.entity.FootballTeam out = null;
    if (inFootballTeamInsertOrUpdateDto != null) {
      out = new com.app.demo.model.entity.FootballTeam();
      out.setCreateDate(inFootballTeamInsertOrUpdateDto.getCreateDate());
      out.setIsActive(inFootballTeamInsertOrUpdateDto.getIsActive());
      out.setName(inFootballTeamInsertOrUpdateDto.getName());
      out.setUniqueFootballTeamIdentifier(inFootballTeamInsertOrUpdateDto.getUFTI());
      if (inFootballTeamInsertOrUpdateDto.getCountryName() != null) {
        if (out.getCountryDictionary() == null) {
          out.setCountryDictionary(new com.app.demo.model.entity.CountryDictionary());
        }
        out.getCountryDictionary().setName(inFootballTeamInsertOrUpdateDto.getCountryName());
      }
      if (inFootballTeamInsertOrUpdateDto.getCurrencyName() != null) {
        if (out.getCurrencyDictionary() == null) {
          out.setCurrencyDictionary(new com.app.demo.model.entity.CurrencyDictionary());
        }
        out.getCurrencyDictionary().setName(inFootballTeamInsertOrUpdateDto.getCurrencyName());
      }
    }
    return out;
  }


  @Override
  public final com.app.demo.dto.FootballTeamResponseDto asFootballTeamResponseDto(com.app.demo.model.entity.FootballTeam inFootballTeam) {
    com.app.demo.dto.FootballTeamResponseDto out = null;
    if (inFootballTeam != null) {
      out = new com.app.demo.dto.FootballTeamResponseDto();
      if (inFootballTeam.getFootballPlayerList() != null) {
        java.util.ArrayList<com.app.demo.dto.FootballPlayerResponseDto> afootballplayerlistTmpCollection = new java.util.ArrayList<com.app.demo.dto.FootballPlayerResponseDto>(inFootballTeam.getFootballPlayerList().size());
        for (com.app.demo.model.entity.FootballPlayer afootballplayerlistItem : inFootballTeam.getFootballPlayerList()) {
          afootballplayerlistTmpCollection.add(asFootballPlayerResponseDto(afootballplayerlistItem));
        }
        out.setFootballPlayerBaseDtoList(afootballplayerlistTmpCollection);
      }
      else {
        out.setFootballPlayerBaseDtoList(null);
      }
      out.setName(inFootballTeam.getName());
      out.setUFTI(inFootballTeam.getUniqueFootballTeamIdentifier());
      if (inFootballTeam.getCountryDictionary() != null) {
        out.setCountryName(inFootballTeam.getCountryDictionary().getName());
      }
      if (inFootballTeam.getCurrencyDictionary() != null) {
        out.setCurrencyName(inFootballTeam.getCurrencyDictionary().getName());
      }
    }
    return out;
  }


  @Override
  public final com.app.demo.dto.FootballPlayerHistoryResponseDto asFootballPlayerHistoryResponseDto(com.app.demo.model.entity.FootballPlayerFootballTeamHistory inFootballPlayerFootballTeamHistory) {
    com.app.demo.dto.FootballPlayerHistoryResponseDto out = null;
    if (inFootballPlayerFootballTeamHistory != null) {
      out = new com.app.demo.dto.FootballPlayerHistoryResponseDto();
      out.setExitDate(inFootballPlayerFootballTeamHistory.getExitDate());
      out.setId(inFootballPlayerFootballTeamHistory.getId());
      out.setJoinDate(inFootballPlayerFootballTeamHistory.getJoinDate());
      out.setTeamCommission(inFootballPlayerFootballTeamHistory.getTeamCommission());
      out.setTransferFee(inFootballPlayerFootballTeamHistory.getTransferFee());
      if (inFootballPlayerFootballTeamHistory.getFootballPlayer() != null) {
        out.setFootballPlayerName(inFootballPlayerFootballTeamHistory.getFootballPlayer().getName());
      }
      if (inFootballPlayerFootballTeamHistory.getFootballTeam() != null) {
        out.setFootballTeamName(inFootballPlayerFootballTeamHistory.getFootballTeam().getName());
      }
    }
    return out;
  }


  @Override
  public final com.app.demo.model.entity.FootballPlayerFootballTeamHistory asFootballPlayerFootballTeamHistory(com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto inFootballPlayerHistoryInsertOrUpdateDto) {
    com.app.demo.model.entity.FootballPlayerFootballTeamHistory out = null;
    if (inFootballPlayerHistoryInsertOrUpdateDto != null) {
      out = new com.app.demo.model.entity.FootballPlayerFootballTeamHistory();
      out.setExitDate(inFootballPlayerHistoryInsertOrUpdateDto.getExitDate());
      out.setId(inFootballPlayerHistoryInsertOrUpdateDto.getId());
      out.setIsActive(inFootballPlayerHistoryInsertOrUpdateDto.getIsActive());
      out.setJoinDate(inFootballPlayerHistoryInsertOrUpdateDto.getJoinDate());
      out.setTeamCommission(inFootballPlayerHistoryInsertOrUpdateDto.getTeamCommission());
      out.setTransferFee(inFootballPlayerHistoryInsertOrUpdateDto.getTransferFee());
      if (inFootballPlayerHistoryInsertOrUpdateDto.getUFPI() != null) {
        if (out.getFootballPlayer() == null) {
          out.setFootballPlayer(new com.app.demo.model.entity.FootballPlayer());
        }
        out.getFootballPlayer().setUniqueFootballPlayerIdentifier(inFootballPlayerHistoryInsertOrUpdateDto.getUFPI());
      }
      if (inFootballPlayerHistoryInsertOrUpdateDto.getUFTI() != null) {
        if (out.getFootballTeam() == null) {
          out.setFootballTeam(new com.app.demo.model.entity.FootballTeam());
        }
        out.getFootballTeam().setUniqueFootballTeamIdentifier(inFootballPlayerHistoryInsertOrUpdateDto.getUFTI());
      }
    }
    return out;
  }


  @Override
  public final com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto asFootballPlayerHistoryInsertOrUpdateDto(com.app.demo.dto.FootballPlayerTransferResponseDto inFootballPlayerTransferResponseDto) {
    com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto out = null;
    if (inFootballPlayerTransferResponseDto != null) {
      out = new com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto();
      out.setId(inFootballPlayerTransferResponseDto.getHistoryId());
      out.setJoinDate(inFootballPlayerTransferResponseDto.getStartDate());
      out.setTeamCommission(inFootballPlayerTransferResponseDto.getTeamCommission());
      out.setTransferFee(inFootballPlayerTransferResponseDto.getTransferFee());
      out.setUFPI(inFootballPlayerTransferResponseDto.getUFPI());
      out.setUFTI(inFootballPlayerTransferResponseDto.getUFTI());
    }
    return out;
  }


  @Override
  public final com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto asFootballPlayerHistoryInsertOrUpdateDto(com.app.demo.dto.FootballPlayerTransferRequestDto inFootballPlayerTransferRequestDto) {
    com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto out = null;
    if (inFootballPlayerTransferRequestDto != null) {
      out = new com.app.demo.dto.FootballPlayerHistoryInsertOrUpdateDto();
      out.setTeamCommission(inFootballPlayerTransferRequestDto.getTeamCommission());
      out.setTransferFee(inFootballPlayerTransferRequestDto.getTransferFee());
      out.setUFPI(inFootballPlayerTransferRequestDto.getUFPI());
      out.setUFTI(inFootballPlayerTransferRequestDto.getUFTI());
    }
    return out;
  }



  /**
   * Single constructor
   */
  public DtoMapperSelmaGeneratedClass() {
  }

}
