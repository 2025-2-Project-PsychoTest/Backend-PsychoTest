package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources;

import java.util.List;

/**
 * PsychologistAnalyticsResource.
 * Resource representing complete psychologist analytics data.
 */
public record PsychologistAnalyticsResource(
        Long id,
        Long userId,
        AppointmentSummaryResource appointmentSummary,
        List<SharedInfoInsightResource> sharedInfoInsights,
        UsageMetricsResource usageMetrics
) {
}

