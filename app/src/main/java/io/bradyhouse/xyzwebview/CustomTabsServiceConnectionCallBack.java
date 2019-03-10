package io.bradyhouse.xyzwebview;


import android.content.ComponentName;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;

public class CustomTabsServiceConnectionCallBack extends CustomTabsServiceConnection {
    private XyzWebViewListener webViewListener;
    private boolean warmUpView = false;
    private CustomTabsClient mClient;

    public CustomTabsServiceConnectionCallBack(@Nullable XyzWebViewListener listener, boolean warmUp) {
        this.webViewListener = listener;
        this.warmUpView = warmUp;
    }

    public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
        if (this.warmUpView) {
            client.warmup(0L);
        }
        if (this.webViewListener != null) {
            this.webViewListener.onCustomTabsServiceConnected(name, client);
        }
        this.mClient = client;
    }

    public void onServiceDisconnected(ComponentName name) {
        if (this.webViewListener != null) {
            this.webViewListener.onServiceDisconnected(name);
        }
    }

    public void setWebViewListener(XyzWebViewListener listener) {
        this.webViewListener = listener;
    }

    public CustomTabsClient getCustomTabsClient() {
        return this.mClient;
    }
}
