package com.sergon146.mobilization18.di.base;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class InjectableFragment extends MvpAppCompatFragment
        implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
