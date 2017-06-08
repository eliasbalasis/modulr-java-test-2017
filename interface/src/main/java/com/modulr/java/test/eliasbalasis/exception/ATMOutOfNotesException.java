package com.modulr.java.test.eliasbalasis.exception;

import org.slf4j.helpers.MessageFormatter;

import com.modulr.java.test.eliasbalasis.Note;

/**
 * Error to be raised when ATM runs out of notes</br>
 * 
 * @author eliasbalasis
 */
public class ATMOutOfNotesException extends Exception {

	private static final long serialVersionUID = -8904349435565850109L;

	private final String message;

	public ATMOutOfNotesException(final Note note) {
		final String message = //
				MessageFormatter.arrayFormat( //
						"Not enough notes of value '{}' in ATM", //
						new Object[] { //
								note.getNumber() //
						} //
				).getMessage();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
