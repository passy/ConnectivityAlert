package net.rdrei.android.connectivityalert.ui;

import android.app.ActionBar;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import net.rdrei.android.connectivityalert.MainActivity;
import net.rdrei.android.connectivityalert.scope.ForActivity;

import javax.inject.Inject;

public class MainScreen extends FrameLayout {
    @Inject
    @ForActivity
    ActionBar mActionBar;

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
        ((MainActivity) context).getComponent().inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActionBar.setTitle("Hello, World!");
    }
}
