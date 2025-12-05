package pe.edu.upc.psychotest_platform.assessment.domain.model.commands;

/**
 * AddQuestionToTestCommand.
 * Command to add a question to a test.
 */
public record AddQuestionToTestCommand(
        Long testId,
        String questionText,
        String questionType,
        Integer order,
        Integer points
) {
}

