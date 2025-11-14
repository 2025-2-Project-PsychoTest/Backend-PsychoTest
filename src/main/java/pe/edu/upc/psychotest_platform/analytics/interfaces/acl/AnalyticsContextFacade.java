package pe.edu.upc.psychotest_platform.analytics.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.services.AnalyticsCommandService;
import pe.edu.upc.psychotest_platform.analytics.domain.services.AnalyticsQueryService;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyticsContextFacade {
    private final AnalyticsCommandService analyticsCommandService;
    private final AnalyticsQueryService analyticsQueryService;

    public AnalyticsContextFacade(AnalyticsCommandService analyticsCommandService, AnalyticsQueryService analyticsQueryService) {
        this.analyticsCommandService = analyticsCommandService;
        this.analyticsQueryService = analyticsQueryService;
    }

    public Optional<StudentAnalytics> getStudentOverview(Long userId) {
        var query = new GetStudentOverviewQuery(userId);
        return analyticsQueryService.handle(query);
    }

    public Optional<PsychologistAnalytics> getPsychologistOverview(Long userId) {
        var query = new GetPsychologistOverviewQuery(userId);
        return analyticsQueryService.handle(query);
    }

    // Add other methods as needed
}
