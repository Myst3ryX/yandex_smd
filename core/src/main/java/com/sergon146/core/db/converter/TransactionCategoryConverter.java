package com.sergon146.core.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.sergon146.business.model.types.TransactionCategory;

public class TransactionCategoryConverter {

    @TypeConverter
    public static TransactionCategory toCategory(int ordinal) {
        return TransactionCategory.values()[ordinal];
    }

    @TypeConverter
    public static int toOrdinal(TransactionCategory category) {
        return category.ordinal();
    }
}
