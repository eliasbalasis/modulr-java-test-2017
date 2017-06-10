package com.modulr.java.test.eliasbalasis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;

/**
 * The accounts access service</br>
 * 
 * @author eliasbalasis
 */
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	/**
	 * The underlying accounts repository</br>
	 */
	private final AccountRepository accountRepository;

	public AccountServiceImpl() {
		accountRepository = AccountRepositoryMemoryImpl.DEFAULT;
	}

	AccountServiceImpl(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.AccountService#getAccount(java.lang.
	 * String)
	 */
	@Override
	public Account getAccount( //
			final String accountNumber //
	) throws AccountNotFoundException {
		LOGGER.info( //
				"Retrieving account with number '{}'", //
				accountNumber //
		);
		final Account account = accountRepository.getAccount(accountNumber);
		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.AccountService#withdrawAmount(java.lang
	 * .String, long)
	 */
	@Override
	public void withdrawAmount( //
			final String accountNumber, //
			final long withdrawalAmount //
	) throws AccountBalanceNotEnoughException, //
			AccountNotFoundException {
		LOGGER.info( //
				"Withdrawing amountParameter of '{}' from account with number '{}'", //
				withdrawalAmount, accountNumber //
		);
		accountRepository.withdrawAmount(accountNumber, withdrawalAmount);
	}
}
