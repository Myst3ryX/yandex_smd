package com.sergon146.core.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class WalletWithTransactions {

    @Embedded
    public WalletEntity wallet;

    @Relation(parentColumn = "id", entityColumn = "wallet_id")
    public List<TransactionEntity> transactions;


}
