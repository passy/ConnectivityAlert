package net.rdrei.android.connectivityalert.ui;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import net.rdrei.android.connectivityalert.ConnectivityObservable;
import net.rdrei.android.connectivityalert.R;

import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

public class MainScreenPresenter implements Presenter {
    final MainScreen mView;
    final private Collection<Subscription> mSubscriptions = new LinkedList<>();
    private final Observable<Intent> mConnectivityObservable;
    private final ConnectivityManager mConnectivityManager;

    @Inject
    public MainScreenPresenter(
            @NonNull final MainScreen view,
            @NonNull @ConnectivityObservable final Observable<Intent> connectivityObservable,
            @NonNull final ConnectivityManager connectivityManager
    ) {
        mView = view;
        mConnectivityObservable = connectivityObservable;
        mConnectivityManager = connectivityManager;
    }

    @Override
    public void onStart() {
        // TODO: Use a proper observable that's Observable<ConnectivityStatus>
        mSubscriptions.add(mConnectivityObservable.subscribe(intent -> updateUI()));
    }

    @Override
    public void onStop() {
        for (final Subscription subscription : mSubscriptions) {
            subscription.unsubscribe();
        }
    }

    void updateUI() {
        final int containerViewId;

        // TODO: Presenter, animate!
        if (isConnected()) {
            containerViewId = R.layout.ui_connected_view;
        } else {
            containerViewId = R.layout.ui_disconnected_view;
        }

        mView.inflateInnerView(containerViewId);
    }

    boolean isConnected() {
        final NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
