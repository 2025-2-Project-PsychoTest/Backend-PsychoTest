package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

import java.util.List;

/**
 * TestResource.
 * Resource representing a complete test.
 */
public record TestResource(
        Long id,
        String title,
        String description,
        String testType,
        String pricingTier,
        Double price,
        Integer durationMinutes,
        Integer totalQuestions,
        Boolean isActive,
        Long createdByPsychologistId,
        List<QuestionResource> questions
) {
}

