package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

/**
 * Resource representing an answer option.
 * AnswerResource.
 */
public record AnswerResource(
        Integer value,
        Boolean isCorrect,
        String answerText,
        Long id
) {}