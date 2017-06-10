package com.modulr.java.test.eliasbalasis;

import java.util.ArrayList;
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

	public static Collection<Note> toNoteList(final Map<Note, Long> noteMap) {
		final Collection<Note> noteList = new ArrayList<>();
		if (noteMap.containsKey(Note.FIFTY)) {
			for (int index = 0; index < noteMap.get(Note.FIFTY); index++) {
				noteList.add(Note.FIFTY);
			}
		}
		if (noteMap.containsKey(Note.TWENTY)) {
			for (int index = 0; index < noteMap.get(Note.TWENTY); index++) {
				noteList.add(Note.TWENTY);
			}
		}
		if (noteMap.containsKey(Note.TEN)) {
			for (int index = 0; index < noteMap.get(Note.TEN); index++) {
				noteList.add(Note.TEN);
			}
		}
		if (noteMap.containsKey(Note.FIVE)) {
			for (int index = 0; index < noteMap.get(Note.FIVE); index++) {
				noteList.add(Note.FIVE);
			}
		}
		return noteList;
	}

	private NoteHelper() {
	}

}
