package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

/**
 * UpdateTestResource.
 * Resource for updating a test.
 */
public record UpdateTestResource(
        String title,
        String description,
        Integer durationMinutes
) {
}

