package jp.co.geisha.itunestracker.service.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.os.Handler
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import jp.co.geisha.itunestracker.service.*
import jp.co.geisha.itunestracker.service.model.ArtistList
import jp.co.geisha.itunestracker.service.repository.ArtistRepository
import timber.log.Timber
import jp.co.geisha.itunestracker.service.ConstArrays.DEFAULT_ARTIST_LIST
import jp.co.geisha.itunestracker.service.util.CalcUtils.getRand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistListViewModel(application: Application) : AndroidViewModel(application) {

    sealed class Status {
        object Success : Status()
        object Loading : Status()
        data class Error(val message: String) : Status()
    }

    val videoListLiveData: MutableLiveData<ArtistList> = MutableLiveData()
    val state: MutableLiveData<Status> = MutableLiveData()
    private val repo: ArtistRepository = ArtistRepository.instance
    private val handler = Handler()
    private var count: Int = 0

    init {
        setUpRequestScheduler()
    }

    fun loadArtists(query: CharSequence) {
        viewModelScope.launch {
            state.value = Status.Loading
            if (count <= MAX_REQUEST_PER_MINUTE) {
                if (TextUtils.isEmpty(query)) {
                    getVideoListWithQuery(DEFAULT_ARTIST_LIST[getRand(DEFAULT_ARTIST_LIST.size)])
                } else {
                    getVideoListWithQuery(query.toString())
                }
            } else {
                state.postValue(Status.Error("loadArtists :api call failed Possible limit exceeded , Now count == $count"))
            }
        }
    }

    private suspend fun getVideoListWithQuery(query: String) = withContext(Dispatchers.Default) {
        val result = repo.getMusicVideoList(query, MUSIC_VIDEO, LIMIT)
        if (result.isSuccessful) {
            state.postValue(Status.Success)
            videoListLiveData.postValue(result.body())
            count++
        } else {
            state.postValue(Status.Error("getVideoListWithQuery : api call failed"))
        }
    }

    val scheduler = object : Runnable {
        override fun run() {
            if (count > MAX_REQUEST_PER_MINUTE) {
                Timber.d("%s danger count", count)
            } else
                Timber.d("%s count safely", count)
            count = ZERO
            handler.postDelayed(this, DELAY_MINUTES.toLong())
        }
    }

    private fun setUpRequestScheduler() {
        viewModelScope.launch {
            handler.postDelayed(scheduler, DELAY_MINUTES.toLong())
        }
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks {
            scheduler
        }
    }
}
