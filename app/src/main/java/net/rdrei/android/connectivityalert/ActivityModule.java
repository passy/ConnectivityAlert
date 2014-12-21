package net.rdrei.android.connectivityalert;

import android.app.ActionBar;
import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module(includes = AndroidModule.class)
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(final Activity activity) {
        mActivity = activity;
    }

    @Provides
    public ActionBar provideActionBar() {
        return mActivity.getActionBar();
    }
}
