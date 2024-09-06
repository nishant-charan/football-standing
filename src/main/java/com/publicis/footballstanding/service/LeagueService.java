package com.publicis.footballstanding.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.footballstanding.dto.ErrorResponseDto;
import com.publicis.footballstanding.dto.LeagueDto;
import com.publicis.footballstanding.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.publicis.footballstanding.constant.Constants.*;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final FootballService footballService;

    private final ObjectMapper objectMapper;

    public List<LeagueDto> getLeagues(String countryId) {
        List<LeagueDto> leagues;
        String response = footballService.getDataFromService(GET_LEAGUES_ACTION, COUNTRY_ID_PARAM, countryId);
        try {
            if (response.contains(ERROR)) {
                ErrorResponseDto errorResponseDto = objectMapper.readValue(response, ErrorResponseDto.class);
                throw new ServiceException(errorResponseDto.getMessage());
            } else {
                leagues = Arrays.asList(objectMapper.readValue(response, LeagueDto[].class));
            }
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Something Went Wrong!!!");
        }
        return leagues;
    }
}
