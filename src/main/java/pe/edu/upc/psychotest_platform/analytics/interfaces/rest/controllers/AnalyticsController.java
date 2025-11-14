package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.services.AnalyticsQueryService;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.*;
import pe.edu.upc.psychotest_platform.shared.interfaces.rest.resources.NotFoundResponse;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/analytics", produces = "application/json")
@Tag(name = "Analytics", description = "Analytics Management Endpoints")
public class AnalyticsController {

    private final AnalyticsQueryService analyticsQueryService;

    public AnalyticsController(AnalyticsQueryService analyticsQueryService) {
        this.analyticsQueryService = analyticsQueryService;
    }

    @Operation(summary = "Get student test progress")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Test progress retrieved"),
        @ApiResponse(responseCode = "404", description = "Not found", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = NotFoundResponse.class)))
    })
    @GetMapping("/student/test-progress")
    public ResponseEntity<TestProgress> getStudentTestProgress(@Parameter(description = "User ID") @RequestParam Long userId) {
        var query = new GetStudentTestProgressQuery(userId);
        var result = analyticsQueryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/student/career-recommendations")
    public ResponseEntity<List<CareerRecommendation>> getStudentCareerRecommendations(@RequestParam Long userId) {
        var query = new GetStudentCareerRecommendationsQuery(userId);
        var result = analyticsQueryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/student/overview")
    public ResponseEntity<StudentAnalytics> getStudentOverview(@RequestParam Long userId) {
        var query = new GetStudentOverviewQuery(userId);
        var result = analyticsQueryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/psychologist/overview")
    public ResponseEntity<PsychologistAnalytics> getPsychologistOverview(@RequestParam Long userId) {
        var query = new GetPsychologistOverviewQuery(userId);
        var result = analyticsQueryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/psychologist/appointment-summary")
    public ResponseEntity<AppointmentSummary> getPsychologistAppointmentSummary(@RequestParam Long userId) {
        var query = new GetPsychologistAppointmentSummaryQuery(userId);
        var result = analyticsQueryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/psychologist/shared-info-insights")
    public ResponseEntity<List<SharedInfoInsight>> getPsychologistSharedInfoInsights(@RequestParam Long userId) {
        var query = new GetPsychologistSharedInfoInsightsQuery(userId);
        var result = analyticsQueryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get());
    }
}
