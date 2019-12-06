package jp.co.geisha.itunestracker.service.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import jp.co.geisha.itunestracker.R
import jp.co.geisha.itunestracker.service.model.Artist
import jp.co.geisha.itunestracker.service.util.FragmentUtils

import android.text.TextUtils.isEmpty
import jp.co.geisha.itunestracker.service.ARTIST_TABS_FRAGMENT_TAG
import jp.co.geisha.itunestracker.service.URL
import jp.co.geisha.itunestracker.service.view.fragment.ArtistListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            val fragment = ArtistListFragment()

            FragmentUtils.insertFragmentToActivity(
                    R.id.fragment_container,
                    supportFragmentManager,
                    fragment,
                    ARTIST_TABS_FRAGMENT_TAG)
        }

    }

    fun show(artist: Artist) {
        if (!isEmpty(artist.previewUrl)) {
            val i = Intent(applicationContext, VideoViewActivity::class.java)
            i.putExtra(URL, artist.previewUrl)
            startActivity(i)
        } else {
            Toast.makeText(applicationContext, "This Video has not Preview", Toast.LENGTH_SHORT).show()
        }
    }

}
