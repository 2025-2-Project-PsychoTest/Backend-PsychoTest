package pe.edu.upc.psychotest_platform.analytics.domain.services;

import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.CareerRecommendation;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.TestProgress;

import java.util.List;
import java.util.Optional;

/**
 * StudentAnalyticsQueryService.
 * Service interface for student analytics query operations.
 */
public interface StudentAnalyticsQueryService {

    /**
     * Handle get student analytics by ID query.
     * @param query the query
     * @return the student analytics
     */
    Optional<StudentAnalytics> handle(GetStudentAnalyticsByIdQuery query);

    /**
     * Handle get student analytics by user ID query.
     * @param query the query
     * @return the student analytics
     */
    Optional<StudentAnalytics> handle(GetStudentAnalyticsByUserIdQuery query);

    /**
     * Handle get student overview query.
     * @param query the query
     * @return the student analytics
     */
    Optional<StudentAnalytics> handle(GetStudentOverviewQuery query);

    /**
     * Handle get student test progress query.
     * @param query the query
     * @return the test progress
     */
    Optional<TestProgress> handle(GetStudentTestProgressQuery query);

    /**
     * Handle get student career recommendations query.
     * @param query the query
     * @return the career recommendations
     */
    Optional<List<CareerRecommendation>> handle(GetStudentCareerRecommendationsQuery query);
}

