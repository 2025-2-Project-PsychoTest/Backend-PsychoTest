package pe.edu.upc.psychotest_platform.analytics.domain.model.commands;
import java.util.Objects;
public record UpdateAnalyticsPreferencesCommand(Long userId, boolean showRecommendations, boolean showUsage) {
    public UpdateAnalyticsPreferencesCommand {
        Objects.requireNonNull(userId, "UserId cannot be null");
    }
}






