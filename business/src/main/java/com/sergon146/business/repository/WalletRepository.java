package com.sergon146.business.repository;

import com.sergon146.business.model.Balance;
import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;

import java.util.List;

import io.reactivex.Observable;

public interface WalletRepository {

    Observable<List<Wallet>> getWallets();

    Observable<Wallet> getWallet(long id);

    Observable<Balance> getWalletsBalanceSum(ExchangeRate rate);

    void applyWalletTransaction(long id, Transaction transaction);

    long addWallet(Wallet wallet);
}
