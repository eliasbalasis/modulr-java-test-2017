package com.modulr.java.test.eliasbalasis;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.modulr.java.test.eliasbalasis.exception.AccountBalanceNotEnoughException;
import com.modulr.java.test.eliasbalasis.exception.AccountNotFoundException;

/**
 * Test for {@link AccountServiceImpl}
 * 
 * @author eliasbalasis
 */
public class AccountServiceImplTest {

	private static final String ACCOUNT_NUMBER = "number";
	private static final BigDecimal ACCOUNT_BALANCE = BigDecimal.ZERO;

	private AccountServiceImpl accountService;

	private AccountRepository accountRepository;

	private Account account;

	@Before
	public void setup() throws AccountNotFoundException {
		accountRepository = Mockito.mock(AccountRepository.class);
		account = //
				new AccountImpl( //
						ACCOUNT_NUMBER, //
						ACCOUNT_BALANCE //
				);
		Mockito.doReturn( //
				account //
		).when( //
				accountRepository //
		).getAccount( //
				Mockito.eq(ACCOUNT_NUMBER) //
		);
		accountService = new AccountServiceImpl(accountRepository);
	}

	@Test
	public void getAccount() throws AccountNotFoundException {
		final Account account = accountService.getAccount(ACCOUNT_NUMBER);
		Mockito.verify( //
				accountRepository //
		).getAccount( //
				Mockito.same(ACCOUNT_NUMBER) //
		);
		Assert.assertSame( //
				this.account, //
				account //
		);
	}

	@Test
	public void withdrawAmount() throws AccountBalanceNotEnoughException, AccountNotFoundException {
		accountService.withdrawAmount( //
				ACCOUNT_NUMBER, //
				ACCOUNT_BALANCE.longValue() //
		);
		Mockito.verify( //
				accountRepository //
		).withdrawAmount( //
				Mockito.same(ACCOUNT_NUMBER), //
				Mockito.eq(ACCOUNT_BALANCE.longValue()) //
		);
	}
}
