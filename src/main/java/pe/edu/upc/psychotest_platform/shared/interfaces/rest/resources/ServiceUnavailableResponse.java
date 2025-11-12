package pe.edu.upc.psychotest_platform.shared.interfaces.rest.resources;

public record ServiceUnavailableResponse(
    int status, String error, String message
) {
}
