package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

/**
 * QuestionResource.
 * Resource representing a question in a test.
 */
public record QuestionResource(
        Long id,
        String questionText,
        String questionType,
        Integer order,
        Integer points
) {
}

