package pe.edu.upc.psychotest_platform.assessment.domain.model.commands;

/**
 * CompleteTestInstanceCommand.
 * Command to complete a test instance.
 */
public record CompleteTestInstanceCommand(
        Long testInstanceId
) {
}

