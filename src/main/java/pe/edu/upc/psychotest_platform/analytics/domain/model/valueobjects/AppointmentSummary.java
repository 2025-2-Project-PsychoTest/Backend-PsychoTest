package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * AppointmentSummary Value Object.
 * Represents the summary of appointments for psychologists with various counts.
 */
@Embeddable
public record AppointmentSummary(
        int totalAppointments,
        int upcomingAppointments,
        int completedAppointments,
        int cancelledAppointments,
        int monthlyAppointments
) {

    public AppointmentSummary {
        if (totalAppointments < 0) {
            throw new IllegalArgumentException("Total appointments cannot be negative");
        }
        if (upcomingAppointments < 0) {
            throw new IllegalArgumentException("Upcoming appointments cannot be negative");
        }
        if (completedAppointments < 0) {
            throw new IllegalArgumentException("Completed appointments cannot be negative");
        }
        if (cancelledAppointments < 0) {
            throw new IllegalArgumentException("Cancelled appointments cannot be negative");
        }
        if (monthlyAppointments < 0) {
            throw new IllegalArgumentException("Monthly appointments cannot be negative");
        }
    }

    /**
     * Default constructor for empty appointment summary.
     */
    public AppointmentSummary() {
        this(0, 0, 0, 0, 0);
    }

    /**
     * Gets the completion rate of appointments.
     * @return completion rate as percentage
     */
    public double getCompletionRate() {
        if (totalAppointments == 0) return 0.0;
        return (completedAppointments * 100.0) / totalAppointments;
    }
}

