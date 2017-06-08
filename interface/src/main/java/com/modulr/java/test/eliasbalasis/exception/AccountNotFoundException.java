package com.modulr.java.test.eliasbalasis.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * Error to be raised when an account does not exist</br>
 * 
 * @author eliasbalasis
 */
public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = -8904349435565850109L;

	private final String message;

	public AccountNotFoundException(final String accountNumber) {
		final String message = //
				MessageFormatter.arrayFormat( //
						"Wrong account number '{}'", //
						new Object[] { //
								accountNumber //
						} //
				).getMessage();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
