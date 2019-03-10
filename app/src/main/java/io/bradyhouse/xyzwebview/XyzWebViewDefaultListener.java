package io.bradyhouse.xyzwebview;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.util.Log;

public class XyzWebViewDefaultListener implements XyzWebViewListener {

    private static final String TAG = "XyzDefaultListener";

    @Override
    public void onCustomTabsServiceConnected(ComponentName paramComponentName, CustomTabsClient paramCustomTabsClient) {
        Log.d(TAG, "onCustomTabsServiceConnected");
        Log.d(TAG, "paramComponentName = " + paramComponentName);
        Log.d(TAG, "paramCustomTabsClient = " + paramCustomTabsClient.toString());

    }

    @Override
    public void onServiceDisconnected(ComponentName paramComponentName) {
        Log.d(TAG, "onServiceDisconnected");
        Log.d(TAG, "paramComponentName = " +paramComponentName);
    }

    @Override
    public void onNavigationEvent(int paramInt, Bundle paramBundle) {
        Log.d(TAG, "onNavigationEvent");
        Log.d(TAG, "paramInt = " + paramInt);
        Log.d(TAG, "paramBundle = " + paramBundle.toString());
    }
}
