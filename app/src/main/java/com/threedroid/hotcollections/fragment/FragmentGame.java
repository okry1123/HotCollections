package com.threedroid.hotcollections.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.threedroid.hotcollections.R;

/**
 * Created by Administrator on 2014-8-28.
 */
public class FragmentGame extends android.support.v4.app.Fragment {

    private String mUrl;
    private WebView mWebView;

    public static FragmentGame launch(String url){
        FragmentGame fragment = new FragmentGame();
        Bundle b = new Bundle();
        b.putString("url", url);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUrl = getArguments().getString("url");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, null);
        mWebView = (WebView) root.findViewById(R.id.webView);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);

                ((ActionBarActivity)getActivity()).getSupportActionBar().show();
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                ((ActionBarActivity)getActivity()).setProgressBarIndeterminateVisibility(false);
            }
            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                ((ActionBarActivity)getActivity()).setProgressBarIndeterminateVisibility(true);
            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        mWebView.loadUrl(mUrl);
    }
}
