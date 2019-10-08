package com.simple.money.transfer.common.exception;

public class AccountAlreadyExistsException extends RuntimeException {


	private static final long serialVersionUID = -7495263162354168270L;

	public AccountAlreadyExistsException(Long id, Throwable e) 
	{
		super( String.format("Account with Id %s already exists", id), e );
	}

}
