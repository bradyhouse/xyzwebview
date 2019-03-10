package io.bradyhouse.xyzwebview;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsSession;

public class XyzWebView {
    private static CustomTabsServiceConnectionCallBack customTabsServiceConnection;
    private XyzWebViewListener webViewListener;
    private CustomTabsClient customTabsClient;
    private static CustomTabsCallbackListener customTabsCallbackListener;
    private boolean warmUpView = false;
    private CustomTabsSession customTabsSession;
    private CustomTabsIntent customTabsIntent;
    private CustomTabsIntent.Builder builder;
    private Context mContext;
    public static final int REQUEST_CODE = 1868;
    public static final String PACKAGE_NAME = "com.android.chrome";

    public XyzWebView(Context context, @Nullable XyzWebViewListener listener) {
        this.mContext = context;
        setWebViewListener(listener);
        setUp();
    }

    void setUp() {
        if (customTabsServiceConnection.getCustomTabsClient() != null) {
            this.customTabsClient = customTabsServiceConnection.getCustomTabsClient();
            this.customTabsSession = this.customTabsClient.newSession(customTabsCallbackListener);
            this.builder = new CustomTabsIntent.Builder(this.customTabsSession);
        }
    }

    public static void init(Context context, boolean warmUp) {
        if (customTabsServiceConnection == null) {
            customTabsServiceConnection = new CustomTabsServiceConnectionCallBack(null, warmUp);
        }
        if (customTabsCallbackListener == null) {
            customTabsCallbackListener = new CustomTabsCallbackListener(null);
        }
        CustomTabsClient.bindCustomTabsService(context, "com.android.chrome", customTabsServiceConnection);
    }

    public void setWebViewListener(XyzWebViewListener listener) {
        this.webViewListener = listener;
        customTabsServiceConnection.setWebViewListener(listener);
        customTabsCallbackListener.setWebViewListener(listener);
    }

    public void setCustomTabsClient(CustomTabsClient client) {
        this.customTabsClient = client;
    }

    public CustomTabsClient getCustomTabsClient() {
        return this.customTabsClient;
    }

    public void loadUrl(String string) {
        PackageManager pm = this.mContext.getPackageManager();
        try {
            ApplicationInfo info = pm.getApplicationInfo("com.android.chrome", 0);
            if (info.enabled) {
                setUp();
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.builder != null) {
                    this.customTabsIntent = this.builder.build();
                    this.customTabsIntent.intent.setPackage("com.android.chrome");
                }
            }
            if (this.customTabsIntent != null) {
                this.customTabsIntent.launchUrl(this.mContext, Uri.parse(string));
            } else {
                WebViewFallback fallback = new WebViewFallback();
                fallback.openUri((Activity) this.mContext, Uri.parse(string));
            }
        } catch (PackageManager.NameNotFoundException e) {
            WebViewFallback fallback = new WebViewFallback();
            fallback.openUri((Activity) this.mContext, Uri.parse(string));
        }
    }

    public CustomTabsSession getSession() {
        return this.customTabsSession;
    }

    public CustomTabsIntent.Builder getBuilder() {
        return this.builder;
    }
}
