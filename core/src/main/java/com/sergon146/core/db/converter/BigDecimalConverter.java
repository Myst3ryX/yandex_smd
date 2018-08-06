package com.sergon146.core.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.math.BigDecimal;

public class BigDecimalConverter {

    private static final BigDecimal INCREMENT = new BigDecimal(100);

    @TypeConverter
    public BigDecimal fromLong(Long value) {
        return value == null ? null : new BigDecimal(value).divide(INCREMENT);
    }

    @TypeConverter
    public Long toLong(BigDecimal bd) {
        if (bd != null) {
            return bd.multiply(INCREMENT).longValue();
        }
        return null;
    }

}
