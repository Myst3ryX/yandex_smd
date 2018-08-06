package com.sergon146.business.usecase;

import com.sergon146.business.contracts.TransactionsUseCase;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.repository.TransactionRepository;

import java.util.List;

import io.reactivex.Flowable;

public class TransactionsUseCaseImpl implements TransactionsUseCase {

    private final TransactionRepository transactionRepository;

    public TransactionsUseCaseImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Flowable<List<Transaction>> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }
}
