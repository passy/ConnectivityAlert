package net.rdrei.android.connectivityalert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;

import com.parse.ParseAnalytics;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}
