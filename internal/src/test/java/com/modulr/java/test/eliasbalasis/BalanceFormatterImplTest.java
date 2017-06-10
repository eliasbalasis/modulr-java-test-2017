package com.modulr.java.test.eliasbalasis;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link BalanceFormatter}
 * 
 * @author eliasbalasis
 */
public class BalanceFormatterImplTest {

	private static final String ACCOUNT_NUMBER = "number";
	private static final BigDecimal ACCOUNT_BALANCE = BigDecimal.valueOf(100L);

	private static final String BALANCE_TO_DISPLAY = Note.CURRENCY.getSymbol() + " 100";

	private BalanceFormatterImpl balanceFormatter;

	private Account account;

	@Before
	public void setup() {
		balanceFormatter = new BalanceFormatterImpl();
		account = new AccountImpl(ACCOUNT_NUMBER, ACCOUNT_BALANCE);
	}

	@Test
	public void formatBalance() {
		final String balanceToDisplay = balanceFormatter.formatBalance(account);
		Assert.assertEquals(BALANCE_TO_DISPLAY, balanceToDisplay);
	}
}
