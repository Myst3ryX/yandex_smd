package com.sergon146.mobilization18.di.features;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.sergon146.business.repository.ExchangeRepository;
import com.sergon146.business.repository.TransactionRepository;
import com.sergon146.business.repository.WalletRepository;
import com.sergon146.core.Core;
import com.sergon146.core.api.ApiService;
import com.sergon146.core.db.WalletsDatabase;
import com.sergon146.core.repository.ExchangeRepositoryImpl;
import com.sergon146.core.repository.TransactionRepositoryImpl;
import com.sergon146.core.repository.WalletRepositoryImpl;
import com.sergon146.mobilization18.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static Context provideContext(Application app) {
        return app;
    }

    @Singleton
    @Provides
    static Resources provideResources(Context context) {
        return context.getResources();
    }

    @Singleton
    @Provides
    static ApiService provideMiddlewareService() {
        return Core.api();
    }

    @Singleton
    @Provides
    static WalletsDatabase provideDatabase() {
        return Core.getDatabase();
    }

    @Singleton
    @Provides
    static TransactionRepository provideTransactionRepository(WalletsDatabase walletsDatabase) {
        return new TransactionRepositoryImpl(walletsDatabase);
    }

    @Singleton
    @Provides
    static WalletRepository provideWalletRepository(WalletsDatabase walletsDatabase) {
        return new WalletRepositoryImpl(walletsDatabase);
    }

    @Singleton
    @Provides
    static ExchangeRepository provideExchangeRepository(ApiService apiService) {
        return new ExchangeRepositoryImpl(apiService);
    }

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity contributeMainActivity();
}
