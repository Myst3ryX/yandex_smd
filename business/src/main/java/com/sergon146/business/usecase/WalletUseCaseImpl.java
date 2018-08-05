package com.sergon146.business.usecase;

import com.sergon146.business.contracts.WalletUseCase;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.repository.BalanceRepository;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;

import java.util.List;

import io.reactivex.Observable;

public class WalletUseCaseImpl implements WalletUseCase {
    private final BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public WalletUseCaseImpl(BalanceRepository balanceRepository,
                             TransactionRepository transactionRepository,
                             WalletRepository walletRepository) {
        this.balanceRepository = balanceRepository;
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public Observable<Wallet> getWallet(long id) {
        return walletRepository.getWallet(id);
    }

    @Override
    public Observable<List<Transaction>> getWalletTransactions(long id) {
        return transactionRepository.getWalletTransactions(id);
    }


}
