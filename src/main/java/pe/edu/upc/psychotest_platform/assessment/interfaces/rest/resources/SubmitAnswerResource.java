package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

/**
 * SubmitAnswerResource.
 * Resource for submitting an answer.
 */
public record SubmitAnswerResource(
        Long questionId,
        Long answerId
) {
}

