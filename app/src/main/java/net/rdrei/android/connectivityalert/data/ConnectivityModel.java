package net.rdrei.android.connectivityalert.data;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import net.rdrei.android.connectivityalert.scope.ConnectivityObservable;

import java.util.Date;

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

    public static class Connectivity {
        public final Date timestamp;
        public final ConnectivityState state;

        public Connectivity(final ConnectivityState state, final Date timestamp) {
            this.timestamp = timestamp;
            this.state = state;
        }

        public Connectivity(final ConnectivityState state) {
            this.timestamp = new Date();
            this.state = state;
        }
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
    public Observable<Connectivity> connectivity() {
        return mConnectivityIntentObservable.flatMap(intent -> Observable.just(isConnected() ?
                new Connectivity(ConnectivityState.CONNECTED) :
                new Connectivity(ConnectivityState.DISCONNECTED)));
    }

    boolean isConnected() {
        final NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
