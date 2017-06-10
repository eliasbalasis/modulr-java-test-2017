package com.modulr.java.test.eliasbalasis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modulr.java.test.eliasbalasis.exception.ATMOutOfNotesException;

/**
 * Test for {@link NoteRepositoryImpl}
 * 
 * @author eliasbalasis
 */
public class NoteRepositoryImplTest {

	private NoteRepositoryImpl noteRepository;

	@Before
	public void setup() {
		noteRepository = new NoteRepositoryImpl();
	}

	@Test
	public void addNoteList() {
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.values() //
				);
		noteRepository.addNoteList(noteList);
		final Map<Note, Long> noteMap = noteRepository.getDeposit();
		Assert.assertEquals( //
				noteMap.size(), //
				noteList.size() //
		);
		for (Note note : Note.values()) {
			Assert.assertEquals( //
					noteMap.get(note), //
					Long.valueOf(1) //
			);
		}
	}

	@Test
	public void addNoteListRepeatedly() {
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.values() //
				);
		noteRepository.addNoteList(noteList);
		noteRepository.addNoteList(noteList);
		final Map<Note, Long> noteMap = noteRepository.getDeposit();
		Assert.assertEquals( //
				noteMap.size(), //
				noteList.size() //
		);
		for (Note note : Note.values()) {
			Assert.assertEquals( //
					noteMap.get(note), //
					Long.valueOf(2) //
			);
		}
	}

	@Test
	public void tryRemoveNoteList() throws ATMOutOfNotesException {
		addNoteList();
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.FIVE, //
						Note.TEN, //
						Note.TWENTY, //
						Note.FIFTY //
				);
		noteRepository.tryRemoveNoteList(noteList);
	}

	@Test(expected = ATMOutOfNotesException.class)
	public void tryRemoveNoteListNotEnoughFIVE() throws ATMOutOfNotesException {
		addNoteList();
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.FIVE, //
						Note.FIVE //
				);
		noteRepository.tryRemoveNoteList(noteList);
	}

	@Test(expected = ATMOutOfNotesException.class)
	public void tryRemoveNoteListNotEnoughTEN() throws ATMOutOfNotesException {
		addNoteList();
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.TEN, //
						Note.TEN //
				);
		noteRepository.tryRemoveNoteList(noteList);
	}

	@Test(expected = ATMOutOfNotesException.class)
	public void tryRemoveNoteListNotEnoughTWENTY() throws ATMOutOfNotesException {
		addNoteList();
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.TWENTY, //
						Note.TWENTY //
				);
		noteRepository.tryRemoveNoteList(noteList);
	}

	@Test(expected = ATMOutOfNotesException.class)
	public void tryRemoveNoteListNotEnoughFIFTY() throws ATMOutOfNotesException {
		addNoteList();
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.FIFTY, //
						Note.FIFTY //
				);
		noteRepository.tryRemoveNoteList(noteList);
	}

	@Test
	public void removeNoteListFIVE() throws ATMOutOfNotesException {
		addNoteList();
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.FIVE //
				);
		noteRepository.removeNoteList(noteList);
		final Map<Note, Long> noteMap = noteRepository.getDeposit();
		Assert.assertNull(noteMap.get(Note.FIVE));
		Assert.assertNotNull(noteMap.get(Note.TEN));
		Assert.assertNotNull(noteMap.get(Note.TWENTY));
		Assert.assertNotNull(noteMap.get(Note.FIFTY));
	}

	@Test
	public void removeNoteListTEN() throws ATMOutOfNotesException {
		addNoteList();
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.TEN //
				);
		noteRepository.removeNoteList(noteList);
		final Map<Note, Long> noteMap = noteRepository.getDeposit();
		Assert.assertNotNull(noteMap.get(Note.FIVE));
		Assert.assertNull(noteMap.get(Note.TEN));
		Assert.assertNotNull(noteMap.get(Note.TWENTY));
		Assert.assertNotNull(noteMap.get(Note.FIFTY));
	}

	@Test
	public void removeNoteListTWENTY() throws ATMOutOfNotesException {
		addNoteList();
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.TWENTY //
				);
		noteRepository.removeNoteList(noteList);
		final Map<Note, Long> noteMap = noteRepository.getDeposit();
		Assert.assertNotNull(noteMap.get(Note.FIVE));
		Assert.assertNotNull(noteMap.get(Note.TEN));
		Assert.assertNull(noteMap.get(Note.TWENTY));
		Assert.assertNotNull(noteMap.get(Note.FIFTY));
	}

	@Test
	public void removeNoteListFIFTY() throws ATMOutOfNotesException {
		addNoteList();
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.FIFTY //
				);
		noteRepository.removeNoteList(noteList);
		final Map<Note, Long> noteMap = noteRepository.getDeposit();
		Assert.assertNotNull(noteMap.get(Note.FIVE));
		Assert.assertNotNull(noteMap.get(Note.TEN));
		Assert.assertNotNull(noteMap.get(Note.TWENTY));
		Assert.assertNull(noteMap.get(Note.FIFTY));
	}
}
