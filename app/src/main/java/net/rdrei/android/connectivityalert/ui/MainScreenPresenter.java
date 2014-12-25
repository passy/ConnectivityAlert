package net.rdrei.android.connectivityalert.ui;

import android.support.annotation.NonNull;

import net.rdrei.android.connectivityalert.R;
import net.rdrei.android.connectivityalert.data.ConnectivityModel;

import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;

import rx.Subscription;

public class MainScreenPresenter implements Presenter {
    final MainScreen mView;
    final private Collection<Subscription> mSubscriptions = new LinkedList<>();
    private final ConnectivityModel mModel;

    @Inject
    public MainScreenPresenter(
            @NonNull final MainScreen view,
            @NonNull final ConnectivityModel model
    ) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onStart() {
        mSubscriptions.add(mModel.connectivity().subscribe(this::updateUI));
    }

    @Override
    public void onStop() {
        for (final Subscription subscription : mSubscriptions) {
            subscription.unsubscribe();
        }
    }

    void updateUI(final ConnectivityModel.ConnectivityState state) {
        final int containerViewId;

        if (state.equals(ConnectivityModel.ConnectivityState.CONNECTED)) {
            containerViewId = R.layout.ui_connected_view;
        } else {
            containerViewId = R.layout.ui_disconnected_view;
        }

        mView.inflateInnerView(containerViewId);
    }
}
