package com.sergon146.core.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.sergon146.core.db.entity.WalletEntity;
import com.sergon146.core.db.entity.WalletWithTransactions;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface WalletDAO {

    @Transaction
    @Query("select * from wallet")
    Flowable<List<WalletWithTransactions>> getWalletsWithTransactions();

    @Transaction
    @Query("select * from wallet where id = :id")
    Flowable<WalletWithTransactions> getWalletWithTransactions(long id);

    @Query("select * from wallet")
    List<WalletEntity> getWallets();

    @Query("select * from wallet where id = :id")
    WalletEntity getWallet(long id);

    @Insert
    long addWallet(WalletEntity wallet);

    @Update
    int updateWallet(WalletEntity wallet);

    @Delete
    void deleteWallet(WalletEntity wallet);

    @Ignore
    default boolean isEmpty() {
        return getWallets().isEmpty();
    }
}
