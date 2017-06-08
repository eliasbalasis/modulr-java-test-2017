package com.modulr.java.test.eliasbalasis;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Account interface </br>
 * Holds information about an account, passed around among services</br>
 * 
 * @author eliasbalasis
 *
 */
public interface Account {

	/**
	 * Get The account number
	 * 
	 * @return The account number
	 */
	String getNumber();

	/**
	 * Get the account balance
	 * 
	 * @return The account balance
	 */
	BigDecimal getBalance();

	/**
	 * Get the account currency
	 * 
	 * @return The account currency
	 */
	Currency getCurrency();
}
