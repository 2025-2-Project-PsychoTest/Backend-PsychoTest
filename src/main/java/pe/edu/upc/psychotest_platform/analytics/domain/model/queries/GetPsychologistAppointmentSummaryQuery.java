package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

/**
 * GetPsychologistAppointmentSummaryQuery.
 * Query to get psychologist appointment summary by user ID.
 */
public record GetPsychologistAppointmentSummaryQuery(Long userId) {
    public GetPsychologistAppointmentSummaryQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}
