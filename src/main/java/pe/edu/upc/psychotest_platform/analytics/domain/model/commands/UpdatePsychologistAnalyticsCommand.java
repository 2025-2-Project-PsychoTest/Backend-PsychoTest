package pe.edu.upc.psychotest_platform.analytics.domain.model.commands;

import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.*;
import java.util.List;

public record UpdatePsychologistAnalyticsCommand(
        Long userId,
        AppointmentSummary appointmentSummary,
        List<SharedInfoInsight> sharedInfoInsights,
        UsageMetrics usageMetrics
) {
    public UpdatePsychologistAnalyticsCommand {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}