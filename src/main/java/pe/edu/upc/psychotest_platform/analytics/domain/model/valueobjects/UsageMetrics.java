package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UsageMetrics {
    private int loginCount;
    private long timeSpentMinutes;

    public UsageMetrics() {}

    public UsageMetrics(int loginCount, long timeSpentMinutes) {
        if (loginCount < 0) {
            throw new IllegalArgumentException("Login count cannot be negative");
        }
        if (timeSpentMinutes < 0) {
            throw new IllegalArgumentException("Time spent cannot be negative");
        }
        this.loginCount = loginCount;
        this.timeSpentMinutes = timeSpentMinutes;
    }

    public int getLoginCount() { return loginCount; }

    public long getTimeSpentMinutes() { return timeSpentMinutes; }
}

