package pe.edu.upc.psychotest_platform.analytics.domain.model.commands;

import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.*;
import java.util.List;

public record UpdateStudentAnalyticsCommand(
        Long userId,
        TestProgress testProgress,
        List<CareerRecommendation> careerRecommendations,
        UsageMetrics usageMetrics
) {
    public UpdateStudentAnalyticsCommand {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}