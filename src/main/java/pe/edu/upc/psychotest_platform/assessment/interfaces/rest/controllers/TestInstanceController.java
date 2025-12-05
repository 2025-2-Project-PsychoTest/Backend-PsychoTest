package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetSharedTestsForPsychologistQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetTestInstanceByIdQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetTestInstancesByStudentIdQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestInstanceCommandService;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestInstanceQueryService;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.assemblers.TestInstanceAssembler;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.*;

import java.util.List;

/**
 * TestInstanceController.
 * REST controller for test instance management.
 */
@RestController
@RequestMapping("/api/v1/test-instances")
@Tag(name = "Test Instances", description = "Test instance management endpoints")
public class TestInstanceController {

    private final TestInstanceCommandService testInstanceCommandService;
    private final TestInstanceQueryService testInstanceQueryService;

    public TestInstanceController(TestInstanceCommandService testInstanceCommandService,
                                   TestInstanceQueryService testInstanceQueryService) {
        this.testInstanceCommandService = testInstanceCommandService;
        this.testInstanceQueryService = testInstanceQueryService;
    }

    /**
     * Start a new test instance.
     * @param resource the start test resource
     * @return the created test instance
     */
    @PostMapping("/start")
    @Operation(summary = "Start test", description = "Start a new test instance for a student")
    public ResponseEntity<TestInstanceResource> startTest(@RequestBody StartTestResource resource) {
        try {
            var command = TestInstanceAssembler.toCommandFromResource(resource);
            var testInstance = testInstanceCommandService.handle(command);

            if (testInstance.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            var testInstanceResource = TestInstanceAssembler.toResourceFromEntity(testInstance.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(testInstanceResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Submit an answer to a test instance.
     * @param id the test instance id
     * @param resource the submit answer resource
     * @return the updated test instance
     */
    @PostMapping("/{id}/answers")
    @Operation(summary = "Submit answer", description = "Submit an answer to a question in a test instance")
    public ResponseEntity<TestInstanceResource> submitAnswer(@PathVariable Long id,
                                                               @RequestBody SubmitAnswerResource resource) {
        try {
            var command = TestInstanceAssembler.toCommandFromResource(id, resource);
            var testInstance = testInstanceCommandService.handle(command);

            if (testInstance.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var testInstanceResource = TestInstanceAssembler.toResourceFromEntity(testInstance.get());
            return ResponseEntity.ok(testInstanceResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Complete a test instance.
     * @param id the test instance id
     * @return the completed test instance
     */
    @PostMapping("/{id}/complete")
    @Operation(summary = "Complete test", description = "Complete a test instance and calculate the score")
    public ResponseEntity<TestInstanceResource> completeTest(@PathVariable Long id) {
        try {
            var command = TestInstanceAssembler.toCompleteCommand(id);
            var testInstance = testInstanceCommandService.handle(command);

            if (testInstance.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var testInstanceResource = TestInstanceAssembler.toResourceFromEntity(testInstance.get());
            return ResponseEntity.ok(testInstanceResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Share a test instance with a psychologist.
     * @param id the test instance id
     * @param resource the share test resource
     * @return the updated test instance
     */
    @PostMapping("/{id}/share")
    @Operation(summary = "Share test", description = "Share a test instance with a psychologist")
    public ResponseEntity<TestInstanceResource> shareTest(@PathVariable Long id,
                                                            @RequestBody ShareTestResource resource) {
        try {
            var command = TestInstanceAssembler.toCommandFromResource(id, resource);
            var testInstance = testInstanceCommandService.handle(command);

            if (testInstance.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var testInstanceResource = TestInstanceAssembler.toResourceFromEntity(testInstance.get());
            return ResponseEntity.ok(testInstanceResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Validate a test instance.
     * @param id the test instance id
     * @param resource the validate test resource
     * @return the validated test instance
     */
    @PostMapping("/{id}/validate")
    @Operation(summary = "Validate test", description = "Validate a test instance (Psychologist only)")
    public ResponseEntity<TestInstanceResource> validateTest(@PathVariable Long id,
                                                               @RequestBody ValidateTestResource resource) {
        try {
            var command = TestInstanceAssembler.toCommandFromResource(id, resource);
            var testInstance = testInstanceCommandService.handle(command);

            if (testInstance.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var testInstanceResource = TestInstanceAssembler.toResourceFromEntity(testInstance.get());
            return ResponseEntity.ok(testInstanceResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get test instance by ID.
     * @param id the test instance id
     * @return the test instance resource
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get test instance by ID", description = "Retrieve a specific test instance")
    public ResponseEntity<TestInstanceResource> getTestInstanceById(@PathVariable Long id) {
        var query = new GetTestInstanceByIdQuery(id);
        var testInstance = testInstanceQueryService.handle(query);

        if (testInstance.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resource = TestInstanceAssembler.toResourceFromEntity(testInstance.get());
        return ResponseEntity.ok(resource);
    }

    /**
     * Get test instances by student ID.
     * @param studentId the student id
     * @return list of test instances
     */
    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get test instances by student", description = "Retrieve all test instances for a student")
    public ResponseEntity<List<TestInstanceResource>> getTestInstancesByStudentId(@PathVariable Long studentId) {
        var query = new GetTestInstancesByStudentIdQuery(studentId);
        var testInstances = testInstanceQueryService.handle(query);
        var resources = testInstances.stream()
                .map(TestInstanceAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get completed test instances by student ID.
     * @param studentId the student id
     * @return list of completed test instances
     */
    @GetMapping("/student/{studentId}/completed")
    @Operation(summary = "Get completed tests by student", description = "Retrieve all completed test instances for a student")
    public ResponseEntity<List<TestInstanceResource>> getCompletedTestsByStudentId(@PathVariable Long studentId) {
        var testInstances = testInstanceQueryService.getCompletedTestsByStudentId(studentId);
        var resources = testInstances.stream()
                .map(TestInstanceAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get in-progress test instances by student ID.
     * @param studentId the student id
     * @return list of in-progress test instances
     */
    @GetMapping("/student/{studentId}/in-progress")
    @Operation(summary = "Get in-progress tests by student", description = "Retrieve all in-progress test instances for a student")
    public ResponseEntity<List<TestInstanceResource>> getInProgressTestsByStudentId(@PathVariable Long studentId) {
        var testInstances = testInstanceQueryService.getInProgressTestsByStudentId(studentId);
        var resources = testInstances.stream()
                .map(TestInstanceAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get shared test instances for a psychologist.
     * @param psychologistId the psychologist id
     * @return list of shared test instances
     */
    @GetMapping("/psychologist/{psychologistId}/shared")
    @Operation(summary = "Get shared tests for psychologist", description = "Retrieve all test instances shared with a psychologist")
    public ResponseEntity<List<TestInstanceResource>> getSharedTestsForPsychologist(@PathVariable Long psychologistId) {
        var query = new GetSharedTestsForPsychologistQuery(psychologistId);
        var testInstances = testInstanceQueryService.handle(query);
        var resources = testInstances.stream()
                .map(TestInstanceAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}

