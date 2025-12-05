package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

/**
 * GetStudentCareerRecommendationsQuery.
 * Query to get student career recommendations by user ID.
 */
public record GetStudentCareerRecommendationsQuery(Long userId) {
    public GetStudentCareerRecommendationsQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}
