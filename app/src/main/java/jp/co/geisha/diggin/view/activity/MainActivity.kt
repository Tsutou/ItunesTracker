package jp.co.geisha.diggin.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

import jp.co.geisha.diggin.R
import jp.co.geisha.diggin.api.entity.ItunesData
import jp.co.geisha.diggin.util.FragmentUtils

import jp.co.geisha.diggin.ARTIST_TABS_FRAGMENT_TAG
import jp.co.geisha.diggin.view.fragment.MusicVideoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = MusicVideoListFragment()

            FragmentUtils.insertFragmentToActivity(
                    R.id.fragmentContainer,
                    supportFragmentManager,
                    fragment,
                    ARTIST_TABS_FRAGMENT_TAG)
        }
    }

    fun show(itunesData: ItunesData) {
        if (itunesData.previewUrl.isNotEmpty()) {
            val intent = ItunesVideoViewActivity.createIntent(this,itunesData.previewUrl)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "This Video has not Preview", Toast.LENGTH_SHORT).show()
        }
    }

}
