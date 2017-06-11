package com.modulr.java.test.eliasbalasis;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Account object</br>
 * Holds information and manipulates balance</br>
 * 
 * @author eliasbalasis
 */
public class AccountImpl implements Account {

	private final String number;
	private BigDecimal balance;

	public AccountImpl(final String number, final BigDecimal balance) {
		this.number = number;
		this.balance = balance;
	}

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public BigDecimal getBalance() {
		return balance;
	}

	@Override
	public Currency getCurrency() {
		return Note.CURRENCY;
	}
}
