package com.sergon146.core.repository;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.core.db.WalletsDatabase;
import com.sergon146.core.mapper.TransactionEntityMapper;

import java.util.List;

import io.reactivex.Flowable;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final WalletsDatabase walletsDatabase;

    public TransactionRepositoryImpl(WalletsDatabase walletsDatabase) {
        this.walletsDatabase = walletsDatabase;
    }

    @Override
    public Flowable<List<Transaction>> getAllTransactions() {
        return walletsDatabase.getTransactionDao().getAllTransactions()
                .map(TransactionEntityMapper::transformFromEntities);
    }

    @Override
    public Flowable<List<Transaction>> getWalletTransactions(long walletId) {
        return walletsDatabase.getTransactionDao().getWalletTransactions(walletId)
                .map(TransactionEntityMapper::transformFromEntities);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        walletsDatabase.getTransactionDao()
                .addTransaction(TransactionEntityMapper.transformToEntity(transaction));
    }
}
