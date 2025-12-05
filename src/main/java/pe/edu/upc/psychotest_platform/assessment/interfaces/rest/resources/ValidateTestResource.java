package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

/**
 * ValidateTestResource.
 * Resource for validating a test.
 */
public record ValidateTestResource(
        Long psychologistId,
        String validationNotes
) {
}

