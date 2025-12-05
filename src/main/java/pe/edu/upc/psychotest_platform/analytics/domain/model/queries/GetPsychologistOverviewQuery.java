package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

/**
 * GetPsychologistOverviewQuery.
 * Query to get complete psychologist analytics overview by user ID.
 */
public record GetPsychologistOverviewQuery(Long userId) {
    public GetPsychologistOverviewQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}
