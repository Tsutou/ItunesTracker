package jp.co.geisha.itunestracker.service.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.Handler
import android.text.TextUtils
import jp.co.geisha.itunestracker.service.*

import jp.co.geisha.itunestracker.service.model.ArtistList
import jp.co.geisha.itunestracker.service.repository.ArtistRepository
import timber.log.Timber

import jp.co.geisha.itunestracker.service.ConstArrays.DEFAULT_ARTIST_LIST
import jp.co.geisha.itunestracker.service.util.CalcUtils.getRand

class ArtistListViewModel(application: Application) : AndroidViewModel(application) {

    //LiveDataのゲッター
    var artistListObservable: LiveData<ArtistList>? = null
        private set
    private val repo: ArtistRepository
    private var count: Int = 0

    init {
        setUpRequestScheduler()

        repo = ArtistRepository.instance

        artistListObservable = repo.getArtistList(DEFAULT_ARTIST_LIST[getRand(DEFAULT_ARTIST_LIST.size)], MUSIC_VIDEO, LIMIT)
    }

    fun reloadArtists(text: CharSequence) {
        if (count <= MAX_REQUEST_PER_MINUTE) {
            if (TextUtils.isEmpty(text)) {
                artistListObservable = repo.getArtistList(DEFAULT_ARTIST_LIST[getRand(DEFAULT_ARTIST_LIST.size)], MUSIC_VIDEO, LIMIT)
            } else {
                artistListObservable = repo.getArtistList(text.toString(), MUSIC_VIDEO, LIMIT)
                count++
            }
        } else {
            Timber.d("%s制限超過", count)
        }
    }

    private fun setUpRequestScheduler() {

        val handler = Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {
                // UIスレッド
                if (count > MAX_REQUEST_PER_MINUTE) {
                    Timber.d("%sだから危険", count)
                } else
                    Timber.d("%sだから安全", count)
                count = ZERO
                handler.postDelayed(this, DELAY_MINUTES.toLong())
            }
        }, DELAY_MINUTES.toLong())
    }

}
