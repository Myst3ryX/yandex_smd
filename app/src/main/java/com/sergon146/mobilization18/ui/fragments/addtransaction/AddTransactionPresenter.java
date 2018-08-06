package com.sergon146.mobilization18.ui.fragments.addtransaction;

import com.arellomobile.mvp.InjectViewState;
import com.sergon146.business.contracts.AddTransactionUseCase;
import com.sergon146.business.model.Transaction;
import com.sergon146.core.utils.WorkerConst;
import com.sergon146.core.worker.RegularTransactionWorker;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.dialog.BaseDialogPresenter;

import java.util.concurrent.TimeUnit;

import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

@InjectViewState
public class AddTransactionPresenter extends BaseDialogPresenter<AddTransactionView> {

    private final AddTransactionUseCase useCase;

    public AddTransactionPresenter(MainRouter router,
                                   AddTransactionUseCase useCase) {
        super(router);
        this.useCase = useCase;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        bind(onUi(useCase.getWallets())
                .subscribe(wallets -> getViewState().showWallets(wallets)));

        bind(onUi(useCase.getExchangeRate())
                .subscribe(rate -> getViewState().showExchangeRate(rate)));
    }

    public void addTransaction(Transaction transaction) {
        useCase.addTransaction(transaction);
    }

    public void addWeeklyTransaction(Data data) {
        WorkRequest weeklyPeriodicRequest = new PeriodicWorkRequest.Builder(
                RegularTransactionWorker.class, WorkerConst.PERIOD_SEVEN_DAYS, TimeUnit.DAYS)
                .setInputData(data)
                .addTag(WorkerConst.WEEKLY_WORKER_TAG)
                .build();
        WorkManager.getInstance().enqueue(weeklyPeriodicRequest);
    }

    public void addMonthlyTransaction(Data data) {
        WorkRequest monthlyPeriodicRequest = new PeriodicWorkRequest.Builder(
                RegularTransactionWorker.class, WorkerConst.PERIOD_THIRTY_DAYS, TimeUnit.DAYS)
                .setInputData(data)
                .addTag(WorkerConst.MONTHLY_WORKER_TAG)
                .build();
        WorkManager.getInstance().enqueue(monthlyPeriodicRequest);
    }

    @Override
    protected String getScreenTag() {
        return "AddTransactionPresenter";
    }
}
