package com.sergon146.core.mapper;

import com.sergon146.business.model.Transaction;
import com.sergon146.core.db.entity.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionEntityMapper {

    public static List<Transaction> transformFromEntities(List<TransactionEntity> entities) {
        List<Transaction> transactions = new ArrayList<>();
        for (TransactionEntity entity : entities) {
            transactions.add(transformFromEntity(entity));
        }
        return transactions;
    }

    public static Transaction transformFromEntity(TransactionEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getType(),
                entity.getCurrency(),
                entity.getAmount(),
                entity.getExchangeRate(),
                entity.getDate(),
                entity.getCategory(),
                entity.getWalletId()
        );
    }

    public static TransactionEntity transformToEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.getType(),
                transaction.getCurrency(),
                transaction.getAmount(),
                transaction.getExchangeRate(),
                transaction.getDate(),
                transaction.getCategory(),
                transaction.getWalletId()
        );
    }
}
