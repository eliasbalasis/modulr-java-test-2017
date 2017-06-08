package com.modulr.java.test.eliasbalasis;

import java.math.BigDecimal;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulr.java.test.eliasbalasis.exception.ATMOutOfNotesException;
import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;
import com.modulr.java.test.eliasbalasis.exception.WithdrawalAmountTranslationToNotesException;

/**
 * The service to handle ATM functions</br>
 * 
 * @author eliasbalasis
 */
public class ATMServiceImpl implements ATMService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ATMServiceImpl.class);

	/**
	 * The underlying accounts access service</br>
	 */
	private AccountService accountService;

	/**
	 * The underlying notes service</br>
	 */
	private NoteService noteService;

	/**
	 * The notes persistent storage service</br>
	 */
	private NotesRepository notesRepository;

	public ATMServiceImpl( //
			final AccountService accountService, //
			final NoteService noteService, //
			final NotesRepository notesRepository //
	) {
		this.accountService = accountService;
		this.noteService = noteService;
		this.notesRepository = notesRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.modulr.java.test.eliasbalasis.ATMService#replenish(java.util.
	 * Collection)
	 */
	@Override
	public void replenish(Collection<Note> noteList) {
		// TODO: provide more detailed message, this is only for demonstration
		// of logging framework use
		LOGGER.info("Replenishing ATM with {} notes", noteList.size());
		notesRepository.addNoteList(noteList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.modulr.java.test.eliasbalasis.ATMService#checkBalance(java.lang.
	 * String)
	 */
	@Override
	public String checkBalance( //
			final String accountNumber //
	) throws AccountNotFoundException {
		LOGGER.info("Checking balance of account '{}'", accountNumber);
		final Account account = accountService.getAccount(accountNumber);
		return formatBalance(account.getBalance());
	}

	@Override
	public Collection<Note> withdrawAmount( //
			final String accountNumber, //
			final long withdrawalAmount //
	) throws AccountNotFoundException, //
			AccountBalanceNotEnoughException, //
			ATMOutOfNotesException, //
			WithdrawalAmountTranslationToNotesException //
	{
		LOGGER.info("Withdrawing amount of '{}' from account '{}'", withdrawalAmount, accountNumber);
		final Collection<Note> noteList = noteService.translateWithdrawalAmount(withdrawalAmount);
		notesRepository.tryRemoveNoteList(noteList);
		accountService.withdrawAmount(accountNumber, withdrawalAmount);
		notesRepository.removeNoteList(noteList);
		return noteList;
	}

	String formatBalance(final BigDecimal balance) {
		return balance.toString();
	}
}
