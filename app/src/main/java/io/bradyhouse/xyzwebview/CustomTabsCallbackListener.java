package io.bradyhouse.xyzwebview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsCallback;

public class CustomTabsCallbackListener extends CustomTabsCallback {
    private XyzWebViewListener webViewListener;

    public CustomTabsCallbackListener(@Nullable XyzWebViewListener listener) {
        this.webViewListener = listener;
    }

    public void onNavigationEvent(int navigationEvent, Bundle extras) {
        if (this.webViewListener != null) {
            this.webViewListener.onNavigationEvent(navigationEvent, extras);
        }
    }

    public void setWebViewListener(XyzWebViewListener listener) {
        this.webViewListener = listener;
    }
}
