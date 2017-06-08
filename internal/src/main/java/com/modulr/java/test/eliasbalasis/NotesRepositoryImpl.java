package com.modulr.java.test.eliasbalasis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulr.java.test.eliasbalasis.exception.ATMOutOfNotesException;

/**
 * A single ATM's notes persistent storage service</br>
 * 
 * @author eliasbalasis
 */
public class NotesRepositoryImpl implements NotesRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotesRepositoryImpl.class);

	/**
	 * The underlying notes storage
	 */
	private Map<Note, Long> noteMap = new HashMap<>();

	/**
	 * Initialize repository with notes</br>
	 * 
	 * @param noteList
	 */
	public NotesRepositoryImpl( //
			final Collection<Note> noteList //
	) {
		addNoteList(noteList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.NotesRepository#addNoteList(java.util.
	 * Collection)
	 */
	@Override
	public void addNoteList(final Collection<Note> noteList) {
		// Assuming no limit for notes capacity
		for (Note note : noteList) {
			Long amount = noteMap.get(note);
			if (amount == null) {
				amount = Long.valueOf(0);
			}
			amount = amount.longValue() + note.getNumber();
			noteMap.put(note, amount);
		}
		// TODO: provide more detailed message, this is only for demonstration
		// of logging framework use
		LOGGER.info("Added {} notes to notes persistent store", noteList.size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.NotesRepository#tryRemoveNoteList(java.
	 * util.Collection)
	 */
	@Override
	public void tryRemoveNoteList(final Collection<Note> noteList) throws ATMOutOfNotesException {
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
		for ( //
		Map.Entry<Note, Long> noteMapEntry : noteMap.entrySet() //
		) {
			final Long amount = this.noteMap.get(noteMapEntry.getKey());
			if ( //
			amount == null //
			) {
				throw new ATMOutOfNotesException( //
						noteMapEntry.getKey() //
				);
			}
			if ( //
			amount.longValue() //
			< //
			noteMapEntry.getValue().longValue() //
			) {
				throw new ATMOutOfNotesException(noteMapEntry.getKey());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.NotesRepository#removeNoteList(java.
	 * util.Collection)
	 */
	@Override
	public void removeNoteList(final Collection<Note> noteList) throws ATMOutOfNotesException {
		for (Note note : noteList) {
			Long amount = noteMap.get(note);
			if (amount == null) {
				amount = Long.valueOf(0);
			}
			amount = amount.longValue() + note.getNumber();
			noteMap.put(note, amount);
		}
		// TODO: provide more detailed message, this is only for demonstration
		// of logging framework use
		LOGGER.info("Removed {} notes to notes persistent store", noteList.size());
	}
}
