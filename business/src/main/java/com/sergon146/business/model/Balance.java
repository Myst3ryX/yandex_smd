package com.sergon146.business.model;

import com.sergon146.business.model.types.Currency;

import java.math.BigDecimal;
import java.util.Map;

public class Balance {

    private Map<Currency, BigDecimal> balances;

    public Balance(Map<Currency, BigDecimal> balances) {
        this.balances = balances;
    }

    public void addBalance(Currency currency, BigDecimal amount) {
        balances.put(currency, amount);
    }

    public BigDecimal getBalance(Currency currency) {
        return balances.get(currency);
    }
}
