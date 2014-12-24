package net.rdrei.android.connectivityalert;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

// TODO: Includes AndroidModule?
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(final Activity activity) {
        mActivity = activity;
    }

    @Provides
    @Singleton
    @ForActivity
    public Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    @Singleton
    public ActionBar provideActionBar() {
        return mActivity.getActionBar();
    }

    @Provides
    @Singleton
    @ForActivity
    public LayoutInflater provideLayoutInflater() {
        return mActivity.getLayoutInflater();
    }
}
