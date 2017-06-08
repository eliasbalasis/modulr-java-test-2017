package com.modulr.java.test.eliasbalasis;

import java.util.ArrayList;
import java.util.Collection;

import com.modulr.java.test.eliasbalasis.exception.AmountTranslationToNotesException;
import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;

public class NoteServiceImpl implements NoteService {

	@Override
	public Collection<Note> translateAmount( //
			final long amount //
	) throws AmountTranslationToNotesException {
		final Collection<Note> noteList = new ArrayList<>();
		return noteList;
	}

	@Override
	public Collection<Note> translateWithdrawalAmount( //
			final long amount //
	) throws WithdrawalAmountTranslationToNotesException {
		final Collection<Note> noteList = new ArrayList<>();
		return noteList;
	}
}
