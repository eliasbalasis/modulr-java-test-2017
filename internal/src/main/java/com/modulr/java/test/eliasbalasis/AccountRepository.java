package com.modulr.java.test.eliasbalasis;

import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;

/**
 * The accounts store interface</br>
 * 
 * @author eliasbalasis
 */
public interface AccountRepository {

	/**
	 * Retrieve an account from the repository</br>
	 * 
	 * @param accountNumber
	 *            The account's number
	 * 
	 * @return The account found in the repository
	 * 
	 * @throws AccountNotFoundException
	 *             The account does not exist
	 */
	Account getAccount( //
			String accountNumber //
	) throws AccountNotFoundException;

	/**
	 * Withdraw money from a repository account</br>
	 * 
	 * @param accountNumber
	 *            The account's number
	 * @param withdrawalAmount
	 *            The amountParameter to withdraw
	 * @throws AccountNotFoundException
	 *             The account does not exist
	 * @throws AccountBalanceNotEnoughException
	 *             The account does not have enough balance
	 */
	void withdrawAmount( //
			String accountNumber, //
			long withdrawalAmount //
	) throws AccountNotFoundException, //
			AccountBalanceNotEnoughException;
}
