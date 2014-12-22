package net.rdrei.android.connectivityalert.ui;

import android.app.ActionBar;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import net.rdrei.android.connectivityalert.MainActivity;

import javax.inject.Inject;

public class HelloView extends TextView {
    @Inject
    ActionBar mActionBar;

    public HelloView(final Context context) {
        super(context);
    }

    public HelloView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public HelloView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HelloView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        final MainActivity ctx = (MainActivity) getContext();
        ctx.inject(this);
        setText("Hello, World!");
        mActionBar.setTitle("HelloView");
    }
}
