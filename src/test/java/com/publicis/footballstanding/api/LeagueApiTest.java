package com.publicis.footballstanding.api;

import com.publicis.footballstanding.dto.CountryDto;
import com.publicis.footballstanding.dto.LeagueDto;
import com.publicis.footballstanding.exception.ServiceException;
import com.publicis.footballstanding.service.CountryService;
import com.publicis.footballstanding.service.LeagueService;
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

@WebMvcTest(LeagueApi.class)
public class LeagueApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeagueService leagueService;

    private List<LeagueDto> leagueList;

    @BeforeEach
    void setUp() {
        leagueList = Arrays.asList(
                new LeagueDto("1", "Country A", "1", "League A"),
                new LeagueDto("1", "Country A", "2", "League B")
        );
    }

    @Test
    @DisplayName("Test to get leagues for country")
    void testGetLeagues() throws Exception {
        given(leagueService.getLeagues(anyString())).willReturn(leagueList);

        mockMvc.perform(get("/api/v1/leagues").param("countryId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].country_id").value("1"))
                .andExpect(jsonPath("$[0].country_name").value("Country A"))
                .andExpect(jsonPath("$[0].league_id").value("1"))
                .andExpect(jsonPath("$[0].league_name").value("League A"))
                .andExpect(jsonPath("$[1].country_id").value("1"))
                .andExpect(jsonPath("$[1].country_name").value("Country A"))
                .andExpect(jsonPath("$[1].league_id").value("2"))
                .andExpect(jsonPath("$[1].league_name").value("League B"));
    }

    @Test
    @DisplayName("Test to check exception when no leagues for country")
    void testGetLeaguesWhenNoLeagues() throws Exception {
        given(leagueService.getLeagues(anyString())).willThrow(new ServiceException("No leagues found!"));

        mockMvc.perform(get("/api/v1/leagues").param("countryId", "2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("404"))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("No leagues found!"));
    }
}
