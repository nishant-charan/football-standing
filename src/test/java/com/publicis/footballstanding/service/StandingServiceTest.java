package com.publicis.footballstanding.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.footballstanding.dto.StandingDto;
import com.publicis.footballstanding.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.publicis.footballstanding.constant.Constants.GET_STANDINGS_ACTION;
import static com.publicis.footballstanding.constant.Constants.LEAGUE_ID_PARAM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StandingServiceTest {

    private StandingService standingService;

    @Mock
    private FootballService footballService;

    @BeforeEach
    void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        standingService = new StandingService(footballService, objectMapper);
    }

    @Test
    @DisplayName("Test for getting list of standings for a given league")
    public void shouldReturnStandingsForLeague() {
        String leagueId = "149";
        List<StandingDto> standingDtoList = new ArrayList<>();
        standingDtoList.add(new StandingDto("149", "Non League Premier", "2827", "Hastings United", "1"));
        standingDtoList.add(new StandingDto("149", "Non League Premier", "3451", "Lewes", "2"));
        standingDtoList.add(new StandingDto("149", "Non League Premier", "2986", "Billericay Town", "3"));


        when(footballService.getDataFromService(GET_STANDINGS_ACTION, LEAGUE_ID_PARAM, leagueId)).thenReturn("""
                                [
                                    {
                                        "country_name": "England",
                                        "league_id": "149",
                                        "league_name": "Non League Premier",
                                        "team_id": "2827",
                                        "team_name": "Hastings United",
                                        "overall_promotion": "Promotion",
                                        "overall_league_position": "1",
                                        "overall_league_payed": "2",
                                        "overall_league_W": "2",
                                        "overall_league_D": "0",
                                        "overall_league_L": "0",
                                        "overall_league_GF": "3",
                                        "overall_league_GA": "0",
                                        "overall_league_PTS": "6",
                                        "home_league_position": "3",
                                        "home_promotion": "",
                                        "home_league_payed": "1",
                                        "home_league_W": "1",
                                        "home_league_D": "0",
                                        "home_league_L": "0",
                                        "home_league_GF": "2",
                                        "home_league_GA": "0",
                                        "home_league_PTS": "3",
                                        "away_league_position": "6",
                                        "away_promotion": "",
                                        "away_league_payed": "1",
                                        "away_league_W": "1",
                                        "away_league_D": "0",
                                        "away_league_L": "0",
                                        "away_league_GF": "1",
                                        "away_league_GA": "0",
                                        "away_league_PTS": "3",
                                        "league_round": "",
                                        "team_badge": "https://apiv3.apifootball.com/badges/2827_hastings-united.jpg",
                                        "fk_stage_key": "958",
                                        "stage_name": "Isthmian"
                                    },
                                    {
                                        "country_name": "England",
                                        "league_id": "149",
                                        "league_name": "Non League Premier",
                                        "team_id": "3451",
                                        "team_name": "Lewes",
                                        "overall_promotion": "Promotion Play-off",
                                        "overall_league_position": "2",
                                        "overall_league_payed": "2",
                                        "overall_league_W": "2",
                                        "overall_league_D": "0",
                                        "overall_league_L": "0",
                                        "overall_league_GF": "3",
                                        "overall_league_GA": "0",
                                        "overall_league_PTS": "6",
                                        "home_league_position": "2",
                                        "home_promotion": "",
                                        "home_league_payed": "1",
                                        "home_league_W": "1",
                                        "home_league_D": "0",
                                        "home_league_L": "0",
                                        "home_league_GF": "2",
                                        "home_league_GA": "0",
                                        "home_league_PTS": "3",
                                        "away_league_position": "4",
                                        "away_promotion": "",
                                        "away_league_payed": "1",
                                        "away_league_W": "1",
                                        "away_league_D": "0",
                                        "away_league_L": "0",
                                        "away_league_GF": "1",
                                        "away_league_GA": "0",
                                        "away_league_PTS": "3",
                                        "league_round": "",
                                        "team_badge": "https://apiv3.apifootball.com/badges/3451_lewes.jpg",
                                        "fk_stage_key": "958",
                                        "stage_name": "Isthmian"
                                    },
                                    {
                                        "country_name": "England",
                                        "league_id": "149",
                                        "league_name": "Non League Premier",
                                        "team_id": "2986",
                                        "team_name": "Billericay Town",
                                        "overall_promotion": "Promotion Play-off",
                                        "overall_league_position": "3",
                                        "overall_league_payed": "2",
                                        "overall_league_W": "1",
                                        "overall_league_D": "1",
                                        "overall_league_L": "0",
                                        "overall_league_GF": "4",
                                        "overall_league_GA": "1",
                                        "overall_league_PTS": "4",
                                        "home_league_position": "1",
                                        "home_promotion": "",
                                        "home_league_payed": "1",
                                        "home_league_W": "1",
                                        "home_league_D": "0",
                                        "home_league_L": "0",
                                        "home_league_GF": "3",
                                        "home_league_GA": "0",
                                        "home_league_PTS": "3",
                                        "away_league_position": "10",
                                        "away_promotion": "",
                                        "away_league_payed": "1",
                                        "away_league_W": "0",
                                        "away_league_D": "1",
                                        "away_league_L": "0",
                                        "away_league_GF": "1",
                                        "away_league_GA": "1",
                                        "away_league_PTS": "1",
                                        "league_round": "",
                                        "team_badge": "https://apiv3.apifootball.com/badges/2986_billericay-town.jpg",
                                        "fk_stage_key": "958",
                                        "stage_name": "Isthmian"
                                    }
                                ]""");
        List<StandingDto> standings = standingService.getStandings(leagueId);
        assertEquals(standingDtoList, standings);
    }

    @Test
    @DisplayName("Test for throwing exception when there is no standing for a given league")
    public void shouldThrowExceptionForNoStandingsForLeague() {
        String leagueId = "150";
        when(footballService.getDataFromService(GET_STANDINGS_ACTION, LEAGUE_ID_PARAM, leagueId)).thenReturn("""
                                {
                                    "error": 404,
                                    "message": "No teams found (please check your plan)!!"
                                }""");
        assertThrows(ServiceException.class, () -> standingService.getStandings(leagueId));
    }

    @Test
    @DisplayName("Test for getting blank list of standings for a given leagueId")
    public void shouldReturnEmptyTeams() {
        String leagueId = "160";
        List<StandingDto> standingDtoList = new ArrayList<>();

        when(footballService.getDataFromService(GET_STANDINGS_ACTION, LEAGUE_ID_PARAM, leagueId)).thenReturn("[]");
        List<StandingDto> standings = standingService.getStandings(leagueId);
        assertEquals(standingDtoList, standings);
    }
}
