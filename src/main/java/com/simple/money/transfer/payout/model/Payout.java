package com.simple.money.transfer.payout.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import com.simple.money.transfer.common.Currency;

public class Payout {
    private Long id;
    private Long fromBankAccountId;
    private Long toBankAccountId;
    private BigDecimal amount;
    private Currency currency;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private PayoutStatus status;
    private String description;

    public Payout() 
    {
        this.creationDate = LocalDateTime.now(ZoneOffset.UTC);
        this.updateDate = LocalDateTime.now(ZoneOffset.UTC);
        this.status = PayoutStatus.PROCESSING;
        this.description = "";
    }

    public Payout(Long fromBankAccountId, Long toBankAccountId, BigDecimal amount, Currency currency) 
    {
        this();
        this.fromBankAccountId = fromBankAccountId;
        this.toBankAccountId = toBankAccountId;
        this.amount = amount;
        this.currency = currency;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getFromBankAccountId() 
    {
        return fromBankAccountId;
    }

    public void setFromBankAccountId(Long fromBankAccountId) 
    {
        this.fromBankAccountId = fromBankAccountId;
    }

    public Long getToBankAccountId() 
    {
        return toBankAccountId;
    }

    public void setToBankAccountId(Long toBankAccountId) 
    {
        this.toBankAccountId = toBankAccountId;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Currency getCurrency() 
    {
        return currency;
    }

    public void setCurrency(Currency currency) 
    {
        this.currency = currency;
    }

    public LocalDateTime getCreationDate() 
    {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) 
    {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() 
    {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) 
    {
        this.updateDate = updateDate;
    }

    public PayoutStatus getStatus() 
    {
        return status;
    }

    public void setStatus(PayoutStatus status) 
    {
        this.status = status;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payout that = (Payout) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(getId());
    }
}
