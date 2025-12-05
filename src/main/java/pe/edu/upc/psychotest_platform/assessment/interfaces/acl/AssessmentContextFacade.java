package pe.edu.upc.psychotest_platform.assessment.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.Test;
import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.TestInstance;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetTestByIdQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetTestInstanceByIdQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetTestInstancesByStudentIdQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestInstanceQueryService;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestQueryService;

import java.util.List;
import java.util.Optional;

/**
 * AssessmentContextFacade.
 * Anti-Corruption Layer (ACL) facade for Assessment bounded context.
 * Provides a simplified interface for other bounded contexts to interact with Assessment.
 * This facade exposes only the necessary operations and hides internal complexity.
 */
@Service
public class AssessmentContextFacade {

    private final TestQueryService testQueryService;
    private final TestInstanceQueryService testInstanceQueryService;

    public AssessmentContextFacade(
            TestQueryService testQueryService,
            TestInstanceQueryService testInstanceQueryService) {
        this.testQueryService = testQueryService;
        this.testInstanceQueryService = testInstanceQueryService;
    }

    // ========== Test Operations ==========

    /**
     * Check if a test exists by its ID.
     * @param testId the test id
     * @return true if test exists, false otherwise
     */
    public boolean existsTestById(Long testId) {
        if (testId == null || testId <= 0) {
            return false;
        }
        var query = new GetTestByIdQuery(testId);
        var test = testQueryService.handle(query);
        return test.isPresent();
    }

    /**
     * Get test by ID.
     * @param testId the test id
     * @return optional test
     */
    public Optional<Test> getTestById(Long testId) {
        if (testId == null || testId <= 0) {
            return Optional.empty();
        }
        var query = new GetTestByIdQuery(testId);
        return testQueryService.handle(query);
    }

    /**
     * Get test title by ID.
     * @param testId the test id
     * @return the test title if exists, null otherwise
     */
    public String getTestTitleById(Long testId) {
        if (testId == null || testId <= 0) {
            return null;
        }
        var query = new GetTestByIdQuery(testId);
        var test = testQueryService.handle(query);
        return test.map(Test::getTitle).orElse(null);
    }

    /**
     * Check if a test is active.
     * @param testId the test id
     * @return true if test is active, false otherwise
     */
    public boolean isTestActive(Long testId) {
        if (testId == null || testId <= 0) {
            return false;
        }
        var query = new GetTestByIdQuery(testId);
        var test = testQueryService.handle(query);
        return test.map(Test::getIsActive).orElse(false);
    }

    /**
     * Get test type by ID.
     * @param testId the test id
     * @return the test type if exists, null otherwise
     */
    public String getTestTypeById(Long testId) {
        if (testId == null || testId <= 0) {
            return null;
        }
        var query = new GetTestByIdQuery(testId);
        var test = testQueryService.handle(query);
        return test.map(t -> t.getTestType() != null ? t.getTestType().type() : null).orElse(null);
    }

    /**
     * Get all active tests.
     * @return list of active tests
     */
    public List<Test> getAllActiveTests() {
        return testQueryService.getAllActiveTests();
    }

    /**
     * Get free tests.
     * @return list of free tests
     */
    public List<Test> getFreeTests() {
        return testQueryService.getFreeTests();
    }

    // ========== Test Instance Operations ==========

    /**
     * Check if a test instance exists by its ID.
     * @param testInstanceId the test instance id
     * @return true if test instance exists, false otherwise
     */
    public boolean existsTestInstanceById(Long testInstanceId) {
        if (testInstanceId == null || testInstanceId <= 0) {
            return false;
        }
        var query = new GetTestInstanceByIdQuery(testInstanceId);
        var testInstance = testInstanceQueryService.handle(query);
        return testInstance.isPresent();
    }

    /**
     * Get test instance by ID.
     * @param testInstanceId the test instance id
     * @return optional test instance
     */
    public Optional<TestInstance> getTestInstanceById(Long testInstanceId) {
        if (testInstanceId == null || testInstanceId <= 0) {
            return Optional.empty();
        }
        var query = new GetTestInstanceByIdQuery(testInstanceId);
        return testInstanceQueryService.handle(query);
    }

    /**
     * Get test instances by student ID.
     * @param studentId the student id
     * @return list of test instances
     */
    public List<TestInstance> getTestInstancesByStudentId(Long studentId) {
        if (studentId == null || studentId <= 0) {
            return List.of();
        }
        var query = new GetTestInstancesByStudentIdQuery(studentId);
        return testInstanceQueryService.handle(query);
    }

    /**
     * Get completed test instances by student ID.
     * @param studentId the student id
     * @return list of completed test instances
     */
    public List<TestInstance> getCompletedTestsByStudentId(Long studentId) {
        if (studentId == null || studentId <= 0) {
            return List.of();
        }
        return testInstanceQueryService.getCompletedTestsByStudentId(studentId);
    }

    /**
     * Get in-progress test instances by student ID.
     * @param studentId the student id
     * @return list of in-progress test instances
     */
    public List<TestInstance> getInProgressTestsByStudentId(Long studentId) {
        if (studentId == null || studentId <= 0) {
            return List.of();
        }
        return testInstanceQueryService.getInProgressTestsByStudentId(studentId);
    }

    /**
     * Get test instance status by ID.
     * @param testInstanceId the test instance id
     * @return the status if exists, null otherwise
     */
    public String getTestInstanceStatusById(Long testInstanceId) {
        if (testInstanceId == null || testInstanceId <= 0) {
            return null;
        }
        var query = new GetTestInstanceByIdQuery(testInstanceId);
        var testInstance = testInstanceQueryService.handle(query);
        return testInstance.map(ti -> ti.getStatus() != null ? ti.getStatus().status() : null).orElse(null);
    }

    /**
     * Get test instance score by ID.
     * @param testInstanceId the test instance id
     * @return the score if exists, null otherwise
     */
    public Double getTestInstanceScoreById(Long testInstanceId) {
        if (testInstanceId == null || testInstanceId <= 0) {
            return null;
        }
        var query = new GetTestInstanceByIdQuery(testInstanceId);
        var testInstance = testInstanceQueryService.handle(query);
        return testInstance.map(TestInstance::getScore).orElse(null);
    }

    /**
     * Check if a test instance is completed.
     * @param testInstanceId the test instance id
     * @return true if test instance is completed, false otherwise
     */
    public boolean isTestInstanceCompleted(Long testInstanceId) {
        if (testInstanceId == null || testInstanceId <= 0) {
            return false;
        }
        var query = new GetTestInstanceByIdQuery(testInstanceId);
        var testInstance = testInstanceQueryService.handle(query);
        return testInstance.map(ti -> "COMPLETED".equals(ti.getStatus().status())).orElse(false);
    }

    /**
     * Count completed tests for a student.
     * @param studentId the student id
     * @return the count of completed tests
     */
    public int countCompletedTestsByStudentId(Long studentId) {
        if (studentId == null || studentId <= 0) {
            return 0;
        }
        return testInstanceQueryService.getCompletedTestsByStudentId(studentId).size();
    }

    /**
     * Count in-progress tests for a student.
     * @param studentId the student id
     * @return the count of in-progress tests
     */
    public int countInProgressTestsByStudentId(Long studentId) {
        if (studentId == null || studentId <= 0) {
            return 0;
        }
        return testInstanceQueryService.getInProgressTestsByStudentId(studentId).size();
    }
}

