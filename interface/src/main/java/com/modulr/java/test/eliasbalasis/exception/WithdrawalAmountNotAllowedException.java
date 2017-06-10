package com.modulr.java.test.eliasbalasis.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * Error to be raised when a withdrawal amount cannot be translated to ATM
 * notes</br>
 * 
 * @author eliasbalasis
 */
public class WithdrawalAmountNotAllowedException extends Exception {

	private static final long serialVersionUID = -8904349435565850109L;

	private final String message;

	public WithdrawalAmountNotAllowedException(final long amount, final long minimum, final long maximum) {
		final String message = //
				MessageFormatter.arrayFormat( //
						"Withdrawal amount '{}' is not within the allowed range ({}-{})", //
						new Object[] { //
								amount, //
								minimum, maximum //
						} //
				).getMessage();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
