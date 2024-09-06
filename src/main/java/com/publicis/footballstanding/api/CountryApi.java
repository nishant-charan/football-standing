package com.publicis.footballstanding.api;

import com.publicis.footballstanding.dto.CountryDto;
import com.publicis.footballstanding.dto.ErrorDto;
import com.publicis.footballstanding.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CountryApi {

    private final CountryService countryService;

    @GetMapping
    @Operation(summary = "Fetch Country List", description = "Fetch Countries supported for this Football Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Exception occurred", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "200", description = "successful"),
    })
    public ResponseEntity<List<CountryDto>> getCountries() {
        List<CountryDto> countries = countryService.getCountries();
        return ResponseEntity.ok(countries);
    }
}
