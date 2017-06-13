package com.modulr.java.test.eliasbalasis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;
import com.modulr.java.test.eliasbalasis.test.TranslateWithdrawalAmountTestData;

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
			final Map<Note, Long> noteMapTranslationExpected, //
			final boolean isErrorExpected //
	) throws WithdrawalAmountTranslationToNotesException {

		final Collection<Note> noteList = NoteHelper.toNoteList(noteMap);
		noteRepository.addNoteList(noteList);

		Collection<Note> noteListTranslation = null;
		try {
			noteListTranslation = noteService.translateWithdrawalAmount(amount, noteRepository);
		} catch (WithdrawalAmountTranslationToNotesException cause) {
			if (!isErrorExpected) {
				throw cause;
			}
		}

		final Map<Note, Long> noteMapTranslation = NoteHelper.toNoteMap(noteListTranslation);
		Assert.assertEquals( //
				noteMapTranslation, //
				noteMapTranslationExpected //
		);
	}

	@DataProvider(name = DATA_PROVIDER)
	public static Object[][] parameters() {
		final Collection<Object[]> parameters = new ArrayList<>();
		for ( //
		TranslateWithdrawalAmountTestData testData : TranslateWithdrawalAmountTestData.values() //
		) {
			parameters.add( //
					testData.getParameters() //
			);
		}
		return parameters.toArray(new Object[][] {});
	}
}
