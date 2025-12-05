package pe.edu.upc.psychotest_platform.analytics.domain.services;

import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.AppointmentSummary;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.SharedInfoInsight;

import java.util.List;
import java.util.Optional;

/**
 * PsychologistAnalyticsQueryService.
 * Service interface for psychologist analytics query operations.
 */
public interface PsychologistAnalyticsQueryService {

    /**
     * Handle get psychologist analytics by ID query.
     * @param query the query
     * @return the psychologist analytics
     */
    Optional<PsychologistAnalytics> handle(GetPsychologistAnalyticsByIdQuery query);

    /**
     * Handle get psychologist analytics by user ID query.
     * @param query the query
     * @return the psychologist analytics
     */
    Optional<PsychologistAnalytics> handle(GetPsychologistAnalyticsByUserIdQuery query);

    /**
     * Handle get psychologist overview query.
     * @param query the query
     * @return the psychologist analytics
     */
    Optional<PsychologistAnalytics> handle(GetPsychologistOverviewQuery query);

    /**
     * Handle get psychologist appointment summary query.
     * @param query the query
     * @return the appointment summary
     */
    Optional<AppointmentSummary> handle(GetPsychologistAppointmentSummaryQuery query);

    /**
     * Handle get psychologist shared info insights query.
     * @param query the query
     * @return the shared info insights
     */
    Optional<List<SharedInfoInsight>> handle(GetPsychologistSharedInfoInsightsQuery query);
}

