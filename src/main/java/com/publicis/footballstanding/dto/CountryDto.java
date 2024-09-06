package com.publicis.footballstanding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

    @JsonProperty("country_id")
    private String countryId;

    @JsonProperty("country_name")
    private String countryName;
}
