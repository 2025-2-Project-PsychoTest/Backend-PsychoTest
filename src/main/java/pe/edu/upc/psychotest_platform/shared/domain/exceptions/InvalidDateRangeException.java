package pe.edu.upc.psychotest_platform.shared.domain.exceptions;

/**
 * Exception thrown when a date range is invalid.
 * For example, when the end date is before the start date.
 */
public class InvalidDateRangeException extends RuntimeException {

  public InvalidDateRangeException(String message) {
    super(message);
  }

  public InvalidDateRangeException(String message, Throwable cause) {
    super(message, cause);
  }
}

