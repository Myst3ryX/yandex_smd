package com.sergon146.core.mapper;

import com.sergon146.business.model.Transaction;
import com.sergon146.business.model.Wallet;
import com.sergon146.core.db.entity.WalletEntity;
import com.sergon146.core.db.entity.WalletWithTransactions;

import java.util.ArrayList;
import java.util.List;

public class WalletEntityMapper {

    public static List<Wallet> transformFromEntities(List<WalletWithTransactions> entities) {
        List<Wallet> wallets = new ArrayList<>();
        for (WalletWithTransactions entity : entities) {
            wallets.add(transformFromEntity(entity));
        }
        return wallets;
    }

    public static Wallet transformFromEntity(WalletWithTransactions entity) {
        List<Transaction> transactions = TransactionEntityMapper.transformFromEntities(entity.transactions);
        return new Wallet(
                entity.wallet.getId(),
                entity.wallet.getBalance(),
                entity.wallet.getCurrency(),
                entity.wallet.getName(),
                entity.wallet.getType(),
                transactions);
    }

    public static WalletEntity transformToEntity(Wallet wallet) {
        return new WalletEntity(
                wallet.getBalance(),
                wallet.getCurrency(),
                wallet.getName(),
                wallet.getType()
        );
    }
}
