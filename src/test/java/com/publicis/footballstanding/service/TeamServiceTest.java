package com.publicis.footballstanding.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.footballstanding.dto.TeamDto;
import com.publicis.footballstanding.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.publicis.footballstanding.constant.Constants.GET_TEAMS_ACTION;
import static com.publicis.footballstanding.constant.Constants.LEAGUE_ID_PARAM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeamServiceTest {

    private TeamService teamService;

    @Mock
    private FootballService footballService;

    @BeforeEach
    void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        teamService = new TeamService(footballService, objectMapper);
    }

    @Test
    @DisplayName("Test for getting list of teams for a given league")
    public void shouldReturnTeamsForLeague() {
        String leagueId = "145";
        List<TeamDto> teamDtoList = new ArrayList<>();
        teamDtoList.add(new TeamDto("2788", "AFC Sudbury"));
        teamDtoList.add(new TeamDto("2801", "Canvey Island"));
        teamDtoList.add(new TeamDto("2811", "Bracknell Town"));

        when(footballService.getDataFromService(GET_TEAMS_ACTION, LEAGUE_ID_PARAM, leagueId)).thenReturn("""
                                [
                                    {
                                        "team_key": "2788",
                                        "team_name": "AFC Sudbury",
                                        "team_country": "England",
                                        "team_founded": "1999",
                                        "team_badge": "https://apiv3.apifootball.com/badges/2788_afc-sudbury.jpg",
                                        "venue": {
                                            "venue_name": "MEL Group Stadium",
                                            "venue_address": "Brundon Lane",
                                            "venue_city": "Sudbury, Suffolk",
                                            "venue_capacity": "2500",
                                            "venue_surface": "grass"
                                        }
                                    },
                                    {
                                        "team_key": "2801",
                                        "team_name": "Canvey Island",
                                        "team_country": "England",
                                        "team_founded": "1926",
                                        "team_badge": "https://apiv3.apifootball.com/badges/2801_canvey-island.jpg",
                                        "venue": {
                                            "venue_name": "The Frost Hire Stadium",
                                            "venue_address": "Park Lane",
                                            "venue_city": "Canvey Island, Essex",
                                            "venue_capacity": "4308",
                                            "venue_surface": "grass"
                                        }
                                    },
                                    {
                                        "team_key": "2811",
                                        "team_name": "Bracknell Town",
                                        "team_country": "England",
                                        "team_founded": "",
                                        "team_badge": "https://apiv3.apifootball.com/badges/2811_bracknell-town.jpg",
                                        "venue": {
                                            "venue_name": "Larges Lane",
                                            "venue_address": "Larges Lane",
                                            "venue_city": "Bracknell, Berkshire",
                                            "venue_capacity": "2500",
                                            "venue_surface": "grass"
                                        }
                                    }
                                ]""");
        List<TeamDto> teams = teamService.getTeams(leagueId);
        assertEquals(teamDtoList, teams);
    }

    @Test
    @DisplayName("Test for throwing exception when there is no team for a given league")
    public void shouldThrowExceptionForNoTeamsForLeague() {
        String leagueId = "150";
        when(footballService.getDataFromService(GET_TEAMS_ACTION, LEAGUE_ID_PARAM, leagueId)).thenReturn("""
                                {
                                    "error": 404,
                                    "message": "No teams found (please check your plan)!!"
                                }""");
        assertThrows(ServiceException.class, () -> teamService.getTeams(leagueId));
    }

    @Test
    @DisplayName("Test for getting blank list of teams for a given leagueId")
    public void shouldReturnEmptyTeams() {
        String leagueId = "160";
        List<TeamDto> teamDtoList = new ArrayList<>();

        when(footballService.getDataFromService(GET_TEAMS_ACTION, LEAGUE_ID_PARAM, leagueId)).thenReturn("[]");
        List<TeamDto> teams = teamService.getTeams(leagueId);
        assertEquals(teamDtoList, teams);
    }
}
