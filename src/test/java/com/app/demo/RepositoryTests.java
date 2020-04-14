package com.app.demo;

import com.app.demo.model.entity.CountryDictionary;
import com.app.demo.model.entity.CurrencyDictionary;
import com.app.demo.model.entity.FootballPlayer;
import com.app.demo.model.entity.FootballPlayerFootballTeamHistory;
import com.app.demo.model.entity.FootballTeam;
import com.app.demo.repository.CountryDictionaryRepository;
import com.app.demo.repository.CurrencyDictionaryRepository;
import com.app.demo.repository.FootballPlayerFootballTeamHistoryRepository;
import com.app.demo.repository.FootballPlayerRepository;
import com.app.demo.repository.FootballTeamRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CountryDictionaryRepository countryDictionaryRepository;

    @Autowired
    private CurrencyDictionaryRepository currencyDictionaryRepository;

    @Autowired
    private FootballPlayerFootballTeamHistoryRepository footballPlayerFootballTeamHistoryRepository;

    @Autowired
    private FootballPlayerRepository footballPlayerRepository;

    @Autowired
    private FootballTeamRepository footballTeamRepository;

    private static final String TEST_NAME = "testName";
    private static final String TEST_SURNAME = "testSurnname";
    private static final String TEST_UFPI = "1";
    private static final String TEST_UFTI = "1";

    private static final LocalDate TEST_JOIN_DATE = LocalDate.of(2010, 12, 10);
    private static final LocalDate TEST_EXIT_DATE = LocalDate.of(2020, 12, 10);
    private static final LocalDate TEST_BEETWEEN_DATE = LocalDate.of(2011, 12, 10);

    private static final Boolean TEST_BOOLEAN_TRUE = Boolean.TRUE;

    private static final Pageable UNPAGED_PAGEABLE = Pageable.unpaged();

    @Test
    public void whenFindByNameAndIsActive_thenReturnCountryDictionary() {

        CountryDictionary countryDictionary = new CountryDictionary();
        countryDictionary.setName(TEST_NAME);
        countryDictionary.setIsActive(TEST_BOOLEAN_TRUE);

        testEntityManager.persist(countryDictionary);
        testEntityManager.flush();

        Optional<CountryDictionary> optionalCountryDictionary = countryDictionaryRepository.findByNameAndIsActive(TEST_NAME, TEST_BOOLEAN_TRUE);

        Assert.assertTrue(optionalCountryDictionary.isPresent());
        Assert.assertEquals(TEST_NAME, optionalCountryDictionary.get().getName());
    }

    @Test
    public void whenFindByNameAndIsActive_thenReturnCurrencyDictionary() {

        CurrencyDictionary currencyDictionary = new CurrencyDictionary();
        currencyDictionary.setName(TEST_NAME);
        currencyDictionary.setIsActive(TEST_BOOLEAN_TRUE);

        testEntityManager.persist(currencyDictionary);
        testEntityManager.flush();

        Optional<CurrencyDictionary> optionalCurrencyDictionary = currencyDictionaryRepository.findByNameAndIsActive(TEST_NAME, TEST_BOOLEAN_TRUE);

        Assert.assertTrue(optionalCurrencyDictionary.isPresent());
        Assert.assertEquals(TEST_NAME, optionalCurrencyDictionary.get().getName());
    }

    @Test
    public void whenFindById_thenReturnFootballPlayerFootballTeamHistory() {

        FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory = new FootballPlayerFootballTeamHistory();
        footballPlayerFootballTeamHistory.setJoinDate(TEST_JOIN_DATE);

        FootballPlayerFootballTeamHistory persistFPFTH = testEntityManager.persist(footballPlayerFootballTeamHistory);
        testEntityManager.flush();

        Optional<FootballPlayerFootballTeamHistory> optionalFootballPlayerFootballTeamHistory = footballPlayerFootballTeamHistoryRepository.findById(persistFPFTH.getId());

        Assert.assertTrue(optionalFootballPlayerFootballTeamHistory.isPresent());
        Assert.assertEquals(persistFPFTH.getId(), optionalFootballPlayerFootballTeamHistory.get().getId());
    }

    @Test
    public void whenFindByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate_thenReturnFootballPlayerFootballTeamHistory() {

        FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory = new FootballPlayerFootballTeamHistory();
        FootballPlayer footballPlayer = new FootballPlayer();

        footballPlayer.setUniqueFootballPlayerIdentifier(TEST_UFPI);

        testEntityManager.persist(footballPlayer);
        testEntityManager.flush();

        footballPlayerFootballTeamHistory.setFootballPlayer(footballPlayer);
        footballPlayerFootballTeamHistory.setJoinDate(TEST_JOIN_DATE);
        footballPlayerFootballTeamHistory.setExitDate(TEST_EXIT_DATE);

        testEntityManager.persist(footballPlayerFootballTeamHistory);
        testEntityManager.flush();

        Optional<FootballPlayerFootballTeamHistory> optionalFootballPlayerFootballTeamHistory = footballPlayerFootballTeamHistoryRepository.findByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate(TEST_UFPI, TEST_EXIT_DATE);

        Assert.assertTrue(optionalFootballPlayerFootballTeamHistory.isPresent());
        Assert.assertEquals(TEST_EXIT_DATE, optionalFootballPlayerFootballTeamHistory.get().getExitDate());
    }

    @Test
    public void whenExistsByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate_thenReturnFootballPlayerFootballTeamHistory() {

        FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory = new FootballPlayerFootballTeamHistory();
        FootballPlayer footballPlayer = new FootballPlayer();

        footballPlayer.setUniqueFootballPlayerIdentifier(TEST_UFPI);

        testEntityManager.persist(footballPlayer);
        testEntityManager.flush();

        footballPlayerFootballTeamHistory.setFootballPlayer(footballPlayer);
        footballPlayerFootballTeamHistory.setJoinDate(TEST_JOIN_DATE);
        footballPlayerFootballTeamHistory.setExitDate(TEST_EXIT_DATE);

        testEntityManager.persist(footballPlayerFootballTeamHistory);
        testEntityManager.flush();

        boolean aBoolean = footballPlayerFootballTeamHistoryRepository.existsByFootballPlayer_UniqueFootballPlayerIdentifierAndExitDate(TEST_UFPI, TEST_EXIT_DATE);

        Assert.assertTrue(aBoolean);
    }

    @Test
    public void whenFindAllByFootballPlayer_UniqueFootballPlayerIdentifier_thenReturnFootballPlayerFootballTeamHistory() {

        FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory = new FootballPlayerFootballTeamHistory();
        FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory1 = new FootballPlayerFootballTeamHistory();

        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setUniqueFootballPlayerIdentifier(TEST_UFPI);

        testEntityManager.persist(footballPlayer);
        testEntityManager.flush();

        footballPlayerFootballTeamHistory.setFootballPlayer(footballPlayer);
        footballPlayerFootballTeamHistory.setJoinDate(TEST_JOIN_DATE);
        footballPlayerFootballTeamHistory.setExitDate(TEST_EXIT_DATE);

        footballPlayerFootballTeamHistory1.setFootballPlayer(footballPlayer);
        footballPlayerFootballTeamHistory1.setJoinDate(TEST_JOIN_DATE);
        footballPlayerFootballTeamHistory1.setExitDate(TEST_EXIT_DATE);

        testEntityManager.persist(footballPlayerFootballTeamHistory);
        testEntityManager.flush();
        testEntityManager.persist(footballPlayerFootballTeamHistory1);
        testEntityManager.flush();

        List<FootballPlayerFootballTeamHistory> footballPlayerFootballTeamHistoryList = footballPlayerFootballTeamHistoryRepository.findAllByFootballPlayer_UniqueFootballPlayerIdentifier(TEST_UFPI);
        Assert.assertFalse(footballPlayerFootballTeamHistoryList.isEmpty());
    }

    @Test
    public void whenFindAllByFootballTeam_UniqueFootballTeamIdentifierAndJoinDateIsBeforeAndExitDateIsAfter_thenReturnFootballPlayerFootballTeamHistory() {

        FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory = new FootballPlayerFootballTeamHistory();
        FootballPlayerFootballTeamHistory footballPlayerFootballTeamHistory1 = new FootballPlayerFootballTeamHistory();

        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setUniqueFootballTeamIdentifier(TEST_UFPI);

        FootballTeam persist = testEntityManager.persist(footballTeam);
        testEntityManager.flush();

        footballPlayerFootballTeamHistory.setFootballTeam(persist);
        footballPlayerFootballTeamHistory.setJoinDate(TEST_JOIN_DATE);
        footballPlayerFootballTeamHistory.setExitDate(TEST_EXIT_DATE);

        footballPlayerFootballTeamHistory1.setFootballTeam(persist);
        footballPlayerFootballTeamHistory1.setJoinDate(TEST_JOIN_DATE);
        footballPlayerFootballTeamHistory1.setExitDate(TEST_EXIT_DATE);

        testEntityManager.persist(footballPlayerFootballTeamHistory);
        testEntityManager.flush();
        testEntityManager.persist(footballPlayerFootballTeamHistory1);
        testEntityManager.flush();

        List<FootballPlayerFootballTeamHistory> footballPlayerFootballTeamHistoryList =
                footballPlayerFootballTeamHistoryRepository.findAllByFootballTeam_UniqueFootballTeamIdentifierAndJoinDateIsBeforeAndExitDateIsAfter(TEST_UFPI, TEST_BEETWEEN_DATE, TEST_BEETWEEN_DATE);
        Assert.assertFalse(footballPlayerFootballTeamHistoryList.isEmpty());
    }

    @Test
    public void whenFindByUniqueFootballPlayerIdentifierAndIsActive_thenReturnFootballPlayer() {

        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setUniqueFootballPlayerIdentifier(TEST_UFPI);
        footballPlayer.setIsActive(TEST_BOOLEAN_TRUE);

        FootballPlayer persist = testEntityManager.persist(footballPlayer);
        testEntityManager.flush();

        Optional<FootballPlayer> optionalFootballPlayer = footballPlayerRepository.findByUniqueFootballPlayerIdentifierAndIsActive(TEST_UFPI, TEST_BOOLEAN_TRUE);


        Assert.assertTrue(optionalFootballPlayer.isPresent());
        Assert.assertEquals(persist.getId(), optionalFootballPlayer.get().getId());
    }

    @Test
    public void whenExistByUniqueFootballPlayerIdentifierAndIsActive_thenReturnFootballPlayer() {

        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setUniqueFootballPlayerIdentifier(TEST_UFPI);
        footballPlayer.setIsActive(TEST_BOOLEAN_TRUE);

        FootballPlayer persist = testEntityManager.persist(footballPlayer);
        testEntityManager.flush();

        boolean aBoolean = footballPlayerRepository.existsByUniqueFootballPlayerIdentifierAndIsActive(TEST_UFPI, TEST_BOOLEAN_TRUE);


        Assert.assertTrue(aBoolean);
    }

    @Test
    public void whenDeleteByUniqueFootballPlayerIdentifier_thenReturnFootballPlayer() {

        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setUniqueFootballPlayerIdentifier(TEST_UFPI);
        footballPlayer.setIsActive(Boolean.TRUE);

        FootballPlayer persist = testEntityManager.persist(footballPlayer);
        testEntityManager.flush();

        footballPlayerRepository.deleteByUniqueFootballPlayerIdentifier(TEST_UFPI);
        Optional<FootballPlayer> optionalFootballPlayer = footballPlayerRepository.findByUniqueFootballPlayerIdentifierAndIsActive(TEST_UFPI, TEST_BOOLEAN_TRUE);

        Assert.assertFalse(optionalFootballPlayer.isPresent());
    }

    @Test
    public void whenFindByNameAndSurname_thenReturnFootballPlayer() {

        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setName(TEST_NAME);
        footballPlayer.setSurname(TEST_SURNAME);

        testEntityManager.persist(footballPlayer);
        testEntityManager.flush();

        FootballPlayer footballPlayer1 = new FootballPlayer();
        footballPlayer1.setName(TEST_NAME);
        footballPlayer1.setSurname(TEST_SURNAME);

        testEntityManager.persist(footballPlayer1);
        testEntityManager.flush();

        List<FootballPlayer> footballPlayerList = footballPlayerRepository.findByNameAndSurname(TEST_NAME, TEST_SURNAME, UNPAGED_PAGEABLE);

        Assert.assertFalse(footballPlayerList.isEmpty());
    }

    @Test
    public void whenFindAllByIsActive_thenReturnFootballPlayer() {

        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setName(TEST_NAME);
        footballPlayer.setSurname(TEST_SURNAME);
        footballPlayer.setIsActive(TEST_BOOLEAN_TRUE);

        testEntityManager.persist(footballPlayer);
        testEntityManager.flush();

        FootballPlayer footballPlayer1 = new FootballPlayer();
        footballPlayer1.setName(TEST_NAME);
        footballPlayer1.setSurname(TEST_SURNAME);
        footballPlayer1.setIsActive(TEST_BOOLEAN_TRUE);

        testEntityManager.persist(footballPlayer1);
        testEntityManager.flush();

        List<FootballPlayer> footballPlayerList = footballPlayerRepository.findAllByIsActive(TEST_BOOLEAN_TRUE, UNPAGED_PAGEABLE);

        Assert.assertFalse(footballPlayerList.isEmpty());
    }

    @Test
    public void whenFindByUniqueFootballTeamIdentifierAndIsActive_thenReturnFootballTeam() {

        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setUniqueFootballTeamIdentifier(TEST_UFTI);
        footballTeam.setIsActive(TEST_BOOLEAN_TRUE);

        testEntityManager.persist(footballTeam);
        testEntityManager.flush();

        Optional<FootballTeam> optionalFootballTeam = footballTeamRepository.findByUniqueFootballTeamIdentifierAndIsActive(TEST_UFTI, TEST_BOOLEAN_TRUE);

        Assert.assertTrue(optionalFootballTeam.isPresent());
        Assert.assertEquals(TEST_UFTI, optionalFootballTeam.get().getUniqueFootballTeamIdentifier());
    }

    @Test
    public void whenExistsByUniqueFootballTeamIdentifierAndIsActive_thenReturnFootballTeam() {

        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setUniqueFootballTeamIdentifier(TEST_UFTI);
        footballTeam.setIsActive(TEST_BOOLEAN_TRUE);

        testEntityManager.persist(footballTeam);
        testEntityManager.flush();

        boolean aBoolean = footballTeamRepository.existsByUniqueFootballTeamIdentifierAndIsActive(TEST_UFTI, TEST_BOOLEAN_TRUE);

        Assert.assertTrue(aBoolean);
    }

    @Test
    public void whenDeleteByUniqueFootballTeamIdentifier_thenReturnFootballTeam() {

        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setUniqueFootballTeamIdentifier(TEST_UFTI);
        footballTeam.setIsActive(TEST_BOOLEAN_TRUE);

        FootballTeam persist = testEntityManager.persist(footballTeam);
        testEntityManager.flush();

        footballTeamRepository.deleteByUniqueFootballTeamIdentifier(TEST_UFTI);
        Optional<FootballTeam> optionalFootballTeam = footballTeamRepository.findByUniqueFootballTeamIdentifierAndIsActive(TEST_UFTI, TEST_BOOLEAN_TRUE);

        Assert.assertFalse(optionalFootballTeam.isPresent());
    }

}
