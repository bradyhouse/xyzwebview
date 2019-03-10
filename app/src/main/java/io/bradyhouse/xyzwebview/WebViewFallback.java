package io.bradyhouse.xyzwebview;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class WebViewFallback implements CustomTabFallback {
    public void openUri(Activity activity, Uri uri)
    {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("extra.url", uri.toString());
        activity.startActivityForResult(intent, 1868);
    }
}
