package com.sergon146.business.usecase;

import com.sergon146.business.contracts.BalanceUseCase;
import com.sergon146.business.model.Balance;
import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.repository.ExchangeRepository;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class BalanceUseCaseImpl implements BalanceUseCase {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final ExchangeRepository exchangeRepository;

    public BalanceUseCaseImpl(WalletRepository walletRepository,
                              TransactionRepository transactionRepository,
                              ExchangeRepository exchangeRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public Flowable<List<Wallet>> getWallets() {
        return walletRepository.getWallets();
    }

    @Override
    public Flowable<List<Transaction>> getTransactions() {
        return transactionRepository.getAllTransactions();
    }

    @Override
    public Flowable<Balance> getWalletsBalanceSum(ExchangeRate rate) {
        return walletRepository.getWalletsBalanceSum(rate);
    }

    @Override
    public Observable<ExchangeRate> getExchangeRate() {
        return exchangeRepository.getExchangeRate("USD", "RUB");
    }
}