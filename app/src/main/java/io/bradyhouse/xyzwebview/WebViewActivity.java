package io.bradyhouse.xyzwebview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "https://www.duckduckgo.com";
    private static final String TAG = "WebViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        String url = EXTRA_URL;
        WebView webView = (WebView)findViewById(R.id.webview);
        String userAgent = webView.getSettings().getUserAgentString();
        WebViewClient client = new WebViewClient();
        WebChromeClient chromeClient = new WebChromeClient();
        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(client);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setUserAgentString(cleanUserAgentString(userAgent));
        webSettings.getJavaScriptCanOpenWindowsAutomatically();
        if(android.os.Build.VERSION.SDK_INT >= 21){
            webSettings.setMixedContentMode(0);
            webView.setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null);
        }else if(android.os.Build.VERSION.SDK_INT >= 19){
            webView.setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null);
        }else {
            webView.setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null);
        }
        setTitle(url);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        webView.loadUrl(url);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case 16908332:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String cleanUserAgentString(String useragent) {
        String[] words = useragent.split(" ");
        String out = "";
        for(int i =0; i < words.length ; i++) {
            if(words[i] == "wv)"){
                out += ") ";
            } else {
                out += words[i]+" ";
            }
        }
        return out;
    }

}
