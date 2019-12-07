package jp.co.geisha.diggin.view.fragment

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

import jp.co.geisha.diggin.R
import jp.co.geisha.diggin.util.FragmentUtils

import jp.co.geisha.diggin.URL
import kotlinx.android.synthetic.main.fragment_video_view.view.*

class ArtistVideoViewFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_video_view, container, false)

        val url = FragmentUtils.getArgsOfPreFragment(this, URL)

        view.videoView.apply {
            setVideoURI(Uri.parse(url))
            start()
            setMediaController(MediaController(view.context))
        }

        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setHtmlBanner(v: WebView, data: String) {
        val client = WebChromeClient()

        val settings = v.settings
        settings.apply {
            javaScriptEnabled = true
            allowFileAccess = true
        }

        v.webChromeClient = client
        v.loadData(data, "text/html", null)
    }
}
