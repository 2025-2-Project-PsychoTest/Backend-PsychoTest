package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

public record GetStudentAnalyticsByUserIdQuery(Long userId) {
    public GetStudentAnalyticsByUserIdQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}