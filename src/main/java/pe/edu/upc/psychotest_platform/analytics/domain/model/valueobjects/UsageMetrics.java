package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * UsageMetrics Value Object.
 * Represents the usage metrics of a user in the platform.
 */
@Embeddable
public record UsageMetrics(
        int loginCount,
        long timeSpentMinutes,
        LocalDateTime lastLoginDate,
        int actionsPerformed
) {

    public UsageMetrics {
        if (loginCount < 0) {
            throw new IllegalArgumentException("Login count cannot be negative");
        }
        if (timeSpentMinutes < 0) {
            throw new IllegalArgumentException("Time spent cannot be negative");
        }
        if (actionsPerformed < 0) {
            throw new IllegalArgumentException("Actions performed cannot be negative");
        }
    }

    /**
     * Default constructor for empty usage metrics.
     */
    public UsageMetrics() {
        this(0, 0L, null, 0);
    }

    /**
     * Gets the average time spent per login.
     * @return average time spent in minutes
     */
    public double getAverageTimePerLogin() {
        if (loginCount == 0) return 0.0;
        return (double) timeSpentMinutes / loginCount;
    }

    /**
     * Gets the average actions per login.
     * @return average actions per login
     */
    public double getAverageActionsPerLogin() {
        if (loginCount == 0) return 0.0;
        return (double) actionsPerformed / loginCount;
    }
}

