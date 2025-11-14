package pe.edu.upc.psychotest_platform.test.interfaces.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.edu.upc.psychotest_platform.shared.domain.exceptions.NotFoundIdException;
import pe.edu.upc.psychotest_platform.shared.interfaces.rest.resources.NotFoundResponse;

@RestControllerAdvice(basePackages = "pe.edu.upc.psychotest_platform.test")
public class TestExceptionHandler {

    @ExceptionHandler(NotFoundIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<NotFoundResponse> handleNotFound(NotFoundIdException ex) {
        var response = new NotFoundResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<NotFoundResponse> handleIllegalArgument(IllegalArgumentException ex) {
        var response = new NotFoundResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
