package net.rdrei.android.connectivityalert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseAnalytics;

import dagger.Component;


public class MainActivity extends Activity {
    private ActivityComponent mComponent;

    @Component(modules = ActivityModule.class)
    interface ActivityComponent {
        void inject(View view);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseAnalytics.trackEventInBackground("MainActivity.onCreate");
        final ViewGroup container = (ViewGroup) findViewById(android.R.id.content);
        getLayoutInflater().inflate(R.layout.activity_main, container);

        ((CAApplication) getApplication()).getComponent().inject(this);
        mComponent = Dagger_MainActivity$ActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void inject(final View view) {
        mComponent.inject(view);
    }
}
