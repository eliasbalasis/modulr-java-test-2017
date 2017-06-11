package com.modulr.java.test.eliasbalasis.test;

import java.util.LinkedHashMap;
import java.util.Map;

import com.modulr.java.test.eliasbalasis.Note;

public enum TranslateWithdrawalAmountTestData {
	Translate20asAtLeastOneFIVE( //
			20L //
	) {

		@Override
		public Map<Note, Long> getNoteMap() {
			final Map<Note, Long> noteMap = new LinkedHashMap<>();
			noteMap.put(Note.TEN, 1L);
			noteMap.put(Note.FIVE, 2L);
			return noteMap;
		}

		@Override
		public Map<Note, Long> getNoteMapTranslation() {
			final Map<Note, Long> noteMapTranslation = new LinkedHashMap<>();
			noteMapTranslation.put(Note.TEN, 1L);
			noteMapTranslation.put(Note.FIVE, 2L);
			return noteMapTranslation;
		}

	},
	Translate20asTwoTEN( //
			20L //
	) {

		@Override
		public Map<Note, Long> getNoteMap() {
			final Map<Note, Long> noteMap = new LinkedHashMap<>();
			noteMap.put(Note.TEN, 2L);
			return noteMap;
		}

		@Override
		public Map<Note, Long> getNoteMapTranslation() {
			final Map<Note, Long> noteMapTranslation = new LinkedHashMap<>();
			noteMapTranslation.put(Note.TEN, 2L);
			return noteMapTranslation;
		}

	},
	Translate20asOneTWENTY( //
			20L //
	) {

		@Override
		public Map<Note, Long> getNoteMap() {
			final Map<Note, Long> noteMap = new LinkedHashMap<>();
			noteMap.put(Note.TWENTY, 1L);
			return noteMap;
		}

		@Override
		public Map<Note, Long> getNoteMapTranslation() {
			final Map<Note, Long> noteMapTranslation = new LinkedHashMap<>();
			noteMapTranslation.put(Note.TWENTY, 1L);
			return noteMapTranslation;
		}

	}
	// TODO: Add more test cases
	;

	private final long amount;

	private TranslateWithdrawalAmountTestData(final long amount) {
		this.amount = amount;
	}

	public final long getAmount() {
		return amount;
	}

	public abstract Map<Note, Long> getNoteMap();

	public abstract Map<Note, Long> getNoteMapTranslation();

	public final Object[] getParameters() {
		return //
		new Object[] { //
				name(), //
				getNoteMap(), //
				getAmount(), //
				getNoteMapTranslation() //
		};
	}
}
