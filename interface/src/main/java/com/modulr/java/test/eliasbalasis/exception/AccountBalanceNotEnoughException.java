package com.modulr.java.test.eliasbalasis.exception;

import java.math.BigDecimal;

import org.slf4j.helpers.MessageFormatter;

/**
 * Error to be raised when an account does not have enough balance</br>
 * 
 * @author eliasbalasis
 */
public class AccountBalanceNotEnoughException extends Exception {

	private static final long serialVersionUID = -8904349435565850109L;

	private final String message;

	public AccountBalanceNotEnoughException(final String accountNumber, final BigDecimal balance,
			final long withdrawalAmount) {
		final String message = //
				MessageFormatter.arrayFormat( //
						"Account '{}' does not have enough balance. Requested '{}' but was only '{}'", //
						new Object[] { //
								accountNumber, withdrawalAmount, balance //
						} //
				).getMessage();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
