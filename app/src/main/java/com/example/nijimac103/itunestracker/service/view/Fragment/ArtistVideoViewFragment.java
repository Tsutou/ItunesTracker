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

import static com.example.nijimac103.itunestracker.service.view.MainActivity.URL;

public class ArtistVideoViewFragment extends Fragment {

    public static final String TAG = "ArtistVideoViewFragment";

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
        int baseWidth = videoView.getMeasuredWidth();

        setHtmlBanner(v, getAfiScript(baseWidth));

        return v;
    }

    private void setHtmlBanner(View v, String data) {
        WebView webview = (WebView) v.findViewById(R.id.web_view);
        WebChromeClient client = new WebChromeClient();

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);

        webview.setWebChromeClient(client);
        webview.setForegroundGravity(Gravity.CENTER);
        webview.loadData(
                data, "text/html", null);
    }

    private String getAfiScript(int baseWidth) {

        String banner_script = "<div id='ibb-widget-root'></div>" +
                "<script>(function(t,e,i,d){var o=t.getElementById(i),n=t.createElement(e);" +
                "o.style.height=50;" +
                "o.style.width=320;" +
                "o.style.display='inline-block';" +
                "n.id='ibb-widget',n.setAttribute('src',('https:'===t.location.protocol?'https://':'http://')+d)," +
                "n.setAttribute('width','320')," +
                "n.setAttribute('height','50')," +
                "n.setAttribute('frameborder','10')," +
                "n.setAttribute('scrolling','no')," +
                "o.appendChild(n)})(document,'iframe','ibb-widget-root',\"" +
                "banners.itunes.apple.com/banner.html?partnerId=&aId=1001lMax" +
                "&bt=promotional&at=Music&st=apple_music&c=us&l=en-US&w=320&h=50&rs=1\");" +
                "</script>";

        return banner_script;
    }
}
