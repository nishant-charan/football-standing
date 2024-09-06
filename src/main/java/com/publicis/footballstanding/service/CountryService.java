package com.publicis.footballstanding.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.footballstanding.dto.CountryDto;
import com.publicis.footballstanding.dto.ErrorResponseDto;
import com.publicis.footballstanding.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.publicis.footballstanding.constant.Constants.ERROR;
import static com.publicis.footballstanding.constant.Constants.GET_COUNTRIES_ACTION;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final FootballService footballService;

    private final ObjectMapper objectMapper;

    public List<CountryDto> getCountries() {
        List<CountryDto> countries;
        String response = footballService.getDataFromService(GET_COUNTRIES_ACTION, null, null);
        try {
            if (response.contains(ERROR)) {
                ErrorResponseDto errorResponseDto = objectMapper.readValue(response, ErrorResponseDto.class);
                throw new ServiceException(errorResponseDto.getMessage());
            } else {
                countries = Arrays.asList(objectMapper.readValue(response, CountryDto[].class));
            }
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Something Went Wrong!!!");
        }
        return countries;
    }
}
