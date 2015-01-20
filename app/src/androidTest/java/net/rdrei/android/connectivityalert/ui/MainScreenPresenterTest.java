package net.rdrei.android.connectivityalert.ui;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import net.rdrei.android.connectivityalert.R;
import net.rdrei.android.connectivityalert.data.ConnectivityModel;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.subjects.PublishSubject;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainScreenPresenterTest extends TestCase {
    @Mock
    MainScreen mViewMock;

    @Mock
    ConnectivityManager mConnectivityManager;

    @Mock
    NetworkInfo mNetworkInfo;

    private MainScreenPresenter mPresenter;
    private ConnectivityModel mModel;
    private PublishSubject<Intent> mConnectivitySubject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.initMocks(this);
        when(mConnectivityManager.getActiveNetworkInfo()).thenReturn(mNetworkInfo);

        mConnectivitySubject = PublishSubject.create();
        mModel = new ConnectivityModel(mConnectivitySubject, mConnectivityManager);
        mPresenter = new MainScreenPresenter(mViewMock, mModel);
        mPresenter.onStart();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        mPresenter.onStop();
    }

    @SmallTest
    public void testShowsConnectedView() {
        when(mNetworkInfo.isConnected()).thenReturn(true);
        mConnectivitySubject.onNext(new Intent());
        verify(mViewMock).inflateInnerView(R.layout.ui_connected_view);
    }

    @SmallTest
    public void testShowsDisconnectedView() {
        when(mNetworkInfo.isConnected()).thenReturn(false);
        mConnectivitySubject.onNext(new Intent());
        verify(mViewMock).inflateInnerView(R.layout.ui_disconnected_view);
    }
}