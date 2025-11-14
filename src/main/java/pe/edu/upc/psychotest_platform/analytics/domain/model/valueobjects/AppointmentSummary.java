package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class AppointmentSummary {
    private int totalAppointments;
    private int upcomingAppointments;

    public AppointmentSummary() {}

    public AppointmentSummary(int totalAppointments, int upcomingAppointments) {
        if (totalAppointments < 0) {
            throw new IllegalArgumentException("Total appointments cannot be negative");
        }
        if (upcomingAppointments < 0) {
            throw new IllegalArgumentException("Upcoming appointments cannot be negative");
        }
        this.totalAppointments = totalAppointments;
        this.upcomingAppointments = upcomingAppointments;
    }

    public int getTotalAppointments() { return totalAppointments; }

    public int getUpcomingAppointments() { return upcomingAppointments; }
}

