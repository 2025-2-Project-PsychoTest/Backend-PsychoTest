package pe.edu.upc.psychotest_platform.assessment.domain.services;

import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.TestInstance;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * TestInstanceQueryService.
 * Service interface for test instance queries.
 */
public interface TestInstanceQueryService {

    /**
     * Handle GetTestInstanceByIdQuery.
     * @param query the query
     * @return the test instance if found
     */
    Optional<TestInstance> handle(GetTestInstanceByIdQuery query);

    /**
     * Handle GetTestInstancesByStudentIdQuery.
     * @param query the query
     * @return list of test instances for the student
     */
    List<TestInstance> handle(GetTestInstancesByStudentIdQuery query);

    /**
     * Handle GetSharedTestsForPsychologistQuery.
     * @param query the query
     * @return list of test instances shared with the psychologist
     */
    List<TestInstance> handle(GetSharedTestsForPsychologistQuery query);

    /**
     * Get completed test instances for a student.
     * @param studentId the student id
     * @return list of completed test instances
     */
    List<TestInstance> getCompletedTestsByStudentId(Long studentId);

    /**
     * Get in-progress test instances for a student.
     * @param studentId the student id
     * @return list of in-progress test instances
     */
    List<TestInstance> getInProgressTestsByStudentId(Long studentId);
}

