package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

/**
 * TestSummaryResource.
 * Resource representing a test summary for catalog listing.
 */
public record TestSummaryResource(
        Long id,
        String title,
        String description,
        String testType,
        String pricingTier,
        Double price,
        Integer durationMinutes,
        Integer totalQuestions,
        Boolean isActive
) {
}

