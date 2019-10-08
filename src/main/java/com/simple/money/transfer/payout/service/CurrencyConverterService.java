package com.simple.money.transfer.payout.service;

import java.math.BigDecimal;

import com.simple.money.transfer.common.Currency;

public interface CurrencyConverterService {

	BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal amount );
}
