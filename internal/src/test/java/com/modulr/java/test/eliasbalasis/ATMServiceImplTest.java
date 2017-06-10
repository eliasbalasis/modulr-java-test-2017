package com.modulr.java.test.eliasbalasis;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import com.modulr.java.test.eliasbalasis.exception.ATMOutOfNotesException;
import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;
import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;

/**
 * Test for {@link ATMServiceImpl}
 * 
 * @author eliasbalasis
 */
public class ATMServiceImplTest {

	private static final String ACCOUNT_NUMBER = "number";
	private static final long WITHDRAWAL_AMOUNT = 100L;

	private ATMServiceImpl service;

	private AccountService accountService;
	private NoteService noteService;
	private NoteRepository noteRepository;
	private BalanceFormatter balanceFormatter;

	private Account account;
	private Collection<Note> withdrawalNoteList;

	@Before
	public void setup() throws AccountNotFoundException, WithdrawalAmountTranslationToNotesException {
		accountService = Mockito.mock(AccountService.class);
		noteService = Mockito.mock(NoteService.class);
		noteRepository = Mockito.mock(NoteRepository.class);
		balanceFormatter = Mockito.mock(BalanceFormatter.class);
		account = Mockito.mock(Account.class);
		Mockito.doReturn( //
				account //
		).when( //
				accountService //
		).getAccount( //
				ACCOUNT_NUMBER //
		);
		withdrawalNoteList = //
				new NoteServiceImpl().translateWithdrawalAmount( //
						WITHDRAWAL_AMOUNT, //
						noteRepository //
				);
		Mockito.doReturn( //
				withdrawalNoteList //
		).when( //
				noteService //
		).translateWithdrawalAmount( //
				Mockito.eq(WITHDRAWAL_AMOUNT), //
				Mockito.same(noteRepository) //
		);
		service = //
				new ATMServiceImpl( //
						accountService, //
						noteService, //
						noteRepository, //
						balanceFormatter //
				);
	}

	@Test
	public void replenish() {
		final Collection<Note> noteList = //
				Arrays.asList( //
						Note.values() //
				);
		service.replenish(noteList);
	}

	@Test
	public void checkBalance() throws AccountNotFoundException {

		service.checkBalance(ACCOUNT_NUMBER);

		final InOrder inOrder = //
				Mockito.inOrder( //
						accountService, //
						balanceFormatter //
				);
		inOrder.verify( //
				accountService //
		).getAccount( //
				Mockito.same(ACCOUNT_NUMBER) //
		);
		inOrder.verify( //
				balanceFormatter //
		).formatBalance( //
				Mockito.same(account) //
		);
	}

	@Test
	public void withdrawAmount() throws AccountNotFoundException, //
			AccountBalanceNotEnoughException, //
			ATMOutOfNotesException, //
			WithdrawalAmountTranslationToNotesException //
	{
		service.withdrawAmount( //
				ACCOUNT_NUMBER, //
				WITHDRAWAL_AMOUNT //
		);

		final InOrder inOrder = //
				Mockito.inOrder( //
						noteService, //
						noteRepository, //
						accountService //
				);
		inOrder.verify( //
				noteService //
		).translateWithdrawalAmount( //
				Mockito.eq(WITHDRAWAL_AMOUNT), //
				Mockito.same(noteRepository) //
		);
		inOrder.verify( //
				noteRepository //
		).tryRemoveNoteList( //
				Mockito.same(withdrawalNoteList) //
		);
		inOrder.verify( //
				accountService //
		).withdrawAmount( //
				Mockito.same(ACCOUNT_NUMBER), //
				Mockito.eq(WITHDRAWAL_AMOUNT) //
		);
		inOrder.verify( //
				noteRepository //
		).removeNoteList( //
				Mockito.same(withdrawalNoteList) //
		);
	}
}
