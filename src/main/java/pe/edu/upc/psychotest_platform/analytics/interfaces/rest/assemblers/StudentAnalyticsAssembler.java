package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.assemblers;

import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.CareerRecommendation;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.TestProgress;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.UsageMetrics;
import pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * StudentAnalyticsAssembler.
 * Assembler to convert between StudentAnalytics entity and resources.
 */
public class StudentAnalyticsAssembler {

    /**
     * Convert StudentAnalytics entity to StudentAnalyticsResource.
     */
    public static StudentAnalyticsResource toResourceFromEntity(StudentAnalytics entity) {
        return new StudentAnalyticsResource(
                entity.getId(),
                entity.getUserId(),
                toTestProgressResource(entity.getTestProgress()),
                toCareerRecommendationResourceList(entity.getCareerRecommendations()),
                toUsageMetricsResource(entity.getUsageMetrics())
        );
    }

    /**
     * Convert TestProgress to TestProgressResource.
     */
    public static TestProgressResource toTestProgressResource(TestProgress testProgress) {
        if (testProgress == null) {
            return new TestProgressResource(0, 0.0, 0, 0, 0.0);
        }
        return new TestProgressResource(
                testProgress.completedTests(),
                testProgress.averageScore(),
                testProgress.totalTests(),
                testProgress.inProgressTests(),
                testProgress.getCompletionPercentage()
        );
    }

    /**
     * Convert list of CareerRecommendation to list of CareerRecommendationResource.
     */
    public static List<CareerRecommendationResource> toCareerRecommendationResourceList(
            List<CareerRecommendation> careerRecommendations) {
        return careerRecommendations.stream()
                .map(StudentAnalyticsAssembler::toCareerRecommendationResource)
                .collect(Collectors.toList());
    }

    /**
     * Convert CareerRecommendation to CareerRecommendationResource.
     */
    public static CareerRecommendationResource toCareerRecommendationResource(
            CareerRecommendation careerRecommendation) {
        return new CareerRecommendationResource(
                careerRecommendation.careerName(),
                careerRecommendation.matchPercentage(),
                careerRecommendation.description(),
                careerRecommendation.faculty()
        );
    }

    /**
     * Convert UsageMetrics to UsageMetricsResource.
     */
    public static UsageMetricsResource toUsageMetricsResource(UsageMetrics usageMetrics) {
        if (usageMetrics == null) {
            return new UsageMetricsResource(0, 0L, null, 0, 0.0, 0.0);
        }
        return new UsageMetricsResource(
                usageMetrics.loginCount(),
                usageMetrics.timeSpentMinutes(),
                usageMetrics.lastLoginDate(),
                usageMetrics.actionsPerformed(),
                usageMetrics.getAverageTimePerLogin(),
                usageMetrics.getAverageActionsPerLogin()
        );
    }
}