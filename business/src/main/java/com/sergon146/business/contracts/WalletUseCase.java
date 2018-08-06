package com.sergon146.business.contracts;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;

import java.util.List;

import io.reactivex.Flowable;

public interface WalletUseCase {

    Flowable<Wallet> getWallet(long id);

    Flowable<List<Transaction>> getWalletTransactions(long id);
}
