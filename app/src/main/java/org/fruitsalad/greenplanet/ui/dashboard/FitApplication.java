package org.fruitsalad.greenplanet.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.Application;

@SuppressLint("Registered")
public class FitApplication extends Application {

    private GoogleApiManager mGoogleApiManager;
    private static FitApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mGoogleApiManager = new GoogleApiManager(mInstance);
    }

    public static synchronized FitApplication getInstance() {
        return mInstance;
    }

    public GoogleApiManager getGoogleApiMangerInstance() {
        return this.mGoogleApiManager;
    }

    public static GoogleApiManager getGoogleApiManager() {
        return mInstance.getGoogleApiMangerInstance();
    }
}
