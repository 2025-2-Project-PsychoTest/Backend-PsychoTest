package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

/**
 * Resource for creating a test.
 * CreateTestResource.
 */
public record CreateTestResource(
        Long createdByPsychologistId,
        Integer durationMinutes,
        Double price,
        String pricingTier,
        String testType,
        String description,
        String title
) {}