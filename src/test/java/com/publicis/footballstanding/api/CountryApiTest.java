package com.publicis.footballstanding.api;

import com.publicis.footballstanding.dto.CountryDto;
import com.publicis.footballstanding.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryApi.class)
public class CountryApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    private List<CountryDto> countryList;

    @BeforeEach
    void setUp() {
        countryList = Arrays.asList(
                new CountryDto("1", "Country A"),
                new CountryDto("2", "Country B")
        );
    }

    @Test
    @DisplayName("Test to get countries")
    void testGetCountries() throws Exception {
        given(countryService.getCountries()).willReturn(countryList);

        mockMvc.perform(get("/api/v1/countries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].country_id").value("1"))
                .andExpect(jsonPath("$[0].country_name").value("Country A"))
                .andExpect(jsonPath("$[1].country_id").value("2"))
                .andExpect(jsonPath("$[1].country_name").value("Country B"));
    }
}
