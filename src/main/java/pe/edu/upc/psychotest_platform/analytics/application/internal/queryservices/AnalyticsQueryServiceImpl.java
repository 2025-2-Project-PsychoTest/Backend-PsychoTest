package pe.edu.upc.psychotest_platform.analytics.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.services.AnalyticsQueryService;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.*;
import pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories.StudentAnalyticsRepository;
import pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories.PsychologistAnalyticsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyticsQueryServiceImpl implements AnalyticsQueryService {

    private final StudentAnalyticsRepository studentAnalyticsRepository;
    private final PsychologistAnalyticsRepository psychologistAnalyticsRepository;

    public AnalyticsQueryServiceImpl(StudentAnalyticsRepository studentAnalyticsRepository, PsychologistAnalyticsRepository psychologistAnalyticsRepository) {
        this.studentAnalyticsRepository = studentAnalyticsRepository;
        this.psychologistAnalyticsRepository = psychologistAnalyticsRepository;
    }

    @Override
    public Optional<StudentAnalytics> handle(GetStudentOverviewQuery query) {
        return studentAnalyticsRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<TestProgress> handle(GetStudentTestProgressQuery query) {
        return studentAnalyticsRepository.findByUserId(query.userId()).map(StudentAnalytics::getTestProgress);
    }

    @Override
    public Optional<List<CareerRecommendation>> handle(GetStudentCareerRecommendationsQuery query) {
        return studentAnalyticsRepository.findByUserId(query.userId()).map(StudentAnalytics::getCareerRecommendations);
    }

    @Override
    public Optional<PsychologistAnalytics> handle(GetPsychologistOverviewQuery query) {
        return psychologistAnalyticsRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<AppointmentSummary> handle(GetPsychologistAppointmentSummaryQuery query) {
        return psychologistAnalyticsRepository.findByUserId(query.userId()).map(PsychologistAnalytics::getAppointmentSummary);
    }

    @Override
    public Optional<List<SharedInfoInsight>> handle(GetPsychologistSharedInfoInsightsQuery query) {
        return psychologistAnalyticsRepository.findByUserId(query.userId()).map(PsychologistAnalytics::getSharedInfoInsights);
    }
}
