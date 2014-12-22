package net.rdrei.android.connectivityalert.ui;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.AttributeSet;
import android.widget.TextView;

import net.rdrei.android.connectivityalert.MainActivity;

import javax.inject.Inject;

import rx.Observable;
import rx.android.observables.AndroidObservable;

public class HelloView extends TextView {
    @Inject
    ActionBar mActionBar;

    @Inject
    ConnectivityManager mConnectivityManager;

    public HelloView(final Context context) {
        super(context);
    }

    public HelloView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public HelloView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ((MainActivity) getContext()).getComponent().inject(this);

        updateUI();

        final IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        final Observable<Intent> connectivityObservable = AndroidObservable.fromBroadcast(getContext(), intentFilter);
        connectivityObservable.subscribe(intent -> updateUI());
    }

    boolean isConnected() {
        final NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    void updateUI() {
        setText("Connected: " + isConnected());
        mActionBar.setTitle("HelloView");
    }
}
