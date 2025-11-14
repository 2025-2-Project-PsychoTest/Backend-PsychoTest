package pe.edu.upc.psychotest_platform.management.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.Duration;
import java.time.Instant;

@Embeddable
public record TestDateRange(
        @Column(name = "start_date") Instant start,
        @Column(name = "end_date") Instant end
) {
    // El constructor
    public TestDateRange {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
    }

    public long durationInMinutes() {
        return Duration.between(start, end).toMinutes();
    }

    public boolean isWithinDate(Instant now) {
        return !now.isBefore(start) && !now.isAfter(end);
    }

    public boolean isValid() {
        return start != null && end != null && start.isBefore(end);
    }
}