package com.sergon146.mobilization18.ui.fragments.addtransaction;

import com.sergon146.business.model.ExchangeRate;
import com.sergon146.business.model.Wallet;
import com.sergon146.mobilization18.ui.base.dialog.BaseDialogMvpView;

import java.util.List;

public interface AddTransactionView extends BaseDialogMvpView {

    void showWallets(List<Wallet> wallets);

    void showExchangeRate(ExchangeRate rate);
}
