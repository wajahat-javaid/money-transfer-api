package com.simple.money.transfer.common.exception;

public class AccountCreationException extends RuntimeException {

	private static final long serialVersionUID = 7276233628993898029L;
	

	public AccountCreationException(final String title, Throwable e) {
		super( String.format( "Colud not create account for Title: %s", title), e );
	}
	
	public AccountCreationException(final String title ) {
		super( String.format( "Colud not create account for Title: %s", title ) );
	}

}
