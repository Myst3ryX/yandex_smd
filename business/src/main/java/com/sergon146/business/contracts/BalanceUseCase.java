package com.sergon146.business.contracts;

import com.sergon146.business.model.Balance;
import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;

import java.util.List;

import io.reactivex.Observable;

public interface BalanceUseCase {

    Observable<List<Wallet>> getWallets();

    Observable<List<Transaction>> getTransactions();

    Observable<Balance> getWalletsBalanceSum(ExchangeRate rate);

    Observable<ExchangeRate> getExchangeRate();
}
