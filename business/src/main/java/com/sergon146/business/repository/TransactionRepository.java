package com.sergon146.business.repository;

import com.sergon146.business.model.Transaction;

import java.util.List;

import io.reactivex.Flowable;

public interface TransactionRepository {

    Flowable<List<Transaction>> getAllTransactions();

    Flowable<List<Transaction>> getWalletTransactions(long walletId);

    void addTransaction(Transaction transaction);
}
