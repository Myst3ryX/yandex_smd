package com.sergon146.core.repository;

import com.sergon146.business.model.Balance;
import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.repository.WalletRepository;
import com.sergon146.core.db.WalletsDatabase;
import com.sergon146.core.db.entity.WalletEntity;
import com.sergon146.core.mapper.WalletEntityMapper;
import com.sergon146.core.utils.WalletCalculations;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.Flowable;

public class WalletRepositoryImpl implements WalletRepository {

    private final WalletsDatabase walletsDatabase;
    private final WalletCalculations walletCalculations;

    public WalletRepositoryImpl(WalletsDatabase walletsDatabase,
                                WalletCalculations walletCalculations) {
        this.walletsDatabase = walletsDatabase;
        this.walletCalculations = walletCalculations;
    }

    @Override
    public Flowable<List<Wallet>> getWallets() {
        return walletsDatabase.getWalletDao()
                .getWalletsWithTransactions()
                .map(WalletEntityMapper::transformFromEntities);
    }

    @Override
    public Flowable<Wallet> getWallet(long id) {
        return walletsDatabase.getWalletDao()
                .getWalletWithTransactions(id)
                .map(WalletEntityMapper::transformFromEntity);
    }

    @Override
    public long addWallet(Wallet wallet) {
        return walletsDatabase.getWalletDao()
                .addWallet(WalletEntityMapper.transformToEntity(wallet));
    }

    @Override
    public Flowable<Balance> getWalletsBalanceSum(ExchangeRate rate) {
        return getWallets().map((wallets) -> walletCalculations.getTotalBalance(wallets, rate));
    }

    @Override
    public void applyWalletTransaction(long id, Transaction transaction) {
        WalletEntity wallet = walletsDatabase.getWalletDao().getWallet(id);
        final BigDecimal newBalance = walletCalculations.performTransaction(wallet, transaction);
        wallet.setBalance(newBalance);
        walletsDatabase.getWalletDao().updateWallet(wallet);
    }
}
