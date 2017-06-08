package com.modulr.java.test.eliasbalasis;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;

/**
 * The accounts store</br>
 * 
 * @author eliasbalasis
 */
public final class AccountRepositoryImpl implements AccountRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepositoryImpl.class);

	private static AccountImpl ACCOUNT_1 = new AccountImpl("01001", BigDecimal.valueOf(2738.59d));
	private static AccountImpl ACCOUNT_2 = new AccountImpl("01002", BigDecimal.valueOf(23.00d));
	private static AccountImpl ACCOUNT_3 = new AccountImpl("01003", BigDecimal.valueOf(0));

	private final Map<String, AccountImpl> accountMap = new LinkedHashMap<>();

	public static final AccountRepository DEFAULT = new AccountRepositoryImpl();

	private AccountRepositoryImpl() {
		accountMap.put(ACCOUNT_1.getNumber(), ACCOUNT_1);
		accountMap.put(ACCOUNT_2.getNumber(), ACCOUNT_2);
		accountMap.put(ACCOUNT_3.getNumber(), ACCOUNT_3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.AccountRepository#getAccount(java.lang.
	 * String)
	 */
	@Override
	public AccountImpl getAccount( //
			final String accountNumber //
	) throws AccountNotFoundException {
		LOGGER.info( //
				"Retrieving account with number '{}'", //
				accountNumber //
		);
		final AccountImpl account = accountMap.get(accountNumber);
		if (account == null) {
			LOGGER.info( //
					"Account with number '{}' does not exist", //
					accountNumber //
			);
			throw new AccountNotFoundException(accountNumber);
		}
		LOGGER.info( //
				"Account with number '{}' was found", //
				accountNumber //
		);
		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.modulr.java.test.eliasbalasis.AccountRepository#withdrawAmount(java.
	 * lang.String, long)
	 */
	@Override
	public void withdrawAmount( //
			final String accountNumber, //
			final long withdrawalAmount //
	) throws AccountNotFoundException, AccountBalanceNotEnoughException {
		LOGGER.info( //
				"Withdrawing amount of '{}' from account with number '{}'", //
				withdrawalAmount, accountNumber //
		);
		final AccountImpl account = getAccount(accountNumber);
		final BigDecimal balance = account.getBalance();
		if ( //
		balance.compareTo(BigDecimal.valueOf(withdrawalAmount)) < 0 //
		) {
			LOGGER.info( //
					"Account with number '{}' does not have enough balance", //
					accountNumber //
			);
			throw new AccountBalanceNotEnoughException(accountNumber, balance, withdrawalAmount);
		}
		account.setBalance(balance.subtract(BigDecimal.valueOf(withdrawalAmount)));
		LOGGER.info( //
				"Amount of '{}' was withdrawn from account with number '{}'", //
				withdrawalAmount, accountNumber //
		);
	}
}
