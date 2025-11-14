package pe.edu.upc.psychotest_platform.shared.domain.exceptions;

/**
 * Exception thrown when a persistence operation fails.
 */
public class PersistenceException extends RuntimeException {

  public PersistenceException(String message) {
    super(message);
  }

  public PersistenceException(String message, Throwable cause) {
    super(message, cause);
  }
}

