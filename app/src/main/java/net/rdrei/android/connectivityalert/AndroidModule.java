package net.rdrei.android.connectivityalert;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.android.observables.AndroidObservable;

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
    @Singleton
    @ForApplication
    public Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) mApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    @ConnectivityObservable
    public Observable<Intent> provideConnectivityObservable() {
        final IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        return AndroidObservable.fromBroadcast(mApplication, intentFilter);
    }

    @Provides
    @Singleton
    @ForApplication
    public LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
