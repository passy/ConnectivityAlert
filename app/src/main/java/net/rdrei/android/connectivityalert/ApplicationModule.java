package net.rdrei.android.connectivityalert;

import android.app.Application;
import android.view.LayoutInflater;

import net.rdrei.android.connectivityalert.scope.ForApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(final Application application) {
        mApplication = application;
    }

    @Provides
    @ForApplication
    public LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) mApplication.getSystemService(
                Application.LAYOUT_INFLATER_SERVICE);
    }
}
