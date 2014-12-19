package net.rdrei.android.connectivityalert;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

public class CAApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Add your initialization code here
        Parse.initialize(this, "m7NZAvarDqfFKgtjt6T61079YAINIejqeZygkl2a",
                "YBqthMB9YKdff5ltlLWHZQMUL2qj60avuoStRH3z");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
