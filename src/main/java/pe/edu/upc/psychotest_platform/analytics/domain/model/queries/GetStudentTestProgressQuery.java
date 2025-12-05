package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

/**
 * GetStudentTestProgressQuery.
 * Query to get student test progress by user ID.
 */
public record GetStudentTestProgressQuery(Long userId) {
    public GetStudentTestProgressQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}
