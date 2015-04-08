package net.rdrei.android.connectivityalert;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.parse.ParseAnalytics;

import net.rdrei.android.connectivityalert.scope.ConnectivityObservable;
import net.rdrei.android.connectivityalert.scope.ForActivity;

import dagger.Component;
import rx.Observable;


public class MainActivity extends Activity implements HasComponent<MainActivity.ActivityComponent> {
    private ActivityComponent mComponent;

    @Component(modules = { ActivityModule.class, AndroidModule.class })
    public interface ActivityComponent {
        @ForActivity
        public ActionBar getActionBar();
        @ConnectivityObservable
        public Observable<Intent> getConnectivityObservable();
        public ConnectivityManager getConnectivityManager();
        @ForActivity
        public LayoutInflater getLayoutInflater();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((CAApplication) getApplication()).getComponent().inject(this);
        mComponent = DaggerMainActivity_ActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .androidModule(new AndroidModule((CAApplication) this.getApplication()))
                .build();

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseAnalytics.trackEventInBackground("MainActivity.onCreate");

        final ViewGroup container = (ViewGroup) findViewById(android.R.id.content);
        getLayoutInflater().inflate(R.layout.activity_main, container);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public ActivityComponent getComponent() {
        return mComponent;
    }
}
