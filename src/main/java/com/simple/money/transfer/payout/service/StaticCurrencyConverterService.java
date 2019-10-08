package com.simple.money.transfer.payout.service;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.simple.money.transfer.common.Currency;



/**
 * @author WJAVAID
 * Hard coded currency conversion between the three supported currencies
 */
public class StaticCurrencyConverterService implements CurrencyConverterService
{

	
	private static final Map<SimpleEntry<Currency,Currency>, BigDecimal> converter;
	
	static 
	{
		Map<SimpleEntry<Currency,Currency>, BigDecimal> coverterMap = new HashMap<>();
		coverterMap.put( new SimpleEntry<Currency, Currency>( Currency.EUR, Currency.GBP ), BigDecimal.valueOf( 0.89D ) );
		coverterMap.put( new SimpleEntry<Currency, Currency>( Currency.EUR, Currency.USD ), BigDecimal.valueOf( 1.10D ) );
		coverterMap.put( new SimpleEntry<Currency, Currency>( Currency.EUR, Currency.EUR ), BigDecimal.valueOf( 1D ) );
		coverterMap.put( new SimpleEntry<Currency, Currency>( Currency.GBP, Currency.GBP ), BigDecimal.valueOf( 1D ) );
		coverterMap.put( new SimpleEntry<Currency, Currency>( Currency.GBP, Currency.USD ), BigDecimal.valueOf( 1.23D ) );
		coverterMap.put( new SimpleEntry<Currency, Currency>( Currency.GBP, Currency.EUR ), BigDecimal.valueOf( 1.12D ) );
		coverterMap.put( new SimpleEntry<Currency, Currency>( Currency.USD, Currency.GBP ), BigDecimal.valueOf( 0.81D ) );
		coverterMap.put( new SimpleEntry<Currency, Currency>( Currency.USD, Currency.USD ), BigDecimal.valueOf( 1D ) );
		coverterMap.put( new SimpleEntry<Currency, Currency>( Currency.USD, Currency.EUR ), BigDecimal.valueOf( 0.91D ) );
		converter = Collections.unmodifiableMap( coverterMap );
	}
	
	
	
	@Override
	public BigDecimal convert( Currency sourceCurrency, Currency targetCurrency, BigDecimal amount ) 
	{
		return amount.multiply(converter.get( new SimpleEntry<Currency, Currency>( sourceCurrency, targetCurrency ) ) );
	}
	
}
