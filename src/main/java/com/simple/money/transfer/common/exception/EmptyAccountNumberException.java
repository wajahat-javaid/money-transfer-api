package com.simple.money.transfer.common.exception;

public class EmptyAccountNumberException extends RuntimeException {
  @Override public String getMessage() {
    return "Empty account number";
  }
}
