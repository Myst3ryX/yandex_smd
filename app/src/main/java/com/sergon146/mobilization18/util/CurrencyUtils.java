package com.sergon146.mobilization18.util;

import android.content.res.Resources;

import com.sergon146.business.model.types.Currency;
import com.sergon146.mobilization18.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CurrencyUtils {

    public static String getAmountText(BigDecimal amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(amount);
    }

    public static String getBalanceText(Resources res, Currency currency, BigDecimal amount) {
        return res.getString(R.string.amount, currency.getSymbol(), getAmountText(amount));
    }
}
