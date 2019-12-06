package jp.co.geisha.itunestracker.service.view.fragment

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.MediaController
import android.widget.VideoView

import jp.co.geisha.itunestracker.R
import jp.co.geisha.itunestracker.service.util.FragmentUtils

import jp.co.geisha.itunestracker.service.BANNER_SCRIPT
import jp.co.geisha.itunestracker.service.URL

class ArtistVideoViewFragment : Fragment() {

    private val afiScript: String
        get() = BANNER_SCRIPT

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_video_view, container, false)

        val url = FragmentUtils.getArgsOfPreFragment(this, URL)

        val videoView = v.findViewById<VideoView>(R.id.video_view)

        // インターネット上のファイルを再生
        videoView.setVideoURI(Uri.parse(url))
        videoView.start()
        videoView.setMediaController(MediaController(v.context))

        val webview = v.findViewById<View>(R.id.web_view) as WebView

        setHtmlBanner(webview, afiScript)

        return v
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setHtmlBanner(v: WebView, data: String) {
        val client = WebChromeClient()

        val settings = v.settings
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true

        v.webChromeClient = client
        v.loadData(
                data, "text/html", null)
    }
}
