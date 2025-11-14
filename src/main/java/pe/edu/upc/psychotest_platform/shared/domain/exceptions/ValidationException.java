package pe.edu.upc.psychotest_platform.shared.domain.exceptions;

/**
 * Exception thrown when validation fails.
 */
public class ValidationException extends RuntimeException {

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}


