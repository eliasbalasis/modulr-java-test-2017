package com.modulr.java.test.eliasbalasis;

import java.util.Collection;

import com.modulr.java.test.eliasbalasis.exception.AmountTranslationToNotesException;
import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;

public interface NoteService {

	Collection<Note> translateAmount(long amount) throws AmountTranslationToNotesException;

	Collection<Note> translateWithdrawalAmount(long amount) throws WithdrawalAmountTranslationToNotesException;
}
