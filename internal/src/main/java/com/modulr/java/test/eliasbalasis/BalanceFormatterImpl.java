package com.modulr.java.test.eliasbalasis;

import org.slf4j.helpers.MessageFormatter;

public class BalanceFormatterImpl implements BalanceFormatter {

	@Override
	public String formatBalance(final Account account) {
		final String balanceToDisplay = //
				MessageFormatter.arrayFormat( //
						"{} {}", //
						new Object[] { //
								Note.CURRENCY.getSymbol(), //
								account.getBalance() //
						} //
				).getMessage();
		return balanceToDisplay;
	}
}
