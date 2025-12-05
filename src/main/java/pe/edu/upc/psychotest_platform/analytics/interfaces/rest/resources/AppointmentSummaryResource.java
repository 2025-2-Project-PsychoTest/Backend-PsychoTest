package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources;

/**
 * AppointmentSummaryResource.
 * Resource representing appointment summary data.
 */
public record AppointmentSummaryResource(
        int totalAppointments,
        int upcomingAppointments,
        int completedAppointments,
        int cancelledAppointments,
        int monthlyAppointments,
        double completionRate
) {
}

