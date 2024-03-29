package com.cfm.Yolo.intern.exception;

public class InvalidPasswordException extends RuntimeException {
  
  /**
   * @param message
   */
  public InvalidPasswordException(String message) {
    super(message);
  }
  
  /**
   * @param message
   * @param cause
   */
  public InvalidPasswordException(String message, Throwable cause) {
    super(message, cause);
  }

}
