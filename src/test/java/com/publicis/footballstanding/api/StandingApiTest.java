package com.publicis.footballstanding.api;

import com.publicis.footballstanding.dto.StandingDto;
import com.publicis.footballstanding.service.StandingService;
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

@WebMvcTest(StandingApi.class)
public class StandingApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StandingService standingService;

    private List<StandingDto> standingList;

    @BeforeEach
    void setUp() {
        standingList = Arrays.asList(
                new StandingDto("1", "League A", "1", "Team A", "1"),
                new StandingDto("1", "League A", "2", "Team B", "2")
        );
    }

    @Test
    @DisplayName("Test to get standings for league")
    void testGetStandings() throws Exception {
        given(standingService.getStandings(anyString())).willReturn(standingList);

        mockMvc.perform(get("/api/v1/standings").param("leagueId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].league_id").value("1"))
                .andExpect(jsonPath("$[0].league_name").value("League A"))
                .andExpect(jsonPath("$[0].team_id").value("1"))
                .andExpect(jsonPath("$[0].team_name").value("Team A"))
                .andExpect(jsonPath("$[0].overall_league_position").value("1"))
                .andExpect(jsonPath("$[1].league_id").value("1"))
                .andExpect(jsonPath("$[1].league_name").value("League A"))
                .andExpect(jsonPath("$[1].team_id").value("2"))
                .andExpect(jsonPath("$[1].team_name").value("Team B"))
                .andExpect(jsonPath("$[1].overall_league_position").value("2"));
    }
}
