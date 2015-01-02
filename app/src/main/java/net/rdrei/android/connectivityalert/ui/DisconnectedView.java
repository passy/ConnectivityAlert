package net.rdrei.android.connectivityalert.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.rdrei.android.connectivityalert.R;
import net.rdrei.android.connectivityalert.data.ConnectivityModel;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;

public class DisconnectedView extends RelativeLayout {
    @InjectView(R.id.disconnected_text)
    TextView mDisconnectedText;

    @Inject
    Observable<ConnectivityModel.Connectivity> mConnectivityObservable;

    public DisconnectedView(final Context context) {
        super(context);
    }

    public DisconnectedView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public DisconnectedView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DisconnectedView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
    }

    private void draw() {
        // Draw time stamp
    }
}
