package jp.co.geisha.diggin.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import jp.co.geisha.diggin.R
import jp.co.geisha.diggin.util.FragmentUtils
import jp.co.geisha.diggin.view.fragment.ArtistVideoViewFragment

import jp.co.geisha.diggin.ARTIST_VIDEO_VIEW_FRAGMENT_TAG
import jp.co.geisha.diggin.URL

class VideoViewActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, url: String): Intent {
            return Intent(context, VideoViewActivity::class.java).apply {
                putExtra(URL, url)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)

        val artistPreviewUrl = intent?.getStringExtra(URL)

        artistPreviewUrl?.let {
            addVideoViewFragment(ArtistVideoViewFragment(), artistPreviewUrl)
        } ?: run {
            finish()
        }
    }

    private fun addVideoViewFragment(fragment: ArtistVideoViewFragment, artistPreviewUrl: String) {
        FragmentUtils.run {
            setArgsToFragment(fragment, URL, artistPreviewUrl)
            insertFragmentToActivity(R.id.fragmentContainer, supportFragmentManager, fragment, ARTIST_VIDEO_VIEW_FRAGMENT_TAG)
        }
    }
}
