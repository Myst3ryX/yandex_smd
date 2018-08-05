package com.sergon146.business.contracts;

import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;

import java.util.List;

import io.reactivex.Observable;

public interface AddTransactionUseCase {

    Observable<List<Wallet>> getWallets();

    Observable<ExchangeRate> getExchangeRate();

    void addTransaction(Transaction transaction);
}
