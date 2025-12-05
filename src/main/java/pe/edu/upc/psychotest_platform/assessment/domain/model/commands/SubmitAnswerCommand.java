package pe.edu.upc.psychotest_platform.assessment.domain.model.commands;

/**
 * SubmitAnswerCommand.
 * Command to submit an answer to a question in a test instance.
 */
public record SubmitAnswerCommand(
        Long testInstanceId,
        Long questionId,
        Long answerId
) {
}

