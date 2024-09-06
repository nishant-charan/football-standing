package com.publicis.footballstanding.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.footballstanding.dto.CountryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.publicis.footballstanding.constant.Constants.GET_COUNTRIES_ACTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CountryServiceTest {

    private CountryService countryService;

    @Mock
    private FootballService footballService;

    @BeforeEach
    void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        countryService = new CountryService(footballService, objectMapper);
    }

    @Test
    @DisplayName("Test for getting list of countries")
    public void shouldReturnCountries() {
        List<CountryDto> countryDtoList = new ArrayList<>();
        countryDtoList.add(new CountryDto("44", "England"));
        countryDtoList.add(new CountryDto("3", "France"));

        when(footballService.getDataFromService(GET_COUNTRIES_ACTION, null, null)).thenReturn("""
                                [
                                    {
                                        "country_id": "44",
                                        "country_name": "England",
                                        "country_logo": "https://apiv3.apifootball.com/badges/logo_country/44_england.png"
                                    },
                                    {
                                        "country_id": "3",
                                        "country_name": "France",
                                        "country_logo": "https://apiv3.apifootball.com/badges/logo_country/3_france.png"
                                    }
                                ]""");
        List<CountryDto> countries = countryService.getCountries();
        assertEquals(countryDtoList, countries);
    }

    @Test
    @DisplayName("Test for getting blank list of countries")
    public void shouldReturnBlankCountries() {
        List<CountryDto> countryDtoList = new ArrayList<>();

        when(footballService.getDataFromService(GET_COUNTRIES_ACTION, null, null)).thenReturn("[]");
        List<CountryDto> countries = countryService.getCountries();
        assertEquals(countryDtoList, countries);
    }

}
