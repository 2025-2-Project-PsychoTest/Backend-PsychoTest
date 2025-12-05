package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetAllTestsQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetTestByIdQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestCommandService;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestQueryService;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.assemblers.TestAssembler;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.AddQuestionResource;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.CreateTestResource;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.TestResource;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.TestSummaryResource;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.UpdateTestResource;

import java.util.List;

/**
 * TestCatalogController.
 * REST controller for test catalog management.
 */
@RestController
@RequestMapping("/api/v1/tests")
@Tag(name = "Tests", description = "Test catalog management endpoints")
public class TestCatalogController {

    private final TestCommandService testCommandService;
    private final TestQueryService testQueryService;

    public TestCatalogController(TestCommandService testCommandService,
                                 TestQueryService testQueryService) {
        this.testCommandService = testCommandService;
        this.testQueryService = testQueryService;
    }

    /**
     * Get all tests in the catalog.
     * @return list of test summaries
     */
    @GetMapping
    @Operation(summary = "Get all tests", description = "Retrieve the complete test catalog")
    public ResponseEntity<List<TestSummaryResource>> getAllTests() {
        var query = new GetAllTestsQuery();
        var tests = testQueryService.handle(query);
        var resources = tests.stream()
                .map(TestAssembler::toSummaryResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get test by ID.
     * @param id the test id
     * @return the test resource
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get test by ID", description = "Retrieve a specific test with all details")
    public ResponseEntity<TestResource> getTestById(@PathVariable Long id) {
        var query = new GetTestByIdQuery(id);
        var test = testQueryService.handle(query);

        if (test.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resource = TestAssembler.toResourceFromEntity(test.get());
        return ResponseEntity.ok(resource);
    }

    /**
     * Get active tests only.
     * @return list of active test summaries
     */
    @GetMapping("/active")
    @Operation(summary = "Get active tests", description = "Retrieve only active tests from the catalog")
    public ResponseEntity<List<TestSummaryResource>> getActiveTests() {
        var tests = testQueryService.getAllActiveTests();
        var resources = tests.stream()
                .map(TestAssembler::toSummaryResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get free tests.
     * @return list of free test summaries
     */
    @GetMapping("/free")
    @Operation(summary = "Get free tests", description = "Retrieve only free tests from the catalog")
    public ResponseEntity<List<TestSummaryResource>> getFreeTests() {
        var tests = testQueryService.getFreeTests();
        var resources = tests.stream()
                .map(TestAssembler::toSummaryResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get tests by type.
     * @param type the test type
     * @return list of test summaries
     */
    @GetMapping("/type/{type}")
    @Operation(summary = "Get tests by type", description = "Retrieve tests filtered by type")
    public ResponseEntity<List<TestSummaryResource>> getTestsByType(@PathVariable String type) {
        var tests = testQueryService.getTestsByType(type);
        var resources = tests.stream()
                .map(TestAssembler::toSummaryResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Create a new test.
     * @param resource the create test resource
     * @return the created test resource
     */
    @PostMapping
    @Operation(summary = "Create test", description = "Create a new test in the catalog (Psychologist only)")
    public ResponseEntity<TestResource> createTest(@RequestBody CreateTestResource resource) {
        try {
            var command = TestAssembler.toCommandFromResource(resource);
            var test = testCommandService.handle(command);

            if (test.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            var testResource = TestAssembler.toResourceFromEntity(test.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(testResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Activate a test.
     * @param id the test id
     * @return the updated test resource
     */
    @PostMapping("/{id}/activate")
    @Operation(summary = "Activate test", description = "Activate a test in the catalog (Psychologist only)")
    public ResponseEntity<TestResource> activateTest(@PathVariable Long id) {
        try {
            var test = testCommandService.activateTest(id);

            if (test.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var resource = TestAssembler.toResourceFromEntity(test.get());
            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deactivate a test.
     * @param id the test id
     * @return the updated test resource
     */
    @PostMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate test", description = "Deactivate a test in the catalog (Psychologist only)")
    public ResponseEntity<TestResource> deactivateTest(@PathVariable Long id) {
        try {
            var test = testCommandService.deactivateTest(id);

            if (test.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var resource = TestAssembler.toResourceFromEntity(test.get());
            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update a test.
     * @param id the test id
     * @param resource the update test resource
     * @return the updated test resource
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update test", description = "Update test information (Psychologist only)")
    public ResponseEntity<TestResource> updateTest(@PathVariable Long id,
                                                    @RequestBody UpdateTestResource resource) {
        try {
            var command = TestAssembler.toCommandFromResource(id, resource);
            var test = testCommandService.handle(command);

            if (test.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var testResource = TestAssembler.toResourceFromEntity(test.get());
            return ResponseEntity.ok(testResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete a test.
     * @param id the test id
     * @return no content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete test", description = "Delete a test from the catalog (Psychologist only)")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        try {
            var command = new pe.edu.upc.psychotest_platform.assessment.domain.model.commands.DeleteTestCommand(id);
            testCommandService.handle(command);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add a question to a test.
     * @param id the test id
     * @param resource the add question resource
     * @return the updated test resource
     */
    @PostMapping("/{id}/questions")
    @Operation(summary = "Add question to test", description = "Add a new question to a test (Psychologist only)")
    public ResponseEntity<TestResource> addQuestion(@PathVariable Long id,
                                                     @RequestBody AddQuestionResource resource) {
        try {
            var command = TestAssembler.toCommandFromResource(id, resource);
            var test = testCommandService.handle(command);

            if (test.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var testResource = TestAssembler.toResourceFromEntity(test.get());
            return ResponseEntity.ok(testResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

