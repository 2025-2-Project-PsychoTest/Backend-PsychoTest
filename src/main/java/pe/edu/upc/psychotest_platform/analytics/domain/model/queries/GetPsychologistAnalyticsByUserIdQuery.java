package pe.edu.upc.psychotest_platform.analytics.domain.model.queries;

public record GetPsychologistAnalyticsByUserIdQuery(Long userId) {
    public GetPsychologistAnalyticsByUserIdQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}