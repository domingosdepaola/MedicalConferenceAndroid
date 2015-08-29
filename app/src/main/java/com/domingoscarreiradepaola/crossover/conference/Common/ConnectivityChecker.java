package com.domingoscarreiradepaola.crossover.conference.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by domin on 27/08/2015.
 */
@EBean
public class ConnectivityChecker {
    @RootContext
    public Context context;

    public boolean isConnected() {

        ConnectivityManager connManager = (ConnectivityManager)this.context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo myWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo my3G = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return myWifi.isConnected() || my3G.isConnected();
    }
}
