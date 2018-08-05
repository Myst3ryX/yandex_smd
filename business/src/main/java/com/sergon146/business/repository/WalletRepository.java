package com.sergon146.business.repository;

import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Wallet;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.Observable;

public interface WalletRepository {

    Observable<List<Wallet>> getWallets();

    Observable<Wallet> getWallet(long id);

    Observable<BigDecimal> getWalletsBalanceSum(List<Wallet> wallets, ExchangeRate rate);

    long addWallet(Wallet wallet);
}
