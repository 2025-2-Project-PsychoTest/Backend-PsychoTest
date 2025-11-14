package pe.edu.upc.psychotest_platform.analytics.domain.services;

import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.UpdateAnalyticsPreferencesCommand;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.*;

import java.util.List;
import java.util.Optional;

public interface AnalyticsQueryService {

    Optional<StudentAnalytics> handle(GetStudentOverviewQuery query);

    Optional<TestProgress> handle(GetStudentTestProgressQuery query);

    Optional<List<CareerRecommendation>> handle(GetStudentCareerRecommendationsQuery query);

    Optional<PsychologistAnalytics> handle(GetPsychologistOverviewQuery query);

    Optional<AppointmentSummary> handle(GetPsychologistAppointmentSummaryQuery query);

    Optional<List<SharedInfoInsight>> handle(GetPsychologistSharedInfoInsightsQuery query);
}
