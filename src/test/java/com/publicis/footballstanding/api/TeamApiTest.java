package com.publicis.footballstanding.api;

import com.publicis.footballstanding.dto.CountryDto;
import com.publicis.footballstanding.dto.LeagueDto;
import com.publicis.footballstanding.dto.StandingDto;
import com.publicis.footballstanding.dto.TeamDto;
import com.publicis.footballstanding.service.FootballService;
import com.publicis.footballstanding.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamApi.class)
public class TeamApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    private List<TeamDto> teamList;

    @BeforeEach
    void setUp() {
        teamList = Arrays.asList(
                new TeamDto("1", "Team A"),
                new TeamDto("2", "Team B")
        );
    }

    @Test
    @DisplayName("Test to get teams for league")
    void testGetTeams() throws Exception {
        given(teamService.getTeams(anyString())).willReturn(teamList);

        mockMvc.perform(get("/api/v1/teams").param("leagueId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].team_key").value("1"))
                .andExpect(jsonPath("$[0].team_name").value("Team A"))
                .andExpect(jsonPath("$[1].team_key").value("2"))
                .andExpect(jsonPath("$[1].team_name").value("Team B"));
    }

}
