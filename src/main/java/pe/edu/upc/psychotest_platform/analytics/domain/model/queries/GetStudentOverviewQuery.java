package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

/**
 * GetStudentOverviewQuery.
 * Query to get complete student analytics overview by user ID.
 */
public record GetStudentOverviewQuery(Long userId) {
    public GetStudentOverviewQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}
