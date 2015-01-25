package net.rdrei.android.connectivityalert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;

import com.parse.ParseAnalytics;

import net.rdrei.android.connectivityalert.scope.ForActivity;
import net.rdrei.android.connectivityalert.ui.MainScreen;

import dagger.Component;


public class MainActivity extends Activity {
    private ActivityComponent mComponent;

    @Component(modules = ActivityModule.class)
    @ForActivity
    public interface ActivityComponent {
        void inject(MainScreen screen);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseAnalytics.trackEventInBackground("MainActivity.onCreate");

        mComponent = Dagger_MainActivity_ActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();

        final ViewGroup container = (ViewGroup) findViewById(android.R.id.content);
        getLayoutInflater().inflate(R.layout.activity_main, container);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public ActivityComponent getComponent() {
        return mComponent;
    }
}
