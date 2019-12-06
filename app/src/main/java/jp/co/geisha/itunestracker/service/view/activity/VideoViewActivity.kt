package jp.co.geisha.itunestracker.service.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import jp.co.geisha.itunestracker.R
import jp.co.geisha.itunestracker.service.util.FragmentUtils
import jp.co.geisha.itunestracker.service.view.fragment.ArtistVideoViewFragment

import jp.co.geisha.itunestracker.service.ARTIST_VIDEO_VIEW_FRAGMENT_TAG
import jp.co.geisha.itunestracker.service.URL

class VideoViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)

        val artistPreviewUrl = intent.getStringExtra(URL)
        addVideoViewFragment(ArtistVideoViewFragment(), artistPreviewUrl)
    }

    private fun addVideoViewFragment(fragment: ArtistVideoViewFragment, artistPreviewUrl: String) {
        FragmentUtils.run {
            setArgsToFragment(fragment, URL, artistPreviewUrl)
            insertFragmentToActivity(R.id.fragment_container, supportFragmentManager, fragment, ARTIST_VIDEO_VIEW_FRAGMENT_TAG)
        }
    }
}
