package pe.edu.upc.psychotest_platform.analytics.domain.model.commands;

public record CreatePsychologistAnalyticsCommand(Long userId) {
    public CreatePsychologistAnalyticsCommand {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}