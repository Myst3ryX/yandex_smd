package com.sergon146.core.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sergon146.core.db.entity.TransactionEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface TransactionDAO {

    @Query("select * from transactions")
    Flowable<List<TransactionEntity>> getAllTransactions();

    @Query("select * from transactions where wallet_id = :walletId")
    Flowable<List<TransactionEntity>> getWalletTransactions(long walletId);

    @Insert
    void addTransaction(TransactionEntity transaction);

    @Delete
    void deleteTransaction(TransactionEntity transaction);
}
