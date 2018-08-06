package com.sergon146.business.usecase;

import com.sergon146.business.contracts.WalletUseCase;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;

import java.util.List;

import io.reactivex.Flowable;

public class WalletUseCaseImpl implements WalletUseCase {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public WalletUseCaseImpl(TransactionRepository transactionRepository,
                             WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public Flowable<Wallet> getWallet(long id) {
        return walletRepository.getWallet(id);
    }

    @Override
    public Flowable<List<Transaction>> getWalletTransactions(long id) {
        return transactionRepository.getWalletTransactions(id);
    }


}
