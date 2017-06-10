package com.modulr.java.test.eliasbalasis;

import java.util.Collection;

import com.modulr.java.test.eliasbalasis.exception.ATMOutOfNotesException;
import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;
import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountNotAllowedException;
import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;

/**
 * Service interface to handle ATM functions</br>
 * 
 * @author eliasbalasis
 */
public interface ATMService {

	/**
	 * Replenish ATM notes deposit</br>
	 * 
	 * @param noteList
	 *            The notes to replenish the ATM with
	 */
	void replenish(Collection<Note> noteList);

	/**
	 * Check account balance</br>
	 * 
	 * @param accountNumber
	 *            The account's number
	 * @return The balance as formatted text
	 * 
	 * @throws AccountNotFoundException
	 *             The account does not exist
	 */
	String checkBalance( //
			String accountNumber //
	) throws AccountNotFoundException;

	/**
	 * Withraw money from an account</br>
	 * 
	 * @param accountNumber
	 *            The account's number
	 * @param withdrawalAmount
	 *            The amount to withdraw
	 * @return The notes to collect
	 * 
	 * @throws AccountNotFoundException
	 *             The account does not exist
	 * @throws AccountBalanceNotEnoughException
	 *             The account does not have enough balance
	 * @throws ATMOutOfNotesException
	 *             The ATM has run out of notes
	 * @throws WithdrawalAmountTranslationToNotesException
	 *             The requested amount cannot be translated to notes
	 */
	Collection<Note> withdrawAmount( //
			String accountNumber, //
			long withdrawalAmount //
	) throws AccountNotFoundException, //
			AccountBalanceNotEnoughException, //
			ATMOutOfNotesException, //
			WithdrawalAmountTranslationToNotesException, //
			WithdrawalAmountNotAllowedException;
}
