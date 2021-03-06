package com.app.demo.service.interfaces;

import com.app.demo.model.dto.FootballTeamInsertOrUpdateDto;
import com.app.demo.model.dto.FootballTeamResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FootballTeamService {

    void saveOrUpdate(FootballTeamInsertOrUpdateDto footballTeamInsertOrUpdateDto);

    void deleteByUniqueFootballTeamIdentifier(String uFTI);

    List<FootballTeamResponseDto> getAll(Pageable pageable);
}
