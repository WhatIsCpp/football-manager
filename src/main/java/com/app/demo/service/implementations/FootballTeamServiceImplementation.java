package com.app.demo.service.implementations;

import com.app.demo.dto.FootballTeamInsertOrUpdateDto;
import com.app.demo.dto.FootballTeamResponseDto;
import com.app.demo.mapper.DtoMapper;
import com.app.demo.model.entity.FootballTeam;
import com.app.demo.repository.CountryDictionaryRepository;
import com.app.demo.repository.CurrencyDictionaryRepository;
import com.app.demo.repository.FootballTeamRepository;
import com.app.demo.service.interfaces.FootballTeamService;
import com.app.demo.util.MyBeansUtils;
import com.app.demo.util.StaticsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import com.app.demo.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FootballTeamServiceImplementation implements FootballTeamService {

    private final FootballTeamRepository footballTeamRepository;
    private final CountryDictionaryRepository countryDictionaryRepository;
    private final CurrencyDictionaryRepository currencyDictionaryRepository;

    private final DtoMapper dtoMapper;
    private final MyBeansUtils myBeansUtils;

    @Override
    public void saveOrUpdate(FootballTeamInsertOrUpdateDto footballTeamInsertOrUpdateDto) {

        FootballTeam footballTeam = dtoMapper.asFootballTeam(footballTeamInsertOrUpdateDto);

        String countryName = footballTeamInsertOrUpdateDto.getCountryName();
        footballTeam.setCountryDictionary(countryDictionaryRepository.findByNameAndIsActive(countryName, Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Country with name: " + countryName + StaticsUtils.DOES_NOT_EXIST)));

        String currencyName = footballTeamInsertOrUpdateDto.getCurrencyName();
        footballTeam.setCurrencyDictionary(currencyDictionaryRepository.findByNameAndIsActive(currencyName, Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Currency with name: " + currencyName + StaticsUtils.DOES_NOT_EXIST)));

        FootballTeam footballTeamFromDb = footballTeamRepository
                .findByUniqueFootballTeamIdentifierAndIsActive(footballTeam.getUniqueFootballTeamIdentifier(), Boolean.TRUE)
                .orElse(new FootballTeam());
        myBeansUtils.copyNonNullProperties(footballTeamFromDb, footballTeam);
        footballTeamRepository.save(footballTeamFromDb);
        log.info("{} team has been successfully saved to database", footballTeamFromDb.getName());
    }

    @Override
    public void deleteByUniqueFootballTeamIdentifier(String uFTI) {

        if (footballTeamRepository.existsByUniqueFootballTeamIdentifierAndIsActive(uFTI, Boolean.TRUE)) {

            footballTeamRepository.deleteByUniqueFootballTeamIdentifier(uFTI);
            log.info("Team with uFTI:{} has been successfully deleted from database", uFTI);
        } else
            throw new EntityNotFoundException("Football team with uFTI : " + uFTI + StaticsUtils.DOES_NOT_EXIST);
    }

    @Override
    public List<FootballTeamResponseDto> getAll(Pageable pageable) {

        return dtoMapper.asFootballTeamResponseDto(footballTeamRepository.findAll(pageable).getContent());
    }
}
