package com.modulr.java.test.eliasbalasis;

import java.util.Collection;
import java.util.Collections;
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
public class NoteRepositoryMemoryImpl implements NoteRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoteRepositoryMemoryImpl.class);

	/**
	 * The underlying notes persistent storage</br>
	 * </br>
	 * Not so much persistent however.</br>
	 * Let's assume this connects to some small embedded RDBMS or other
	 * lightweight form of persistent store.</br>
	 */
	private Map<Note, Long> noteMap = new HashMap<>();

	/**
	 * Initialize an empty repository</br>
	 * 
	 * @param noteList
	 */
	public NoteRepositoryMemoryImpl() {
	}

	/**
	 * Initialize repository with notes</br>
	 * 
	 * @param noteList
	 */
	public NoteRepositoryMemoryImpl( //
			final Collection<Note> noteList //
	) {
		addNoteList(noteList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.NoteRepository#addNoteList(java.util.
	 * Collection)
	 */
	@Override
	public void addNoteList(final Collection<Note> noteList) {
		// Assuming no limit for notes capacity
		for (Note note : noteList) {
			Long amount = noteMap.get(note);
			if (amount == null) {
				// note not stored
				// assume zero size
				amount = Long.valueOf(0);
			}
			// increment
			// eliminating auto-boxing as much as possible
			amount = Long.valueOf(amount.longValue() + 1);
			// store
			noteMap.put(note, amount);
			LOGGER.info("Added a {} note to persistent store", note);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.NoteRepository#tryRemoveNoteList(java.
	 * util.Collection)
	 */
	@Override
	public void tryRemoveNoteList(final Collection<Note> noteList) throws ATMOutOfNotesException {
		final Map<Note, Long> noteMap = NoteHelper.toNoteMap(noteList);
		for ( //
		Map.Entry<Note, Long> noteMapEntry : noteMap.entrySet() //
		) {
			final Long amount = this.noteMap.get(noteMapEntry.getKey());
			if ( //
			amount == null //
			) {
				final ATMOutOfNotesException error = //
						new ATMOutOfNotesException( //
								noteMapEntry.getKey() //
						);
				LOGGER.error( //
						error.getMessage() //
				);
				throw error;
			}
			if ( //
			amount.longValue() //
			< //
			noteMapEntry.getValue().longValue() //
			) {
				final ATMOutOfNotesException error = //
						new ATMOutOfNotesException( //
								noteMapEntry.getKey() //
						);
				LOGGER.error( //
						error.getMessage() //
				);
				throw error;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.NoteRepository#removeNoteList(java.
	 * util.Collection)
	 */
	@Override
	public void removeNoteList(final Collection<Note> noteList) throws ATMOutOfNotesException {
		tryRemoveNoteList(noteList);
		for (Note note : noteList) {
			Long amount = noteMap.get(note);
			// decrement
			// eliminating auto-boxing as much as possible
			amount = Long.valueOf(amount.longValue() - 1);
			if (amount.longValue() == 0) {
				// zero notes left
				// remove note from store
				noteMap.remove(note);
			} else {
				// save change
				noteMap.put(note, amount);
			}
			LOGGER.info("Removed a {} note from persistent store", note);
		}
	}

	/**
	 * Entry point to underlying store</br>
	 * </br>
	 * 
	 * @return The remaining deposit for each type of note</br>
	 */
	@Override
	public Map<Note, Long> getDeposit() {
		return Collections.unmodifiableMap(noteMap);
	}
}
