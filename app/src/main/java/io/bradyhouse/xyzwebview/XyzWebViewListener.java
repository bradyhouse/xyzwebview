package io.bradyhouse.xyzwebview;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;

public abstract interface XyzWebViewListener {

    public abstract void onCustomTabsServiceConnected(ComponentName paramComponentName, CustomTabsClient paramCustomTabsClient);

    public abstract void onServiceDisconnected(ComponentName paramComponentName);

    public abstract void onNavigationEvent(int paramInt, Bundle paramBundle);
}
