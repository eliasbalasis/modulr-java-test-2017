package com.modulr.java.test.eliasbalasis;

import java.util.Collection;

import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;

/**
 * Service interface for translation of amountParameter to notes of respective
 * denominations
 * 
 * @author eliasbalasis
 */
public interface NoteService {

	/**
	 * Translate a withdrawal amountParameter</br>
	 * to notes of respective denominations
	 * 
	 * @param amount
	 *            The amount to translate
	 * @param noteRepository
	 *            The underlying notes repository
	 * @return The notes list
	 * 
	 * @throws WithdrawalAmountTranslationToNotesException
	 *             Insufficient notes or impossible translation
	 */
	Collection<Note> translateWithdrawalAmount(long amount, NoteRepository noteRepository)
			throws WithdrawalAmountTranslationToNotesException;
}
