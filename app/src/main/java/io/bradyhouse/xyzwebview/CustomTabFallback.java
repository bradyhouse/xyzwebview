package io.bradyhouse.xyzwebview;

import android.app.Activity;
import android.net.Uri;

public abstract interface CustomTabFallback {
    public abstract void openUri(Activity paramActivity, Uri paramUri);
}
