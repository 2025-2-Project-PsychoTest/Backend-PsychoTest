package pe.edu.upc.psychotest_platform.shared.interfaces.rest.resources;

/**
 * Response for internal server errors (HTTP 500).
 */
public record InternalServerErrorResponse(
    int status,
    String error,
    String message
) {
}


