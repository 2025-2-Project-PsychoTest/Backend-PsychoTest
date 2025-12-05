package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.services.StudentAnalyticsQueryService;
import pe.edu.upc.psychotest_platform.analytics.interfaces.rest.assemblers.StudentAnalyticsAssembler;
import pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources.*;

import java.util.List;

/**
 * StudentAnalyticsController.
 * REST controller for student analytics endpoints.
 */
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/analytics/students", produces = "application/json")
@Tag(name = "Student Analytics", description = "Student Analytics Management Endpoints")
public class StudentAnalyticsController {

    private final StudentAnalyticsQueryService studentAnalyticsQueryService;

    public StudentAnalyticsController(StudentAnalyticsQueryService studentAnalyticsQueryService) {
        this.studentAnalyticsQueryService = studentAnalyticsQueryService;
    }

    @Operation(summary = "Get student analytics overview", description = "Get complete analytics overview for a student by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student analytics retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Student analytics not found")
    })
    @GetMapping("/{studentId}/overview")
    public ResponseEntity<StudentAnalyticsResource> getStudentOverview(
            @Parameter(description = "Student User ID") @PathVariable Long studentId) {
        var query = new GetStudentOverviewQuery(studentId);
        var result = studentAnalyticsQueryService.handle(query);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resource = StudentAnalyticsAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Get student test progress", description = "Get test progress for a student by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Test progress retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Test progress not found")
    })
    @GetMapping("/{studentId}/test-progress")
    public ResponseEntity<TestProgressResource> getStudentTestProgress(
            @Parameter(description = "Student User ID") @PathVariable Long studentId) {
        var query = new GetStudentTestProgressQuery(studentId);
        var result = studentAnalyticsQueryService.handle(query);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resource = StudentAnalyticsAssembler.toTestProgressResource(result.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Get student career recommendations", description = "Get career recommendations for a student by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Career recommendations retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Career recommendations not found")
    })
    @GetMapping("/{studentId}/career-recommendations")
    public ResponseEntity<List<CareerRecommendationResource>> getStudentCareerRecommendations(
            @Parameter(description = "Student User ID") @PathVariable Long studentId) {
        var query = new GetStudentCareerRecommendationsQuery(studentId);
        var result = studentAnalyticsQueryService.handle(query);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resources = StudentAnalyticsAssembler.toCareerRecommendationResourceList(result.get());
        return ResponseEntity.ok(resources);
    }
}

