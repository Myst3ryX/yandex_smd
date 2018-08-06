package com.sergon146.business.model;

import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.WalletType;

import java.math.BigDecimal;
import java.util.List;

public class Wallet {

    private Long id;
    private BigDecimal balance;
    private Currency currency;
    private String name;
    private WalletType type;
    private List<Transaction> transactions;

    public Wallet(Long id, BigDecimal balance, Currency currency, String name, WalletType type, List<Transaction> transactions) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
        this.name = name;
        this.type = type;
        this.transactions = transactions;
    }

    public Wallet(BigDecimal balance, Currency currency, String name, WalletType type) {
        this.balance = balance;
        this.currency = currency;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WalletType getType() {
        return type;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
