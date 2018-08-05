package com.sergon146.business.usecase;

import com.sergon146.business.contracts.TransactionsUseCase;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;

import java.util.List;

import io.reactivex.Observable;

public class TransactionsUseCaseImpl implements TransactionsUseCase {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionsUseCaseImpl(TransactionRepository transactionRepository,
                                   WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public Observable<List<Transaction>> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }
}
