package com.publicis.footballstanding.api;

import com.publicis.footballstanding.dto.ErrorDto;
import com.publicis.footballstanding.dto.StandingDto;
import com.publicis.footballstanding.service.StandingService;
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
@RequestMapping("/api/v1/standings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StandingApi {

    private final StandingService standingService;

    @GetMapping
    @Operation(summary = "Fetch Standing List", description = "Fetch Standings as per the provided LeagueId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Exception occurred", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "200", description = "successful"),
    })
    public ResponseEntity<List<StandingDto>> getStandings(@RequestParam String leagueId) {
        List<StandingDto> standings = standingService.getStandings(leagueId);
        return ResponseEntity.ok(standings);
    }
}
