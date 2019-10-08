package com.simple.money.transfer.account.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

import com.simple.money.transfer.common.Currency;

public class Account
{
    private Long id;
    @Override
	public String toString() 
    {
		return "Account [id=" + id + ", title=" + title + ", balance=" + balance + ", blockedAmount=" + blockedAmount
				+ ", currency=" + currency + "]";
	}

	private String title;
    private BigDecimal balance;
    private BigDecimal blockedAmount;
    private Currency currency;

    public Account(){}

    public Account(String ownerName, BigDecimal balance, BigDecimal blockedAmount, Currency currency) 
    {
        this(new Random().nextLong(), ownerName, balance, blockedAmount, currency);
    }

    public Account(Long id, String title, BigDecimal balance, BigDecimal blockedAmount, Currency currency) 
    {
        this.id = id;
        this.title = title;
        this.balance = balance;
        this.blockedAmount = blockedAmount;
        this.currency = currency;
    }

    public Account(Long id, String ownerName) 
    {
        this.id = id;
        this.title = ownerName;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public BigDecimal getBalance() 
    {
        return balance;
    }

    public void setBalance(BigDecimal balance) 
    {
        this.balance = balance;
    }

    public BigDecimal getBlockedAmount() 
    {
        return blockedAmount;
    }

    public void setBlockedAmount(BigDecimal blockedAmount) 
    {
        this.blockedAmount = blockedAmount;
    }

    public Currency getCurrency() 
    {
        return currency;
    }

    public void setCurrency(Currency currency) 
    {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return Objects.equals( title, that.getTitle() ) && Objects.equals( getId(), that.getId() ) ;
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash( id, title, currency );
    }
}
