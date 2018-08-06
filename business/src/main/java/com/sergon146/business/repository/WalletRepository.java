package com.sergon146.business.repository;

import com.sergon146.business.model.Balance;
import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;

import java.util.List;

import io.reactivex.Flowable;

public interface WalletRepository {

    Flowable<List<Wallet>> getWallets();

    Flowable<Wallet> getWallet(long id);

    Flowable<Balance> getWalletsBalanceSum(ExchangeRate rate);

    void applyWalletTransaction(long id, Transaction transaction);

    long addWallet(Wallet wallet);
}
