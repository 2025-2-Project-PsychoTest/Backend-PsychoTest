package pe.edu.upc.psychotest_platform.analytics.domain.model.commands;

public record CreateStudentAnalyticsCommand(Long userId) {
    public CreateStudentAnalyticsCommand {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
    }
}