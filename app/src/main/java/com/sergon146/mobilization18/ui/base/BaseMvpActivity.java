package com.sergon146.mobilization18.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.sergon146.mobilization18.App;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.di.base.InjectableActivity;
import com.sergon146.mobilization18.navigation.BaseNavigator;
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.navigation.commands.BackToRoot;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;


public abstract class BaseMvpActivity<Presenter extends BasePresenter> extends InjectableActivity
        implements BaseMvpView {

    private static final String LOG_TAG = "BaseActivity";
    private final NavigateInjector navigateInjector = new NavigateInjector();
    private final Navigator navigator = new BaseNavigator(this, getNavigationContainerId()) {
        @Override
        protected Intent createOrApply(String screenKey, Object[] transitionData) {
            return null;
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            if (getContainerId() != 0) {
                return BaseMvpActivity.this.createFragment(screenKey, data);
            } else {
                return null;
            }
        }

        @Override
        public void applyCommand(Command command) {
            super.applyCommand(command);

            if (command instanceof BackToRoot) {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 1) {
                    super.applyCommand(new BackTo(fm.getBackStackEntryAt(0).getName()));
                }
            }

            // activate top fragment in the tabbar
            FragmentManager fm = getSupportFragmentManager();
            fm.executePendingTransactions();
            int count = fm.getBackStackEntryCount();
            if (count > 0) {
                String name = fm.getBackStackEntryAt(count - 1).getName();
                activateScreen(name);
            }
        }
    };
    private Presenter presenter;

    protected Presenter providePresenter() {
        return null;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    MainRouter getRouter() {
        return navigateInjector.router;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = providePresenter();
        navigateInjector.router.setNavigator(navigator);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigateInjector.navigatorHolder.setNavigator(navigator);
        navigateInjector.router.setNavigator(navigator);
    }

    @Override
    public void showToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setActionBarTitle(int stringId) {
        setActionBarTitle(getString(stringId));
    }

    @Override
    public void setActionBarTitle(String title) {
        setTitle(title);
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingError() {
        Toast.makeText(getApplicationContext(), R.string.loading_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectionLost() {

    }

    @Override
    public void connectionRestore() {

    }

    protected void activateScreen(String name) {

    }

    protected @IdRes
    int getNavigationContainerId() {
        return 0;
    }

    protected Intent createActivityIntent(final String screenKey, final Object data) {
        return null;
    }

    protected Fragment createFragment(final String screenKey, final Object data) {
        return null;
    }

    public static class NavigateInjector {
        @Inject
        NavigatorHolder navigatorHolder;
        @Inject
        MainRouter router;

        NavigateInjector() {
            App.getAppComponent().inject(this);
        }
    }
}
