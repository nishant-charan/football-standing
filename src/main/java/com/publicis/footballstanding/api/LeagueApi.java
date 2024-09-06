package com.publicis.footballstanding.api;

import com.publicis.footballstanding.dto.ErrorDto;
import com.publicis.footballstanding.dto.LeagueDto;
import com.publicis.footballstanding.service.LeagueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leagues")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LeagueApi {

    private final LeagueService leagueService;

    @GetMapping
    @Operation(summary = "Fetch League List", description = "Fetch Leagues as per the provided CountryId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Exception occurred", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "200", description = "successful"),
    })
    public ResponseEntity<List<LeagueDto>> getLeagues(@RequestParam String countryId) {
        List<LeagueDto> leagues = leagueService.getLeagues(countryId);
        return ResponseEntity.ok(leagues);
    }
}
