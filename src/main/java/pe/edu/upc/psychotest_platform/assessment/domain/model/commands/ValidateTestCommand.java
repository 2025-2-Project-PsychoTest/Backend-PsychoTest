package pe.edu.upc.psychotest_platform.assessment.domain.model.commands;

/**
 * ValidateTestCommand.
 * Command for a psychologist to validate a test.
 */
public record ValidateTestCommand(
        Long testInstanceId,
        Long psychologistId,
        String validationNotes
) {
}

