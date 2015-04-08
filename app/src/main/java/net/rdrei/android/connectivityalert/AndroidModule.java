package net.rdrei.android.connectivityalert;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;

import net.rdrei.android.connectivityalert.scope.ConnectivityObservable;
import net.rdrei.android.connectivityalert.scope.ForApplication;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.android.content.ContentObservable;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */
@Module
public class AndroidModule {
    private final CAApplication mApplication;

    public AndroidModule(CAApplication application) {
        mApplication = application;
    }

    /**
     * Allow the mApplication context to be injected but require that it be annotated with
     * {@link CAApplication @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @ForApplication
    public Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    public ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) mApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @ConnectivityObservable
    public Observable<Intent> provideConnectivityObservable() {
        final IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        return ContentObservable.fromBroadcast(mApplication, intentFilter);
    }

    @Provides
    @ForApplication
    public LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
