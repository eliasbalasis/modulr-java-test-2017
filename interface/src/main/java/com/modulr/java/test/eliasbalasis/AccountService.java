package com.modulr.java.test.eliasbalasis;

import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;

/**
 * Service interface to access accounts</br>
 * 
 * @author eliasbalasis
 */
public interface AccountService {

	/**
	 * Retrieve account</br>
	 * 
	 * @param accountNumber
	 *            The accounts number
	 * @return The matching account
	 * 
	 * @throws AccountNotFoundException
	 *             The account does not exist
	 */
	Account getAccount( //
			String accountNumber //
	) throws AccountNotFoundException;

	/**
	 * Withdraw amount from an account
	 * 
	 * @param accountNumber
	 *            The account's number
	 * @param withdrawalAmount
	 *            The amount to withdraw
	 * @throws AccountBalanceNotEnoughException
	 *             The account does not have enough balance
	 * @throws AccountNotFoundException
	 *             The account does not exist
	 */
	void withdrawAmount( //
			String accountNumber, //
			long withdrawalAmount //
	) throws AccountBalanceNotEnoughException, //
			AccountNotFoundException;
}
