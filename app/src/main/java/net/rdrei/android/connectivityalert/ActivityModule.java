package net.rdrei.android.connectivityalert;

import android.app.ActionBar;
import android.app.Activity;

import net.rdrei.android.connectivityalert.scope.ForActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(final Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ForActivity
    public ActionBar provideActionBar() {
        return mActivity.getActionBar();
    }
}
