package pe.edu.upc.psychotest_platform.analytics.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.AppointmentSummary;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.SharedInfoInsight;
import pe.edu.upc.psychotest_platform.analytics.domain.services.PsychologistAnalyticsQueryService;
import pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories.PsychologistAnalyticsRepository;

import java.util.List;
import java.util.Optional;

/**
 * PsychologistAnalyticsQueryServiceImpl.
 * Implementation of psychologist analytics query service.
 */
@Service
public class PsychologistAnalyticsQueryServiceImpl implements PsychologistAnalyticsQueryService {

    private final PsychologistAnalyticsRepository psychologistAnalyticsRepository;

    public PsychologistAnalyticsQueryServiceImpl(PsychologistAnalyticsRepository psychologistAnalyticsRepository) {
        this.psychologistAnalyticsRepository = psychologistAnalyticsRepository;
    }

    @Override
    public Optional<PsychologistAnalytics> handle(GetPsychologistAnalyticsByIdQuery query) {
        return psychologistAnalyticsRepository.findById(query.analyticsId());
    }

    @Override
    public Optional<PsychologistAnalytics> handle(GetPsychologistAnalyticsByUserIdQuery query) {
        return psychologistAnalyticsRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<PsychologistAnalytics> handle(GetPsychologistOverviewQuery query) {
        return psychologistAnalyticsRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<AppointmentSummary> handle(GetPsychologistAppointmentSummaryQuery query) {
        return psychologistAnalyticsRepository.findByUserId(query.userId())
                .map(PsychologistAnalytics::getAppointmentSummary);
    }

    @Override
    public Optional<List<SharedInfoInsight>> handle(GetPsychologistSharedInfoInsightsQuery query) {
        return psychologistAnalyticsRepository.findByUserId(query.userId())
                .map(PsychologistAnalytics::getSharedInfoInsights);
    }
}

