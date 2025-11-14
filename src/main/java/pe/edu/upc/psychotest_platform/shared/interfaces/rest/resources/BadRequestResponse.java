package pe.edu.upc.psychotest_platform.shared.interfaces.rest.resources;

import java.util.Map;

/**
 * Response for bad request errors (HTTP 400).
 */
public record BadRequestResponse(
    int status,
    String error,
    String message,
    Map<String, String> fieldErrors
) {
}

