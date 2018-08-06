package com.sergon146.core.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.sergon146.business.model.types.Currency;

public class CurrencyConverter {

    private static final String DIVIDER = "/";

    @TypeConverter
    public static Currency toCurrency(String str) {
        String symbol = str.split(DIVIDER)[0];
        String shortName = str.split(DIVIDER)[1];

        if (symbol.equalsIgnoreCase(Currency.RUBLE.getSymbol())
                && shortName.equalsIgnoreCase(Currency.RUBLE.getShortName())) {
            return Currency.RUBLE;
        } else if (symbol.equalsIgnoreCase(Currency.DOLLAR.getSymbol())
                && shortName.equalsIgnoreCase(Currency.DOLLAR.getShortName())) {
            return Currency.DOLLAR;
        } else if (symbol.equalsIgnoreCase(Currency.YEN.getSymbol())
                && shortName.equalsIgnoreCase(Currency.YEN.getShortName())) {
            return Currency.YEN;
        } else {
            throw new IllegalArgumentException("Could not recognize currency");
        }
    }

    @TypeConverter
    public static String toString(Currency currency) {
        return currency.getSymbol() + DIVIDER + currency.getShortName();
    }

    //    RUBLE("₽", "RUB"),
    //    DOLLAR("$", "USD"),
    //    YEN("¥", "YEN");
    //
    //    private final String symbol;
    //    private final String shortName;
    //
    //    Currency(String symbol, String shortName) {
    //        this.symbol = symbol;
    //        this.shortName = shortName;
    //    }
    //
    //    public String getSymbol() {
    //        return symbol;
    //    }
    //
    //    public String getShortName() {
    //        return shortName;
    //    }

    //    @TypeConverter
    //    public static Task.Status toStatus(int status) {
    //        if (status == ACTIVE.getCode()) {
    //            return ACTIVE;
    //        } else if (status == INACTIVE.getCode()) {
    //            return INACTIVE;
    //        } else if (status == COMPLETED.getCode()) {
    //            return COMPLETED;
    //        } else {
    //            throw new IllegalArgumentException("Could not recognize status");
    //        }
    //    }
    //
    //    @TypeConverter
    //    public static Integer toInteger(Task.Status status) {
    //        return status.getCode();
    //    }
}
