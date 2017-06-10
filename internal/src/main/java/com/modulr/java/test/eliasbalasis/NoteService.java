package com.modulr.java.test.eliasbalasis;

import java.util.Collection;

import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;

public interface NoteService {

	Collection<Note> translateWithdrawalAmount(long amount, NoteRepository noteRepository)
			throws WithdrawalAmountTranslationToNotesException;
}
