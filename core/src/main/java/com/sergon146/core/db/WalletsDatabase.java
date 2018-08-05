package com.sergon146.core.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sergon146.core.db.dao.TransactionDAO;
import com.sergon146.core.db.dao.WalletDAO;
import com.sergon146.core.db.entity.TransactionEntity;
import com.sergon146.core.db.entity.WalletEntity;

@Database(entities = {WalletEntity.class, TransactionEntity.class}, version = 1)
public abstract class WalletsDatabase extends RoomDatabase {

    public abstract WalletDAO getWalletDao();

    public abstract TransactionDAO getTransactionDao();
}
