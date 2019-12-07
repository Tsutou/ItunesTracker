package jp.co.geisha.diggin.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

import jp.co.geisha.diggin.R
import jp.co.geisha.diggin.api.entity.Artist
import jp.co.geisha.diggin.util.FragmentUtils

import jp.co.geisha.diggin.ARTIST_TABS_FRAGMENT_TAG
import jp.co.geisha.diggin.view.fragment.ArtistVideoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ArtistVideoListFragment()

            FragmentUtils.insertFragmentToActivity(
                    R.id.fragmentContainer,
                    supportFragmentManager,
                    fragment,
                    ARTIST_TABS_FRAGMENT_TAG)
        }
    }

    fun show(artist: Artist) {
        if (artist.previewUrl.isNotEmpty()) {
            val intent = VideoViewActivity.createIntent(this,artist.previewUrl)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "This Video has not Preview", Toast.LENGTH_SHORT).show()
        }
    }

}
