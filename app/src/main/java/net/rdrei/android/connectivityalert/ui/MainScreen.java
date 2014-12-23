package net.rdrei.android.connectivityalert.ui;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import net.rdrei.android.connectivityalert.ConnectivityObservable;
import net.rdrei.android.connectivityalert.ForActivity;
import net.rdrei.android.connectivityalert.MainActivity;
import net.rdrei.android.connectivityalert.R;

import java.util.LinkedList;
import java.util.Queue;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Subscription;

public class MainScreen extends FrameLayout {
    @Inject
    ActionBar mActionBar;

    @Inject
    @ConnectivityObservable
    Observable<Intent> mConnectivityObservable;

    @Inject
    ConnectivityManager mConnectivityManager;

    @Inject
    @ForActivity
    LayoutInflater mLayoutInflater;

    private ViewHolder mViewHolder;
    final private Queue<Subscription> mSubscriptions = new LinkedList<>();

    public MainScreen(final Context context) {
        super(context);
        init(context);
    }

    public MainScreen(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MainScreen(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        final View view = inflate(context, R.layout.ui_main_screen, this);
        mViewHolder = new ViewHolder(view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ((MainActivity) getContext()).getComponent().inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        // TODO: Use a proper observable
        mSubscriptions.add(mConnectivityObservable.subscribe(intent -> updateUI()));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        for (final Subscription subscription : mSubscriptions) {
            subscription.unsubscribe();
        }
    }

    boolean isConnected() {
        final NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    void updateUI() {
        mActionBar.setTitle("HelloView");
        final int containerViewId;

        // TODO: Presenter, animate!
        if (isConnected()) {
            containerViewId = R.layout.ui_connected_view;
        } else {
            containerViewId = R.layout.ui_disconnected_view;
        }

        mViewHolder.mProgressBar.setVisibility(GONE);
        mViewHolder.mViewContainer.removeAllViewsInLayout();
        mLayoutInflater.inflate(containerViewId, mViewHolder.mViewContainer);
        mViewHolder.mViewContainer.setVisibility(VISIBLE);
        mViewHolder.mViewContainer.invalidate();
    }

    static class ViewHolder {
        @InjectView(R.id.progress_view)
        ProgressBar mProgressBar;

        @InjectView(R.id.connectivity_view_stub)
        ViewGroup mViewContainer;

        ViewHolder(final View root) {
            ButterKnife.inject(this, root);
        }
    }
}
