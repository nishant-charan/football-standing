package com.publicis.footballstanding.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.footballstanding.dto.LeagueDto;
import com.publicis.footballstanding.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.publicis.footballstanding.constant.Constants.COUNTRY_ID_PARAM;
import static com.publicis.footballstanding.constant.Constants.GET_LEAGUES_ACTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LeagueServiceTest {

    @InjectMocks
    private LeagueService leagueService;

    @Mock
    private FootballService footballService;


    @BeforeEach
    void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        leagueService = new LeagueService(footballService, objectMapper);
    }

    @Test
    @DisplayName("Test for getting list of leagues for a given countryId")
    public void shouldReturnLeagues() {
        String countryId = "33";
        List<LeagueDto> leagueDtoList = new ArrayList<>();
        leagueDtoList.add(new LeagueDto("44", "England", "149", "Non League Premier"));

        when(footballService.getDataFromService(GET_LEAGUES_ACTION, COUNTRY_ID_PARAM, countryId)).thenReturn("""
                                [
                                    {
                                        "country_id": "44",
                                        "country_name": "England",
                                        "league_id": "149",
                                        "league_name": "Non League Premier",
                                        "league_season": "2024/2025",
                                        "league_logo": "https://apiv3.apifootball.com/badges/logo_leagues/149_non-league-premier.png",
                                        "country_logo": "https://apiv3.apifootball.com/badges/logo_country/44_england.png"
                                    }
                                ]""");
        List<LeagueDto> leagues = leagueService.getLeagues(countryId);
        assertEquals(leagueDtoList, leagues);
    }

    @Test
    @DisplayName("Test for throwing exception when there is no league for a given country")
    public void shouldThrowExceptionForNoLeaguesForCountry() {
        String countryId = "34";
        when(footballService.getDataFromService(GET_LEAGUES_ACTION, COUNTRY_ID_PARAM, countryId)).thenReturn("""
                                {
                                    "error": 404,
                                    "message": "No league found (please check your plan)!!"
                                }""");
        assertThrows(ServiceException.class, () -> leagueService.getLeagues(countryId));
    }

    @Test
    @DisplayName("Test for getting blank list of leagues for a given countryId")
    public void shouldReturnEmptyLeagues() {
        String countryId = "36";
        List<LeagueDto> leagueDtoList = new ArrayList<>();

        when(footballService.getDataFromService(GET_LEAGUES_ACTION, COUNTRY_ID_PARAM, countryId)).thenReturn("[]");
        List<LeagueDto> leagues = leagueService.getLeagues(countryId);
        assertEquals(leagueDtoList, leagues);
    }
}
