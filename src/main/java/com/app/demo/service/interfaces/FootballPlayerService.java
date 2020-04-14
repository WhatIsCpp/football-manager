package com.app.demo.service.interfaces;

import com.app.demo.model.dto.FootballPlayerInsertOrUpdateDto;
import com.app.demo.model.dto.FootballPlayerResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FootballPlayerService {

    void saveOrUpdate(FootballPlayerInsertOrUpdateDto footballPlayerInsertOrUpdateDto);

    void deleteByUniqueFootballPlayerIdentifier(String uFPI);

    List<FootballPlayerResponseDto> getAll(Pageable pageable);
}
