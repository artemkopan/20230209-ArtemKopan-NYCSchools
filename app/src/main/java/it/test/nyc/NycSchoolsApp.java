package it.test.nyc;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

@HiltAndroidApp
public class NycSchoolsApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    //FIXME need to define custom logging on release builds
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
