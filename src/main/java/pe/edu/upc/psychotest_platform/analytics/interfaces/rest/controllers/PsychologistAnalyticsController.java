package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.services.PsychologistAnalyticsQueryService;
import pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources.*;
import pe.edu.upc.psychotest_platform.analytics.interfaces.rest.assemblers.*;

import java.util.List;

/**
 * PsychologistAnalyticsController.
 * REST controller for psychologist analytics endpoints.
 */
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/analytics/psychologists", produces = "application/json")
@Tag(name = "Psychologist Analytics", description = "Psychologist Analytics Management Endpoints")
public class PsychologistAnalyticsController {

    private final PsychologistAnalyticsQueryService psychologistAnalyticsQueryService;

    public PsychologistAnalyticsController(PsychologistAnalyticsQueryService psychologistAnalyticsQueryService) {
        this.psychologistAnalyticsQueryService = psychologistAnalyticsQueryService;
    }

    @Operation(summary = "Get psychologist analytics overview", description = "Get complete analytics overview for a psychologist by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Psychologist analytics retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Psychologist analytics not found")
    })
    @GetMapping("/{psychologistId}/overview")
    public ResponseEntity<PsychologistAnalyticsResource> getPsychologistOverview(
            @Parameter(description = "Psychologist User ID") @PathVariable Long psychologistId) {
        var query = new GetPsychologistOverviewQuery(psychologistId);
        var result = psychologistAnalyticsQueryService.handle(query);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resource = PsychologistAnalyticsAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Get psychologist appointment summary", description = "Get appointment summary for a psychologist by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Appointment summary retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Appointment summary not found")
    })
    @GetMapping("/{psychologistId}/appointment-summary")
    public ResponseEntity<AppointmentSummaryResource> getPsychologistAppointmentSummary(
            @Parameter(description = "Psychologist User ID") @PathVariable Long psychologistId) {
        var query = new GetPsychologistAppointmentSummaryQuery(psychologistId);
        var result = psychologistAnalyticsQueryService.handle(query);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resource = PsychologistAnalyticsAssembler.toAppointmentSummaryResource(result.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Get psychologist shared info insights", description = "Get shared information insights for a psychologist by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Shared info insights retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Shared info insights not found")
    })
    @GetMapping("/{psychologistId}/shared-info-insights")
    public ResponseEntity<List<SharedInfoInsightResource>> getPsychologistSharedInfoInsights(
            @Parameter(description = "Psychologist User ID") @PathVariable Long psychologistId) {
        var query = new GetPsychologistSharedInfoInsightsQuery(psychologistId);
        var result = psychologistAnalyticsQueryService.handle(query);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resources = PsychologistAnalyticsAssembler.toSharedInfoInsightResourceList(result.get());
        return ResponseEntity.ok(resources);
    }
}

