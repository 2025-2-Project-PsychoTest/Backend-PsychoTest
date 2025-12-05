package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * TestInstanceResource.
 * Resource representing a test instance.
 */
public record TestInstanceResource(
        Long id,
        Long testId,
        Long studentId,
        String status,
        LocalDateTime startTime,
        LocalDateTime completionTime,
        Double score,
        Double maxScore,
        Double scorePercentage,
        Map<Long, Long> answers,
        Set<Long> sharedWithPsychologists,
        Long validatedByPsychologistId,
        String validationNotes
) {
}

