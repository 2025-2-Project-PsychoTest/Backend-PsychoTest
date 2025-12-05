package pe.edu.upc.psychotest_platform.assessment.domain.services;

import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.Test;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetAllTestsQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetTestByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * TestQueryService.
 * Service interface for test queries.
 */
public interface TestQueryService {

    /**
     * Handle GetAllTestsQuery.
     * @param query the query
     * @return list of all tests
     */
    List<Test> handle(GetAllTestsQuery query);

    /**
     * Handle GetTestByIdQuery.
     * @param query the query
     * @return the test if found
     */
    Optional<Test> handle(GetTestByIdQuery query);

    /**
     * Get all active tests.
     * @return list of active tests
     */
    List<Test> getAllActiveTests();

    /**
     * Get tests by type.
     * @param testType the test type
     * @return list of tests of that type
     */
    List<Test> getTestsByType(String testType);

    /**
     * Get free tests.
     * @return list of free tests
     */
    List<Test> getFreeTests();
}

