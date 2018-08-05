package com.sergon146.core.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.sergon146.business.model.types.OperationType;

public class OperationTypeConverter {

    @TypeConverter
    public static OperationType toType(int ordinal) {
        return OperationType.values()[ordinal];
    }

    @TypeConverter
    public static int toOrdinal(OperationType type) {
        return type.ordinal();
    }
}
