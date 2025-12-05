package pe.edu.upc.psychotest_platform.analytics.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.CareerRecommendation;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.TestProgress;
import pe.edu.upc.psychotest_platform.analytics.domain.services.StudentAnalyticsQueryService;
import pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories.StudentAnalyticsRepository;

import java.util.List;
import java.util.Optional;

/**
 * StudentAnalyticsQueryServiceImpl.
 * Implementation of student analytics query service.
 */
@Service
public class StudentAnalyticsQueryServiceImpl implements StudentAnalyticsQueryService {

    private final StudentAnalyticsRepository studentAnalyticsRepository;

    public StudentAnalyticsQueryServiceImpl(StudentAnalyticsRepository studentAnalyticsRepository) {
        this.studentAnalyticsRepository = studentAnalyticsRepository;
    }

    @Override
    public Optional<StudentAnalytics> handle(GetStudentAnalyticsByIdQuery query) {
        return studentAnalyticsRepository.findById(query.analyticsId());
    }

    @Override
    public Optional<StudentAnalytics> handle(GetStudentAnalyticsByUserIdQuery query) {
        return studentAnalyticsRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<StudentAnalytics> handle(GetStudentOverviewQuery query) {
        return studentAnalyticsRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<TestProgress> handle(GetStudentTestProgressQuery query) {
        return studentAnalyticsRepository.findByUserId(query.userId())
                .map(StudentAnalytics::getTestProgress);
    }

    @Override
    public Optional<List<CareerRecommendation>> handle(GetStudentCareerRecommendationsQuery query) {
        return studentAnalyticsRepository.findByUserId(query.userId())
                .map(StudentAnalytics::getCareerRecommendations);
    }
}

