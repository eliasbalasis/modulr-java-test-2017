package com.modulr.java.test.eliasbalasis;

import java.util.Collection;
import java.util.Map;

import com.modulr.java.test.eliasbalasis.exception.ATMOutOfNotesException;

/**
 * A single ATM's notes persistent storage service interface</br>
 * 
 * @author eliasbalasis
 */
public interface NoteRepository {

	/**
	 * Add notes to the persistent store</br>
	 * 
	 * @param noteList
	 */
	void addNoteList(final Collection<Note> noteList);

	/**
	 * Check if there are enough notes in the persistent store</br>
	 * 
	 * @param noteList
	 */
	void tryRemoveNoteList(final Collection<Note> noteList) throws ATMOutOfNotesException;

	/**
	 * Remove notes from the store</br>
	 * </br>
	 * A previous call to {@link #tryRemoveNoteList(Collection)} is assumed</br>
	 * 
	 * @param noteList
	 */
	void removeNoteList(final Collection<Note> noteList) throws ATMOutOfNotesException;

	Map<Note, Long> getDeposit();
}
