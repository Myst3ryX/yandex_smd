package com.sergon146.core.repository;

import com.sergon146.business.model.Balance;
import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.business.repository.WalletRepository;
import com.sergon146.core.db.WalletsDatabase;
import com.sergon146.core.db.entity.WalletEntity;
import com.sergon146.core.mapper.WalletEntityMapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class WalletRepositoryImpl implements WalletRepository {

    private final WalletsDatabase walletsDatabase;

    public WalletRepositoryImpl(WalletsDatabase walletsDatabase) {
        this.walletsDatabase = walletsDatabase;
    }

    @Override
    public Observable<List<Wallet>> getWallets() {
        Subject<List<Wallet>> walletsSubj = BehaviorSubject.create();
        List<Wallet> wallets = getLocalWallets();
        walletsSubj.onNext(wallets);
        return walletsSubj;
    }

    @Override
    public Observable<Wallet> getWallet(long id) {
        return Observable.just(WalletEntityMapper.transformFromEntity(walletsDatabase.getWalletDao()
                .getWallet(id)));
    }

    @Override
    public long addWallet(Wallet wallet) {
        return walletsDatabase.getWalletDao().addWallet(WalletEntityMapper.transformToEntity(wallet));
    }

    @Override
    public Observable<Balance> getWalletsBalanceSum(ExchangeRate rate) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Wallet wallet : getLocalWallets()) {
            sum = sum.add(getWalletBalance(WalletEntityMapper.transformToEntity(wallet),
                    rate.getExchageRate()));
        }

        Balance balance = new Balance(new HashMap<>());
        balance.addBalance(Currency.RUBLE, sum);
        balance.addBalance(Currency.DOLLAR, sum.divide(rate.getExchageRate(),
                BigDecimal.ROUND_HALF_EVEN));
        return Observable.just(balance);
    }

    @Override
    public void applyWalletTransaction(long id, Transaction transaction) {
        WalletEntity wallet = walletsDatabase.getWalletDao().getWallet(id).wallet;
        BigDecimal balance = getWalletBalance(wallet, transaction.getExchangeRate());

        if (transaction.getType() == OperationType.INCOME) {
            balance = balance.add(transaction.getAmount());
        } else {
            balance = balance.subtract(transaction.getAmount());
        }

        wallet.setBalance(balance);
        walletsDatabase.getWalletDao().updateWallet(wallet);
    }

    private BigDecimal getWalletBalance(WalletEntity wallet, BigDecimal exchangeRate) {
        //todo fix
        if (wallet.getCurrency() == Currency.RUBLE) {
            return wallet.getBalance();
        } else
            return wallet.getBalance().multiply(exchangeRate);
    }

    private List<Wallet> getLocalWallets() {
        return WalletEntityMapper.transformFromEntities(walletsDatabase.getWalletDao().getWallets());
    }
}
