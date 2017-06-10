package com.modulr.java.test.eliasbalasis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class NoteHelper {

	public static Map<Note, Long> toNoteMap(final Collection<Note> noteList) {
		final Map<Note, Long> noteMap = new HashMap<>();
		for ( //
		Note note : noteList //
		) {
			Long amount = noteMap.get(note);
			if ( //
			amount == null //
			) {
				amount = Long.valueOf(0);
			}
			noteMap.put(note, amount.longValue() + 1);
		}
		return noteMap;
	}

	private NoteHelper() {
	}

}
