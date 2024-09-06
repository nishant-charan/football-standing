package com.publicis.footballstanding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    @JsonProperty("team_key")
    private String teamId;

    @JsonProperty("team_name")
    private String teamName;
}
