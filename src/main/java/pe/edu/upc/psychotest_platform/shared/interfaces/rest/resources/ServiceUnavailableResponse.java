package pe.edu.upc.psychotest_platform.shared.interfaces.rest.resources;

/**
 * Response for service unavailable errors (HTTP 503).
 */
public record ServiceUnavailableResponse(
    int status,
    String error,
    String message
) {
}

