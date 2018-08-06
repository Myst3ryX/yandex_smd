package com.sergon146.mobilization18.ui.fragments.wallet;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.WalletUseCase;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BasePresenter;

@InjectViewState
public class WalletPresenter extends BasePresenter<WalletView> {
    private final WalletUseCase useCase;
    private long id;

    public WalletPresenter(MainRouter router, WalletUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected String getScreenTag() {
        return "WalletPresenter";
    }

    public void setId(long id) {
        if (id == this.id) {
            return;
        }

        this.id = id;
        bind(onUi(useCase.getWallet(id))
                .subscribe(wallet -> getViewState().showWallet(wallet)));

        bind(onUi(useCase.getWalletTransactions(id)).subscribe(transactions -> {
            getViewState().showTransactions(transactions);
        }));
    }
}
