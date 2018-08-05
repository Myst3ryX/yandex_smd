package com.sergon146.mobilization18.di.features;

import com.sergon146.business.contracts.AddTransactionUseCase;
import com.sergon146.business.repository.ExchangeRepository;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;
import com.sergon146.business.usecase.AddTransactionUseCaseImpl;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.fragments.addtransaction.AddTransactionPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AddTransactionModule {

    @Provides
    static AddTransactionUseCase provideAddTransactionUseCase(
            ExchangeRepository exchangeRepository,
            WalletRepository walletRepository,
            TransactionRepository transactionRepository) {

        return new AddTransactionUseCaseImpl(
                exchangeRepository,
                walletRepository,
                transactionRepository);
    }

    @Provides
    static AddTransactionPresenter provideAddTransactionPresenter(MainRouter router,
                                                                  AddTransactionUseCase useCase) {
        return new AddTransactionPresenter(router, useCase);
    }
}
