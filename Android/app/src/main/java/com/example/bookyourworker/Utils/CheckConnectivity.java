package com.example.bookyourworker.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckConnectivity {

    static ConnectivityManager connectivityManager;
    /**
     * Network info object.
     */
    static NetworkInfo wifiInfo;
    /**
     * Network info object.
     */
    static NetworkInfo mobileInfo;

    /**
     * The method isConnected ,checks wheather the device is connected with net.
     *
     * @param context Context.
     * @return boolean value.
     */
    public static boolean isConnected(Context context) {

        try {
            context = ApplicationBase.getAppBaseContext();
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = false;
            if (activeNetwork != null) {
                isConnected = activeNetwork.isConnectedOrConnecting();
            }
            if (isConnected) {
                boolean isWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                        .isAvailable();
            }
            return isConnected;
        } catch (Exception e) {
            return false;
        }

    }

}
