package com.sergon146.core.repository;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.core.db.WalletsDatabase;
import com.sergon146.core.mapper.TransactionEntityMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final WalletsDatabase walletsDatabase;

    public TransactionRepositoryImpl(WalletsDatabase walletsDatabase) {
        this.walletsDatabase = walletsDatabase;
    }

    @Override
    public Observable<List<Transaction>> getAllTransactions() {
        Subject<List<Transaction>> transactionSubj = BehaviorSubject.create();
        List<Transaction> transactions = getTransactions();
        transactionSubj.onNext(transactions);
        return transactionSubj;
    }

    @Override
    public Observable<List<Transaction>> getWalletTransactions(long walletId) {
        Subject<List<Transaction>> walletTransactionsSubj = BehaviorSubject.create();
        List<Transaction> walletTransactions = new ArrayList<>();
        for (Transaction transaction : getTransactions()) {
            if (transaction.getWalletId() == walletId) {
                walletTransactions.add(transaction);
            }
        }

        walletTransactionsSubj.onNext(walletTransactions);
        return walletTransactionsSubj;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        walletsDatabase.getTransactionDao()
                .addTransaction(TransactionEntityMapper.transformToEntity(transaction));
    }

    private List<Transaction> getTransactions() {
        return TransactionEntityMapper.transformFromEntities(walletsDatabase.getTransactionDao()
                .getTransactions());
    }
}
