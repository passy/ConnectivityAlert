package net.rdrei.android.connectivityalert.ui;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.LayoutRes;
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

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;

// TODO: Extract an interface from this.
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

    private MainScreenPresenter mPresenter;
    private ViewHolder mViewHolder;

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
        // Figure out how to inject this, including the View
        mPresenter = new MainScreenPresenter(this, mConnectivityObservable, mConnectivityManager);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // TODO: Consider making a base class that allows hooking into the lifecycle
        //       via observables.
        mPresenter.onStart();
        // Just as an example.
        mActionBar.setTitle("HelloView");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPresenter.onStop();
    }

    public void inflateInnerView(@LayoutRes final int resId) {
        mViewHolder.mProgressBar.setVisibility(GONE);
        mViewHolder.mViewContainer.removeAllViewsInLayout();
        mLayoutInflater.inflate(resId, mViewHolder.mViewContainer);
        mViewHolder.mViewContainer.setVisibility(VISIBLE);
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
