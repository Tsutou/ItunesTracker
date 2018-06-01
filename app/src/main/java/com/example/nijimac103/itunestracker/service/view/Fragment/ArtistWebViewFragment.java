package com.example.nijimac103.itunestracker.service.view.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.util.FragmentUtils;

public class ArtistWebViewFragment extends Fragment {

    public static final String TAG = "ArtistWebViewFragment";

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_web_view, container, false);

        String url = FragmentUtils.getArgsOfPreFragment(this,"URL");

        WebView mWebView = (WebView)v.findViewById(R.id.webview);

        mWebView.setWebViewClient(new WebViewClient());

        mWebView.loadUrl(url);

        return v;
    }
}
