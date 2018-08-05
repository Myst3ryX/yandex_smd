package com.sergon146.core.repository;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.core.db.WalletsDatabase;
import com.sergon146.core.mapper.TransactionEntityMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final WalletsDatabase walletsDatabase;
    private final Currency currentCurrency = Currency.RUBLE;

    private List<Transaction> transactions;
    private Subject<List<Transaction>> transactionSubj = BehaviorSubject.create();

    public TransactionRepositoryImpl(WalletsDatabase walletsDatabase) {
        this.walletsDatabase = walletsDatabase;
        this.transactions = getTransactions();
        transactionSubj.onNext(transactions);
    }

    @Override
    public Observable<List<Transaction>> getAllTransactions() {
        return transactionSubj;
    }

    @Override
    public Observable<List<Transaction>> getWalletTransactions(long walletId) {
        Subject<List<Transaction>> walletTransactionsSubj = BehaviorSubject.create();
        List<Transaction> walletTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getWalletId() == walletId) {
                walletTransactions.add(transaction);
            }
        }

        walletTransactionsSubj.onNext(walletTransactions);
        return walletTransactionsSubj;
    }

    @Override
    public Observable<BigDecimal> getTransactionSum(List<Transaction> transactions) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            BigDecimal amount;
            if (currentCurrency.equals(transaction.getCurrency())) {
                amount = transaction.getAmount();
            } else {
                amount = transaction.getAmount().divide(transaction.getExchangeRate(),
                        BigDecimal.ROUND_HALF_UP);
            }

            if (transaction.getType() == OperationType.INCOME) {
                sum = sum.add(amount);
            } else {
                sum = sum.subtract(amount);
            }
        }
        return Observable.just(sum);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        walletsDatabase.getTransactionDao().addTransaction(TransactionEntityMapper.transformToEntity(transaction));
        //transactionSubj.onNext(transactions);
    }

    private List<Transaction> getTransactions() {
        return TransactionEntityMapper.transformFromEntities(walletsDatabase.getTransactionDao().getTransactions());
    }
}
