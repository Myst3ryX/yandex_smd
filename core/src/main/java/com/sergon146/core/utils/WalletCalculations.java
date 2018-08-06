package com.sergon146.core.utils;

import com.sergon146.business.model.Balance;
import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.core.db.entity.WalletEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class WalletCalculations {

    public Balance getTotalBalance(List<Wallet> wallets, ExchangeRate rate) {
        BigDecimal sum = BigDecimal.ZERO;
        Balance totalBalance = new Balance(new HashMap<>());

        for (Wallet wallet : wallets) {
            if (wallet.getCurrency() == Currency.RUBLE) {
                sum = sum.add(wallet.getBalance());
            } else {
                sum = sum.add(wallet.getBalance().multiply(rate.getExchageRate()));
            }
        }

        totalBalance.addBalance(Currency.RUBLE, sum);
        totalBalance.addBalance(Currency.DOLLAR, sum.divide(rate.getExchageRate(),
                BigDecimal.ROUND_HALF_EVEN));

        return totalBalance;
    }

    public BigDecimal performTransaction(WalletEntity wallet, Transaction transaction) {
        Currency walletCurrency = wallet.getCurrency();
        Currency transactionCurrency = transaction.getCurrency();

        OperationType type = transaction.getType();
        BigDecimal rate = transaction.getExchangeRate();
        BigDecimal amount = transaction.getAmount();
        BigDecimal balance = wallet.getBalance();

        if (walletCurrency == transactionCurrency) {
            balance = calculate(type, balance, amount);
        } else if (walletCurrency == Currency.RUBLE) {
            balance = calculate(type, balance, amount.multiply(rate));
        } else {
            balance = calculate(type, balance, amount.divide(rate, BigDecimal.ROUND_HALF_DOWN));
        }

        return balance;
    }

    private BigDecimal calculate(OperationType type, BigDecimal balance, BigDecimal amount) {
        if (type == OperationType.INCOME) {
            balance = balance.add(amount);
        } else if (type == OperationType.EXPENSE) {
            balance = balance.subtract(amount);
        }

        return balance;
    }
}
