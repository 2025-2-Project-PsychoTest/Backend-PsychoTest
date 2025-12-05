package pe.edu.upc.psychotest_platform.assessment.domain.model.commands;

/**
 * StartTestInstanceCommand.
 * Command for a student to start taking a test.
 */
public record StartTestInstanceCommand(
        Long testId,
        Long studentId
) {
}

