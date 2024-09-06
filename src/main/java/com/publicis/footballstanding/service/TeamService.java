package com.publicis.footballstanding.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.footballstanding.dto.ErrorResponseDto;
import com.publicis.footballstanding.dto.TeamDto;
import com.publicis.footballstanding.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.publicis.footballstanding.constant.Constants.*;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final FootballService footballService;

    private final ObjectMapper objectMapper;

    public List<TeamDto> getTeams(String leagueId) {
        List<TeamDto> teams;
        String response = footballService.getDataFromService(GET_TEAMS_ACTION, LEAGUE_ID_PARAM, leagueId);
        try {
            if (response.contains(ERROR)) {
                ErrorResponseDto errorResponseDto = objectMapper.readValue(response, ErrorResponseDto.class);
                throw new ServiceException(errorResponseDto.getMessage());
            } else {
                teams = Arrays.asList(objectMapper.readValue(response, TeamDto[].class));
            }
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Something Went Wrong!!!");
        }
        return teams;
    }
}
