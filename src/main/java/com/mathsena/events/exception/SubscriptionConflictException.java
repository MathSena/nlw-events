package com.mathsena.events.exception;

public class SubscriptionConflictException extends RuntimeException {

  public SubscriptionConflictException(String message) {
    super(message);
  }

  public SubscriptionConflictException(String message, Throwable cause) {
    super(message, cause);
  }
}
