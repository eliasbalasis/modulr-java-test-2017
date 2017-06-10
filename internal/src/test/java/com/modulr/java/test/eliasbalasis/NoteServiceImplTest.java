package com.modulr.java.test.eliasbalasis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;

/**
 * Test for {@link NoteServiceImpl}
 * 
 * @author eliasbalasis
 */
public class NoteServiceImplTest {

	private NoteServiceImpl noteService;
	private NoteRepository noteRepository;

	@BeforeMethod
	public void setup() {
		noteService = new NoteServiceImpl();
		noteRepository = new NoteRepositoryMemoryImpl();
	}

	@Test(expectedExceptions = { WithdrawalAmountTranslationToNotesException.class })
	public void translateWithdrawalAmountImpossible() throws WithdrawalAmountTranslationToNotesException {
		noteService.translateWithdrawalAmount(12L, noteRepository);
	}

	@Test(expectedExceptions = { WithdrawalAmountTranslationToNotesException.class })
	public void translateWithdrawalAmountInsufficientNotes() throws WithdrawalAmountTranslationToNotesException {
		noteService.translateWithdrawalAmount(1000L, noteRepository);
	}

	private static final String DATA_PROVIDER = "parameters";

	@Test(dataProvider = DATA_PROVIDER)
	public void translateWithdrawalAmount( //
			final String name, //
			final Map<Note, Long> noteMap, //
			final long amount, //
			final Map<Note, Long> noteMapTranslationExpected //
	) throws WithdrawalAmountTranslationToNotesException {

		final Collection<Note> noteList = NoteHelper.toNoteList(noteMap);
		noteRepository.addNoteList(noteList);

		final Collection<Note> noteListTranslation = noteService.translateWithdrawalAmount(amount, noteRepository);

		final Map<Note, Long> noteMapTranslation = NoteHelper.toNoteMap(noteListTranslation);
		Assert.assertEquals( //
				noteMapTranslationExpected, //
				noteMapTranslation //
		);
	}

	@DataProvider(name = DATA_PROVIDER)
	public static Object[][] parameters() {
		final Collection<Object[]> parameters = new ArrayList<>();

		String name = null;
		Map<Note, Long> noteMap = null;
		long amount = 0;
		Map<Note, Long> noteMapTranslationExpected = null;

		name = "20atLeastOneFIVE";
		noteMap = new LinkedHashMap<>();
		noteMap.put(Note.TEN, 1L);
		noteMap.put(Note.FIVE, 2L);
		amount = 20L;
		noteMapTranslationExpected = new LinkedHashMap<>();
		noteMapTranslationExpected.put(Note.TEN, 1L);
		noteMapTranslationExpected.put(Note.FIVE, 2L);
		parameters.add( //
				new Object[] { //
						name, //
						noteMap, //
						amount, //
						noteMapTranslationExpected //
				} //
		);
		name = "20asTwoTEN";
		noteMap = new LinkedHashMap<>();
		noteMap.put(Note.TEN, 2L);
		amount = 20L;
		noteMapTranslationExpected = new LinkedHashMap<>();
		noteMapTranslationExpected.put(Note.TEN, 2L);
		parameters.add( //
				new Object[] { //
						name, //
						noteMap, //
						amount, //
						noteMapTranslationExpected //
				} //
		);
		name = "20asOneTWENTY";
		noteMap = new LinkedHashMap<>();
		noteMap.put(Note.TWENTY, 1L);
		amount = 20L;
		noteMapTranslationExpected = new LinkedHashMap<>();
		noteMapTranslationExpected.put(Note.TWENTY, 1L);
		parameters.add( //
				new Object[] { //
						name, //
						noteMap, //
						amount, //
						noteMapTranslationExpected //
				} //
		);

		return parameters.toArray(new Object[][] {});
	}
}
