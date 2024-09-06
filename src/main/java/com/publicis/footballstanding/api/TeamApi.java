package com.publicis.footballstanding.api;

import com.publicis.footballstanding.dto.ErrorDto;
import com.publicis.footballstanding.dto.TeamDto;
import com.publicis.footballstanding.service.TeamService;
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
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TeamApi {

    private final TeamService teamService;

    @GetMapping
    @Operation(summary = "Fetch Team List", description = "Fetch Teams as per the provided LeagueId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Exception occurred", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "200", description = "successful"),
    })
    public ResponseEntity<List<TeamDto>> getTeams(@RequestParam String leagueId) {
        List<TeamDto> teams = teamService.getTeams(leagueId);
        return ResponseEntity.ok(teams);
    }
}
