package com.sergon146.core.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.WalletType;
import com.sergon146.core.db.converter.BigDecimalConverter;
import com.sergon146.core.db.converter.CurrencyConverter;
import com.sergon146.core.db.converter.WalletTypeConverter;

import java.math.BigDecimal;

@Entity(tableName = "wallet")
public class WalletEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "wallet_label")
    private String name;

    @TypeConverters(BigDecimalConverter.class)
    private BigDecimal balance;

    @TypeConverters(CurrencyConverter.class)
    private Currency currency;

    @TypeConverters(WalletTypeConverter.class)
    private WalletType type;

    public WalletEntity(BigDecimal balance, Currency currency, String name, WalletType type) {
        this.balance = balance;
        this.currency = currency;
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal amount) {
        this.balance = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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

    public void setType(WalletType type) {
        this.type = type;
    }
}
