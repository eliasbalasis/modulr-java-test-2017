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
public final class AccountRepositoryMemoryImpl implements AccountRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepositoryMemoryImpl.class);

	public static final String ACCOUNT_1_NUMBER = "01001";
	public static final String ACCOUNT_2_NUMBER = "01002";
	public static final String ACCOUNT_3_NUMBER = "01003";

	public static final BigDecimal ACCOUNT_1_BALANCE = BigDecimal.valueOf(2738.59d);
	public static final BigDecimal ACCOUNT_2_BALANCE = BigDecimal.valueOf(23.00d);
	public static final BigDecimal ACCOUNT_3_BALANCE = BigDecimal.valueOf(0);

	private final Map<String, BigDecimal> accountBalanceMap = new LinkedHashMap<>();

	public static final AccountRepository DEFAULT = create();

	static AccountRepositoryMemoryImpl create() {
		return new AccountRepositoryMemoryImpl();
	}

	private AccountRepositoryMemoryImpl() {
		accountBalanceMap.put(ACCOUNT_1_NUMBER, ACCOUNT_1_BALANCE);
		accountBalanceMap.put(ACCOUNT_2_NUMBER, ACCOUNT_2_BALANCE);
		accountBalanceMap.put(ACCOUNT_3_NUMBER, ACCOUNT_3_BALANCE);
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
		final BigDecimal accountBalance = accountBalanceMap.get(accountNumber);
		if (accountBalance == null) {
			LOGGER.info( //
					"Account with number '{}' does not exist", //
					accountNumber //
			);
			throw new AccountNotFoundException(accountNumber);
		}
		final AccountImpl account = new AccountImpl(accountNumber, accountBalance);
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
				"Withdrawing amountParameter of '{}' from account with number '{}'", //
				withdrawalAmount, accountNumber //
		);
		final AccountImpl account = getAccount(accountNumber);
		final BigDecimal accountBalance = account.getBalance();
		if ( //
		accountBalance.compareTo(BigDecimal.valueOf(withdrawalAmount)) < 0 //
		) {
			LOGGER.info( //
					"Account with number '{}' does not have enough balance", //
					accountNumber //
			);
			throw new AccountBalanceNotEnoughException( //
					accountNumber, //
					accountBalance, //
					withdrawalAmount //
			);
		}
		final BigDecimal newAccountBalance = //
				accountBalance.subtract( //
						BigDecimal.valueOf(withdrawalAmount) //
				);
		accountBalanceMap.put( //
				accountNumber, //
				newAccountBalance //
		);
		LOGGER.info( //
				"Amount of '{}' was withdrawn from account with number '{}'", //
				withdrawalAmount, accountNumber //
		);
	}
}
