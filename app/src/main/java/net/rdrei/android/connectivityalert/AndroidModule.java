package net.rdrei.android.connectivityalert;

import android.content.Context;
import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */
@Module
public class AndroidModule {
  private final CAApplication mApplication;

  public AndroidModule(CAApplication application) {
    this.mApplication = application;
  }

  /**
   * Allow the mApplication context to be injected but require that it be annotated with
   * {@link CAApplication @Annotation} to explicitly differentiate it from an activity context.
   */
  @Provides @Singleton @ForApplication Context provideApplicationContext() {
    return mApplication;
  }

  @Provides @Singleton LocationManager provideLocationManager() {
    return (LocationManager) mApplication.getSystemService(LOCATION_SERVICE);
  }
}
