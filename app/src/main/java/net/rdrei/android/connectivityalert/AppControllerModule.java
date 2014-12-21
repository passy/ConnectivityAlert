package net.rdrei.android.connectivityalert;

import android.app.ActionBar;
import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module
public class AppControllerModule {
    private final Activity mActivity;

    public AppControllerModule(final Activity activity) {
        mActivity = activity;
    }

    @Provides
    public ActionBar getActionBar() {
        return mActivity.getActionBar();
    }
}
