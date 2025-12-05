package pe.edu.upc.psychotest_platform.assessment.domain.model.commands;

/**
 * ShareTestWithPsychologistCommand.
 * Command to share test results with a psychologist.
 */
public record ShareTestWithPsychologistCommand(
        Long testInstanceId,
        Long psychologistId
) {
}

