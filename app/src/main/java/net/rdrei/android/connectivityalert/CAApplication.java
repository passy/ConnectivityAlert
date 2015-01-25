package net.rdrei.android.connectivityalert;

import android.app.Application;
import android.view.LayoutInflater;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

import net.rdrei.android.connectivityalert.scope.ForApplication;

import dagger.Component;

public class CAApplication extends Application implements HasComponent<CAApplication.ApplicationComponent> {
    private ApplicationComponent mComponent;

    @Component(modules = ApplicationModule.class)
    @ForApplication
    public interface ApplicationComponent {
        @ForApplication
        LayoutInflater getLayoutInflater();
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

        mComponent = Dagger_CAApplication_ApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @Override
    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
