package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * UsageMetricsResource.
 * Resource representing usage metrics data.
 */
public record UsageMetricsResource(
        int loginCount,
        long timeSpentMinutes,
        LocalDateTime lastLoginDate,
        int actionsPerformed,
        double averageTimePerLogin,
        double averageActionsPerLogin
) {
}

