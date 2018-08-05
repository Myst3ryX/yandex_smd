package com.sergon146.business.contracts;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;

import java.util.List;

import io.reactivex.Observable;

public interface WalletUseCase {

    Observable<Wallet> getWallet(long id);

    Observable<List<Transaction>> getWalletTransactions(long id);
}
