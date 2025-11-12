package pe.edu.upc.psychotest_platform.shared.domain.exceptions;

public class NotFoundArgumentException extends RuntimeException {

  public NotFoundArgumentException(String message) {
    super(message);
  }

}