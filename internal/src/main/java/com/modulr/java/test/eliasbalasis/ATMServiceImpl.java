package com.modulr.java.test.eliasbalasis;

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
	private NoteRepository noteRepository;

	/**
	 * The service to generate displayable balance</br>
	 */
	private BalanceFormatter balanceFormatter;

	public ATMServiceImpl( //
			final AccountService accountService, //
			final NoteService noteService, //
			final NoteRepository noteRepository, //
			final BalanceFormatter balanceFormatter) {
		this.accountService = accountService;
		this.noteService = noteService;
		this.noteRepository = noteRepository;
		this.balanceFormatter = balanceFormatter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.modulr.java.test.eliasbalasis.ATMService#replenish(java.util.
	 * Collection)
	 */
	@Override
	public void replenish(Collection<Note> noteList) {
		LOGGER.info( //
				"Replenishing ATM with notes: {}", //
				NoteHelper.toNoteMap(noteList) //
		);
		noteRepository.addNoteList(noteList);
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
		LOGGER.info( //
				"Checking balance of account '{}'", //
				accountNumber //
		);
		final Account account = accountService.getAccount(accountNumber);
		return balanceFormatter.formatBalance(account);
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
		LOGGER.info( //
				"Withdrawing amount of '{}' from account '{}'...", //
				withdrawalAmount, accountNumber //
		);
		final Collection<Note> noteList = //
				noteService.translateWithdrawalAmount( //
						withdrawalAmount, //
						noteRepository //
				);
		noteRepository.tryRemoveNoteList( //
				noteList //
		);
		accountService.withdrawAmount( //
				accountNumber, //
				withdrawalAmount //
		);
		noteRepository.removeNoteList( //
				noteList //
		);
		return noteList;
	}
}
