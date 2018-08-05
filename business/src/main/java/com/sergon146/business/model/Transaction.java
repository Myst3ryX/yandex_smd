package com.sergon146.business.model;

import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.business.model.types.TransactionCategory;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private Long id;
    private OperationType type;
    private Currency currency;
    private BigDecimal amount;
    private BigDecimal exchangeRate;
    private Date date;
    private TransactionCategory category;
    private Long walletId;
    private String walletName;

    public Transaction(Long id, OperationType type, Currency currency,
                       BigDecimal amount, BigDecimal exchangeRate, Date date,
                       TransactionCategory category, Long walletId) {
        this.id = id;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.date = date;
        this.category = category;
        this.walletId = walletId;
    }

    public Transaction(OperationType type, Currency currency,
                       BigDecimal amount, BigDecimal exchangeRate, Date date,
                       TransactionCategory category, Long walletId) {
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.date = date;
        this.category = category;
        this.walletId = walletId;
    }

    public Long getId() {
        return id;
    }

    public OperationType getType() {
        return type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public Date getDate() {
        return date;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public Long getWalletId() {
        return walletId;
    }

    public String getWalletName() {
        return walletName;
    }
}
