package com.modulr.java.test.eliasbalasis.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * Error to be raised when a withdrawal amount cannot be translated to ATM
 * notes</br>
 * 
 * @author eliasbalasis
 */
public class WithdrawalAmountTranslationToNotesException extends Exception {

	private static final long serialVersionUID = -8904349435565850109L;

	private final String message;

	public WithdrawalAmountTranslationToNotesException(final long amount) {
		final String message = //
				MessageFormatter.arrayFormat( //
						"Amount '{}' cannot be translated to notes", //
						new Object[] { //
								amount //
						} //
				).getMessage();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
