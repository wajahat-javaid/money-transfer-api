package com.simple.money.transfer.common.exception;

public class TransferToTheSameAccountException extends RuntimeException {
  @Override public String getMessage() {
    return "Sender and Receiver account numbers must be different";
  }
}
