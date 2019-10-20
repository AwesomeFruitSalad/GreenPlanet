package org.fruitsalad.greenplanet.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;


public class GoogleApiManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = GoogleApiManager.class.getName();
    private Context mContext;
    private Bundle connectionBundle;
    private ConnectionListener connectionListener;
    private boolean authInProgress = false;

    public GoogleApiManager(Context context) {
        this.mContext = context;
        //GoogleApiClient mGoogleApiClient = GoogleApiSingletone.getInstance()
        buildGoogleApi();
        connect();

    }

    public void connect() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    public void disconnect() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void buildGoogleApi() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext).
                addApi(Fitness.HISTORY_API)
                .addApi(Fitness.RECORDING_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public void setConnectionListener(ConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
        if (this.connectionListener != null && isConnected()) {
            connectionListener.onConnected(connectionBundle);
        }

    }

    public GoogleApiClient getmGoogleApiClient() {
        return this.mGoogleApiClient;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        this.connectionBundle = bundle;
        if (connectionListener != null) {
            connectionListener.onConnected(bundle);
            Log.d(TAG, "Connected");
        }

    }

    public boolean isConnected() {
        return mGoogleApiClient != null && mGoogleApiClient.isConnected();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
        if (connectionListener != null) {
            Log.d(TAG, "Suspended");
            connectionListener.onConnectionSuspended(i);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionListener != null) {
            connectionListener.onConnectionFailed(connectionResult);
            Log.d(TAG, "Failed" + connectionResult.getErrorMessage());
        }
    }

    public interface ConnectionListener {

        void onConnectionFailed(@NonNull ConnectionResult connectionResult);

        void onConnectionSuspended(int i);

        void onConnected(Bundle bundle);
    }
}
