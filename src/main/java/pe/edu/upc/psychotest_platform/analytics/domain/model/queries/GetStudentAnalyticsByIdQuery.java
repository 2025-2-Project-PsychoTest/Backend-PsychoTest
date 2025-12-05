package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

public record GetStudentAnalyticsByIdQuery(Long analyticsId) {
    public GetStudentAnalyticsByIdQuery {
        if (analyticsId == null || analyticsId <= 0) {
            throw new IllegalArgumentException("Analytics ID must be valid");
        }
    }
}