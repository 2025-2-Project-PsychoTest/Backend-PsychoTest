package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

/**
 * GetPsychologistSharedInfoInsightsQuery.
 * Query to get psychologist shared info insights by user ID.
 */
public record GetPsychologistSharedInfoInsightsQuery(Long userId) {
    public GetPsychologistSharedInfoInsightsQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}
