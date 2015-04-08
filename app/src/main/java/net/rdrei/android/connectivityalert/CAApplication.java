package net.rdrei.android.connectivityalert;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

import dagger.Component;

public class CAApplication extends Application {
    private ApplicationComponent mComponent;

    @Component(modules = AndroidModule.class)
    interface ApplicationComponent {
        void inject(MainActivity activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Add your initialization code here
        Parse.initialize(this, BuildConfig.PARSE_APPLICATION_ID, BuildConfig.PARSE_MASTER_KEY);

        ParseUser.enableAutomaticUser();
        final ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        mComponent = DaggerCAApplication_ApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
