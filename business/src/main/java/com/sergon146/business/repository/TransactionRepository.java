package com.sergon146.business.repository;

import com.sergon146.business.model.Transaction;

import java.util.List;

import io.reactivex.Observable;

public interface TransactionRepository {

    Observable<List<Transaction>> getAllTransactions();

    Observable<List<Transaction>> getWalletTransactions(long walletId);

    void addTransaction(Transaction transaction);
}
