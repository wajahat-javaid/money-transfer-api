package com.simple.money.transfer.common.exception;

public class NotEnoughMoneyException extends RuntimeException {
  private final String accountNumber;

  public NotEnoughMoneyException(final String accountNumber) {
    this.accountNumber = accountNumber;
  }

  @Override public String getMessage() {
    return String.format("Not enough money in Account with number %s", accountNumber);
  }
}
