package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources;

/**
 * StartTestResource.
 * Resource for starting a test instance.
 */
public record StartTestResource(
        Long testId,
        Long studentId
) {
}

