package jp.co.geisha.diggin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.os.Handler
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import jp.co.geisha.diggin.*
import jp.co.geisha.diggin.repository.ItunesRepository
import timber.log.Timber
import jp.co.geisha.diggin.ConstArrays.DEFAULT_ARTIST_LIST
import jp.co.geisha.diggin.api.entity.ItunesData
import jp.co.geisha.diggin.api.entity.YouTubeResponse
import jp.co.geisha.diggin.repository.YouTubeRepository
import jp.co.geisha.diggin.util.CalcUtils.getRand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MusicVideoListViewModel(application: Application) : AndroidViewModel(application) {

    sealed class Status {
        object Success : Status()
        object Loading : Status()
        data class Error(val message: String) : Status()
    }

    val youTubeListLiveData: MutableLiveData<YouTubeResponse> = MutableLiveData()
    val itunesListLiveData: MutableLiveData<ItunesData.Result> = MutableLiveData()
    val state: MutableLiveData<Status> = MutableLiveData()

    private val itunesRepository: ItunesRepository = ItunesRepository.getInstance()
    private val youtubeRepository: YouTubeRepository = YouTubeRepository.getInstance()

    private val handler = Handler()
    private var count: Int = 0

    fun loadArtists(textInput: CharSequence) {
        viewModelScope.launch {
            state.value = Status.Loading
            if (count <= MAX_REQUEST_PER_MINUTE) {
                val searchText = if (TextUtils.isEmpty(textInput)) {
                    DEFAULT_ARTIST_LIST[getRand(DEFAULT_ARTIST_LIST.size)]
                } else {
                    textInput
                }
                getVideoListWithQuery(searchText.toString())
            } else {
                state.postValue(Status.Error("Possible limit exceeded , Now count == $count"))
            }
        }
    }

    private suspend fun getVideoListWithQuery(query: String) = withContext(Dispatchers.Default) {

        val youTubeResult = youtubeRepository.getMusicVideoList(query)
        val itunesResult = itunesRepository.getMusicVideoList(query, MUSIC_VIDEO, LIMIT)

        if (youTubeResult.isSuccessful && itunesResult.isSuccessful) {
            state.postValue(Status.Success)
            itunesListLiveData.postValue(itunesResult.body())
            youTubeListLiveData.postValue(youTubeResult.body())
            count++
        } else {
            state.postValue(Status.Error("api call failed"))
        }
    }

    private val scheduler = object : Runnable {
        override fun run() {
            if (count > MAX_REQUEST_PER_MINUTE) {
                Timber.d("%s danger count", count)
            } else
                Timber.d("%s count safely", count)
            count = ZERO
            handler.postDelayed(this, DELAY_MINUTES.toLong())
        }
    }

    fun setUpRequestScheduler() {
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
