package com.example.nijimac103.itunestracker.service.view.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.util.FragmentUtils;
import com.example.nijimac103.itunestracker.service.util.WindowUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.example.nijimac103.itunestracker.service.view.MainActivity.URL;

public class ArtistVideoViewFragment extends Fragment {

    private AdView mAdView;

    public static final String TAG = "ArtistVideoViewFragment";
    private static final String BANNER_SCRIPT = "" +
            "<div style =\"text-align:center;\">" +
            "<div id='ibb-widget-root'>" +
            "</div>" +
            "</div>" +
            "<script>(function(t,e,i,d){var o=t.getElementById(i)," +
            "n=t.createElement(e);" +
            "o.style.height=50;" +
            "o.style.width=320;" +
            "o.style.display='inline-block';" +
            "n.id='ibb-widget'," +
            "n.setAttribute('src',('https:'===t.location.protocol?'https://':'http://')+d)," +
            "n.setAttribute('width','320')," +
            "n.setAttribute('height','50')," +
            "n.setAttribute('frameborder','0')," +
            "n.setAttribute('scrolling','no'),o.appendChild(n)})" +
            "(document,'iframe','ibb-widget-root',\"banners.itunes.apple.com/banner.html?" +
            "partnerId=" +
            "&aId=" +
            "&bt=promotional" +
            "&at=Music" +
            "&st=apple_music" +
            "&c=jp" +
            "&l=ja-JP" +
            "&w=320" +
            "&h=50" +
            "&rs=1\");" +
            "</script>";

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_video_view, container, false);

        String url = FragmentUtils.getArgsOfPreFragment(this, URL);

        VideoView videoView = v.findViewById(R.id.video_view);

        // インターネット上のファイルを再生
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
        videoView.setMediaController(new MediaController(v.getContext()));

        WebView webview = (WebView) v.findViewById(R.id.web_view);

        setHtmlBanner(webview, getAfiScript());

        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        return v;
    }

    private void setHtmlBanner(WebView v, String data) {
        WebChromeClient client = new WebChromeClient();

        WebSettings settings = v.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);

        v.setWebChromeClient(client);
        v.loadData(
                data, "text/html", null);
    }

    private String getAfiScript() {
        return BANNER_SCRIPT;
    }
}
