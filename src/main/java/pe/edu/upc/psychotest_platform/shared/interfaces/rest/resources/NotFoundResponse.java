package pe.edu.upc.psychotest_platform.shared.interfaces.rest.resources;

public record NotFoundResponse(
    int status, String error, String message
) {
}
