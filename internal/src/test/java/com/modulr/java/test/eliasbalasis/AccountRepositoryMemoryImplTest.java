package com.modulr.java.test.eliasbalasis;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;

/**
 * Test for {@link AccountRepositoryMemoryImpl}
 * 
 * @author eliasbalasis
 */
public class AccountRepositoryMemoryImplTest {

	private AccountRepositoryMemoryImpl accountRepository;

	@BeforeMethod
	public void setup() {
		accountRepository = AccountRepositoryMemoryImpl.create();
	}

	@Test(expectedExceptions = { AccountNotFoundException.class })
	public void getAccountNotFound() throws AccountNotFoundException {
		accountRepository.getAccount("unknown");
	}

	@Test
	public void getAccount1() throws AccountNotFoundException {
		final Account account = accountRepository.getAccount(AccountRepositoryMemoryImpl.ACCOUNT_1_NUMBER);
		Assert.assertEquals(account.getNumber(), AccountRepositoryMemoryImpl.ACCOUNT_1_NUMBER);
		Assert.assertEquals(account.getBalance(), AccountRepositoryMemoryImpl.ACCOUNT_1_BALANCE);
	}

	@Test
	public void getAccount2() throws AccountNotFoundException {
		final Account account = accountRepository.getAccount(AccountRepositoryMemoryImpl.ACCOUNT_2_NUMBER);
		Assert.assertEquals(account.getNumber(), AccountRepositoryMemoryImpl.ACCOUNT_2_NUMBER);
		Assert.assertEquals(account.getBalance(), AccountRepositoryMemoryImpl.ACCOUNT_2_BALANCE);
	}

	@Test
	public void getAccount3() throws AccountNotFoundException {
		final Account account = accountRepository.getAccount(AccountRepositoryMemoryImpl.ACCOUNT_3_NUMBER);
		Assert.assertEquals(account.getNumber(), AccountRepositoryMemoryImpl.ACCOUNT_3_NUMBER);
		Assert.assertEquals(account.getBalance(), AccountRepositoryMemoryImpl.ACCOUNT_3_BALANCE);
	}

	@Test
	public void withdrawAmount() throws AccountNotFoundException, AccountBalanceNotEnoughException {
		accountRepository.withdrawAmount(AccountRepositoryMemoryImpl.ACCOUNT_1_NUMBER, 500L);
		final Account account = accountRepository.getAccount(AccountRepositoryMemoryImpl.ACCOUNT_1_NUMBER);
		Assert.assertEquals( //
				AccountRepositoryMemoryImpl.ACCOUNT_1_BALANCE.subtract( //
						BigDecimal.valueOf(500L) //
				), //
				account.getBalance() //
		);
	}

	@Test(expectedExceptions = { AccountBalanceNotEnoughException.class })
	public void withdrawAmountNotEnoughBalance() throws AccountNotFoundException, AccountBalanceNotEnoughException {
		accountRepository.withdrawAmount(AccountRepositoryMemoryImpl.ACCOUNT_1_NUMBER, 5000L);
	}
}
