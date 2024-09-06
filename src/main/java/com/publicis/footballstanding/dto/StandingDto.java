package com.publicis.footballstanding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandingDto {

    @JsonProperty("league_id")
    private String leagueId;

    @JsonProperty("league_name")
    private String leagueName;

    @JsonProperty("team_id")
    private String teamId;

    @JsonProperty("team_name")
    private String teamName;

    @JsonProperty("overall_league_position")
    private String overallLeaguePosition;
}
