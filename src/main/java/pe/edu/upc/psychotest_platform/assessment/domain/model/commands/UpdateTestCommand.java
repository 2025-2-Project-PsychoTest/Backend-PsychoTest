package pe.edu.upc.psychotest_platform.assessment.domain.model.commands;

/**
 * Comando para actualizar un test existente.
 */
public record UpdateTestCommand(
        Long testId,
        String title,
        String description,
        Integer durationMinutes
) {}