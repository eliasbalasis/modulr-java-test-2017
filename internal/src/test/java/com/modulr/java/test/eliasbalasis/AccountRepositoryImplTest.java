package com.modulr.java.test.eliasbalasis;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;

/**
 * Test for {@link AccountRepositoryImpl}
 * 
 * @author eliasbalasis
 */
public class AccountRepositoryImplTest {

	private AccountRepository accountRepository;

	@Before
	public void setup() {
		accountRepository = AccountRepositoryImpl.create();
	}

	@Test(expected = AccountNotFoundException.class)
	public void getAccountNotFound() throws AccountNotFoundException {
		accountRepository.getAccount("unknown");
	}

	@Test
	public void getAccount1() throws AccountNotFoundException {
		final Account account = accountRepository.getAccount(AccountRepositoryImpl.ACCOUNT_1_NUMBER);
		Assert.assertSame(AccountRepositoryImpl.ACCOUNT_1, account);
	}

	@Test
	public void getAccount2() throws AccountNotFoundException {
		final Account account = accountRepository.getAccount(AccountRepositoryImpl.ACCOUNT_2_NUMBER);
		Assert.assertSame(AccountRepositoryImpl.ACCOUNT_2, account);
	}

	@Test
	public void getAccount3() throws AccountNotFoundException {
		final Account account = accountRepository.getAccount(AccountRepositoryImpl.ACCOUNT_3_NUMBER);
		Assert.assertSame(AccountRepositoryImpl.ACCOUNT_3, account);
	}

	@Test
	public void withdrawAmount() throws AccountNotFoundException, AccountBalanceNotEnoughException {
		accountRepository.withdrawAmount(AccountRepositoryImpl.ACCOUNT_1_NUMBER, 500L);
		final Account account = accountRepository.getAccount(AccountRepositoryImpl.ACCOUNT_1_NUMBER);
		Assert.assertEquals( //
				AccountRepositoryImpl.ACCOUNT_1_BALANCE.subtract( //
						BigDecimal.valueOf(500L) //
				), //
				account.getBalance() //
		);
	}

	@Test(expected = AccountBalanceNotEnoughException.class)
	public void withdrawAmountNotEnoughBalance() throws AccountNotFoundException, AccountBalanceNotEnoughException {
		accountRepository.withdrawAmount(AccountRepositoryImpl.ACCOUNT_1_NUMBER, 5000L);
	}
}
