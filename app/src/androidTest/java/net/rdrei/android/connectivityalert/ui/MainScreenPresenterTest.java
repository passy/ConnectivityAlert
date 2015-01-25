package net.rdrei.android.connectivityalert.ui;

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
    ConnectivityModel mModelMock;

    private MainScreenPresenter mPresenter;
    private PublishSubject<ConnectivityModel.Connectivity> mConnectivitySubject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.initMocks(this);

        mConnectivitySubject = PublishSubject.create();
        when(mModelMock.connectivity()).thenReturn(mConnectivitySubject);
        mPresenter = new MainScreenPresenter(mViewMock, mModelMock);
        mPresenter.onStart();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        mPresenter.onStop();
    }

    @SmallTest
    public void testShowsConnectedView() {
        mConnectivitySubject.onNext(
                new ConnectivityModel.Connectivity(ConnectivityModel.ConnectivityState.CONNECTED));
        verify(mViewMock).inflateInnerView(R.layout.ui_connected_view);
    }

    @SmallTest
    public void testShowsDisconnectedView() {
        mConnectivitySubject.onNext(
                new ConnectivityModel.Connectivity(
                        ConnectivityModel.ConnectivityState.DISCONNECTED));
        verify(mViewMock).inflateInnerView(R.layout.ui_disconnected_view);
    }
}
