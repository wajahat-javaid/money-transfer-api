package com.simple.money.transfer.common.exception;

public class AccountNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 7276233628993898029L;
	

	public AccountNotExistsException(final Long id, Throwable e) {
		super( String.format( "Account with ID %s does not exist", id), e );
	}
	
	public AccountNotExistsException(final Long id ) {
		super( String.format( "Account with ID %s does not exist", id) );
	}

}
