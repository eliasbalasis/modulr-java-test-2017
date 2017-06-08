package com.modulr.java.test.eliasbalasis.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * Error to be raised when a amount cannot be translated to ATM notes</br>
 * 
 * @author eliasbalasis
 */
public class AmountTranslationToNotesException extends Exception {

	private static final long serialVersionUID = -8904349435565850109L;

	private final String message;

	public AmountTranslationToNotesException(final long amount) {
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
