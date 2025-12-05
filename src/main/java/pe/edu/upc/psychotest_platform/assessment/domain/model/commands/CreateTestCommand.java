package pe.edu.upc.psychotest_platform.assessment.domain.model.commands;

/**
 * Command to create a new test in the catalog.
 */
public record CreateTestCommand(
        Long createdByPsychologistId,
        Integer durationMinutes,
        Double price,
        String pricingTier,
        String testType,
        String description,
        String title
) {}