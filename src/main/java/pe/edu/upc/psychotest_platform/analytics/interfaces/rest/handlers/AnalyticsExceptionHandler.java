package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.edu.upc.psychotest_platform.shared.domain.exceptions.AnalyticsNotFoundException;
import pe.edu.upc.psychotest_platform.shared.interfaces.rest.resources.NotFoundResponse;

@RestControllerAdvice(basePackages = "pe.edu.upc.psychotest_platform.analytics")
public class AnalyticsExceptionHandler {

    @ExceptionHandler(AnalyticsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<NotFoundResponse> handleAnalyticsNotFound(AnalyticsNotFoundException ex) {
        var response = new NotFoundResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
