package com.sergon146.core.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.business.model.types.TransactionCategory;
import com.sergon146.core.db.converter.BigDecimalConverter;
import com.sergon146.core.db.converter.CurrencyConverter;
import com.sergon146.core.db.converter.DateConverter;
import com.sergon146.core.db.converter.OperationTypeConverter;
import com.sergon146.core.db.converter.TransactionCategoryConverter;

import java.math.BigDecimal;
import java.util.Date;

@Entity(tableName = "transactions")
public class TransactionEntity {

    @PrimaryKey (autoGenerate = true)
    private long id;

    @TypeConverters(OperationTypeConverter.class)
    private OperationType type;

    @TypeConverters(CurrencyConverter.class)
    private Currency currency;

    @TypeConverters(BigDecimalConverter.class)
    private BigDecimal amount;

    @TypeConverters(BigDecimalConverter.class)
    @ColumnInfo(name = "exchange_rate")
    private BigDecimal exchangeRate;

    @TypeConverters(DateConverter.class)
    private Date date;

    @TypeConverters(TransactionCategoryConverter.class)
    private TransactionCategory category;

    @ColumnInfo(name = "wallet_id")
    private long walletId;

    public TransactionEntity(OperationType type, Currency currency, BigDecimal amount,
                             BigDecimal exchangeRate, Date date, TransactionCategory category, long walletId) {
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.date = date;
        this.category = category;
        this.walletId = walletId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }
}
