package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

/**
 * AddQuestionResource.
 * Resource for adding a question to a test.
 */
public record AddQuestionResource(
        String questionText,
        String questionType,
        Integer order,
        Integer points
) {
}

