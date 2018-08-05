package com.sergon146.core.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.sergon146.business.model.types.WalletType;

public class WalletTypeConverter {

    @TypeConverter
    public static WalletType toType(int ordinal) {
        return WalletType.values()[ordinal];
    }

    @TypeConverter
    public static int toOrdinal(WalletType type) {
        return type.ordinal();
    }
}
