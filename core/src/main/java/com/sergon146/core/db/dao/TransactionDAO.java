package com.sergon146.core.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sergon146.core.db.entity.TransactionEntity;

import java.util.List;

@Dao
public interface TransactionDAO {

    @Query("select * from transactions")
    List<TransactionEntity> getTransactions();

    @Query("select * from transactions where id = :id")
    TransactionEntity getTransaction(long id);

    @Insert
    void addTransaction(TransactionEntity transaction);

    @Delete
    void deleteTransaction(TransactionEntity transaction);
}
