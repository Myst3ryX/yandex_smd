package com.sergon146.core.worker;

import android.support.annotation.NonNull;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.types.Currency;
import com.sergon146.business.model.types.OperationType;
import com.sergon146.business.model.types.TransactionCategory;
import com.sergon146.core.Core;
import com.sergon146.core.db.entity.WalletEntity;
import com.sergon146.core.mapper.TransactionEntityMapper;
import com.sergon146.core.utils.WalletCalculations;
import com.sergon146.core.utils.WorkerConst;

import java.math.BigDecimal;
import java.util.Date;

import androidx.work.Data;
import androidx.work.Worker;

public class RegularTransactionWorker extends Worker {

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        OperationType operationType = OperationType.valueOf(data.getString(WorkerConst.TYPE));
        Currency currency = Currency.valueOf(data.getString(WorkerConst.CURRENCY));
        BigDecimal amount = new BigDecimal(data.getString(WorkerConst.AMOUNT));
        BigDecimal exchangeRate = new BigDecimal(data.getString(WorkerConst.EXCHANGE_RATE));
        TransactionCategory category = TransactionCategory.valueOf(data.getString(WorkerConst.CATEGORY));
        Long walletId = data.getLong(WorkerConst.WALLET_ID, 0L);

        Transaction transaction = new Transaction(operationType, currency, amount, exchangeRate,
                new Date(), category, walletId);

        Core.getDatabase().getTransactionDao().addTransaction(TransactionEntityMapper
                .transformToEntity(transaction));
        WalletEntity wallet = Core.getDatabase().getWalletDao().getWallet(walletId);
        BigDecimal balance = new WalletCalculations().performTransaction(wallet, transaction);
        wallet.setBalance(balance);
        Core.getDatabase().getWalletDao().updateWallet(wallet);

        return Result.SUCCESS;
    }
}
