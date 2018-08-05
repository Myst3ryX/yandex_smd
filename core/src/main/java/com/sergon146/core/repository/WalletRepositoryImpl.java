package com.sergon146.core.repository;

import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Wallet;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.repository.WalletRepository;
import com.sergon146.core.db.WalletsDatabase;
import com.sergon146.core.mapper.WalletEntityMapper;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class WalletRepositoryImpl implements WalletRepository {

    private final List<Wallet> wallets;
    private final WalletsDatabase walletsDatabase;
    private Subject<List<Wallet>> walletsSubj = BehaviorSubject.create();

    public WalletRepositoryImpl(WalletsDatabase walletsDatabase) {
        this.walletsDatabase = walletsDatabase;
        this.wallets = getLocalWallets();
        walletsSubj.onNext(wallets);

        initFirstWallets();
    }

    @Override
    public Observable<List<Wallet>> getWallets() {
        return walletsSubj;
    }

    @Override
    public Observable<Wallet> getWallet(long id) {
        Wallet wallet = WalletEntityMapper.transformFromEntity(walletsDatabase.getWalletDao().getWallet(id));
        return Observable.just(wallet);
    }

    @Override
    public long addWallet(Wallet wallet) {
        return walletsDatabase.getWalletDao().addWallet(WalletEntityMapper.transformToEntity(wallet));
    }

    @Override
    public Observable<BigDecimal> getWalletsBalanceSum(List<Wallet> wallets, ExchangeRate rate) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Wallet wallet : wallets) {
            if (wallet.getCurrency().equals(Currency.RUBLE)) {
                sum = sum.add(wallet.getBalance());
            } else
                sum = sum.add(wallet.getBalance().multiply(rate.getExchageRate()));
        }
        return Observable.just(sum);
    }

    private List<Wallet> getLocalWallets() {
        return WalletEntityMapper.transformFromEntities(walletsDatabase.getWalletDao().getWallets());
    }

    private void initFirstWallets() {
//        long id = addWallet(new Wallet(BigDecimal.ZERO, Currency.RUBLE, "Наличка", WalletType.CASH));
//        Log.w("WalletRepositoryImpl", String.valueOf(id));
//        id = addWallet(new Wallet(BigDecimal.ZERO, Currency.DOLLAR, "Сбербанк", WalletType.DEBIT_CARD));
//        Log.w("WalletRepositoryImpl", String.valueOf(id));
//        id = addWallet(new Wallet(BigDecimal.ZERO, Currency.RUBLE, "Халва", WalletType.CREDIT_CARD));
//        Log.w("WalletRepositoryImpl", String.valueOf(id));
    }
}
