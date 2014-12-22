package net.rdrei.android.connectivityalert;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;

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

    @Provides @Singleton @ForActivity
    Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    @Singleton
    ActionBar provideActionBar() {
        return mActivity.getActionBar();
    }
}
