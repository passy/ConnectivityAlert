package net.rdrei.android.connectivityalert.data;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import net.rdrei.android.connectivityalert.ConnectivityObservable;

import javax.inject.Inject;

import rx.Observable;

public class ConnectivityModel {
    private final Observable<Intent> mConnectivityIntentObservable;
    private final ConnectivityManager mConnectivityManager;

    public enum ConnectivityState {
        CONNECTED,
        DISCONNECTED,
        PENDING
    }

    @Inject
    public ConnectivityModel(
            @ConnectivityObservable
            final Observable<Intent> connectivityIntentObservable,
            final ConnectivityManager connectivityManager) {
        mConnectivityIntentObservable = connectivityIntentObservable;
        mConnectivityManager = connectivityManager;
    }


    @NonNull
    public Observable<ConnectivityState> connectivity() {
        return mConnectivityIntentObservable.flatMap(intent -> Observable.just(isConnected() ?
                ConnectivityState.CONNECTED :
                ConnectivityState.DISCONNECTED));
    }

    boolean isConnected() {
        final NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
