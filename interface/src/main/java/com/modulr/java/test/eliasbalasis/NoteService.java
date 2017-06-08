package com.modulr.java.test.eliasbalasis;

import java.util.Collection;

import com.modulr.java.test.eliasbalasis.exception.AmountTranslationToNotesException;
import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;

/**
 * Service interface to translate amount to notes</br>
 * 
 * @author eliasbalasis
 */
public interface NoteService {

	/**
	 * Translate an amount of money to ATM notes</br>
	 * 
	 * @param amount
	 *            The amount to be translated
	 * @return The notes
	 * 
	 * @throws AmountTranslationToNotesException
	 *             The amount cannot be translated to notes
	 */
	Collection<Note> translateAmount(long amount) throws AmountTranslationToNotesException;

	/**
	 * Translate a withdrawal amount to ATM notes</br>
	 * 
	 * @param amount
	 *            The amount to be translated
	 * @return The notes
	 * 
	 * @throws WithdrawalAmountTranslationToNotesException
	 *             The amount cannot be translated to notes
	 */
	Collection<Note> translateWithdrawalAmount(long amount) throws WithdrawalAmountTranslationToNotesException;
}
