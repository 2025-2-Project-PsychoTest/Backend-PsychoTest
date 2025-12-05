package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

public record GetPsychologistAnalyticsByIdQuery(Long analyticsId) {
    public GetPsychologistAnalyticsByIdQuery {
        if (analyticsId == null || analyticsId <= 0) {
            throw new IllegalArgumentException("Analytics ID must be valid");
        }
    }
}