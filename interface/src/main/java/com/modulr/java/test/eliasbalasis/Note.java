package com.modulr.java.test.eliasbalasis;

import java.util.Currency;

/**
 * A note</br>
 * 
 * @author eliasbalasis
 */
public enum Note {
	FIVE(5), //
	TEN(10), //
	TWENTY(20), //
	FIFTY(50);

	/**
	 * The currency, assuming GBP
	 */
	public static final Currency CURRENCY = Currency.getInstance("GBP");

	/**
	 * The value of the note
	 */
	private int number;

	private Note(final int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
