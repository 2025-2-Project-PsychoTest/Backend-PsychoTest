package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources;

import java.util.List;

/**
 * StudentAnalyticsResource.
 * Resource representing complete student analytics data.
 */
public record StudentAnalyticsResource(
        Long id,
        Long userId,
        TestProgressResource testProgress,
        List<CareerRecommendationResource> careerRecommendations,
        UsageMetricsResource usageMetrics
) {
}

