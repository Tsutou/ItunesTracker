package jp.co.geisha.itunestracker.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.os.Handler
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import jp.co.geisha.itunestracker.*
import jp.co.geisha.itunestracker.repository.ArtistRepository
import timber.log.Timber
import jp.co.geisha.itunestracker.ConstArrays.DEFAULT_ARTIST_LIST
import jp.co.geisha.itunestracker.api.entity.Artist
import jp.co.geisha.itunestracker.util.CalcUtils.getRand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistListViewModel(application: Application) : AndroidViewModel(application) {

    sealed class Status {
        object Success : Status()
        object Loading : Status()
        data class Error(val message: String) : Status()
    }

    val videoListLiveData: MutableLiveData<Artist.Result> = MutableLiveData()
    val state: MutableLiveData<Status> = MutableLiveData()

    private val repository: ArtistRepository = ArtistRepository.getInstance()

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
        val result = repository.getMusicVideoList( query, MUSIC_VIDEO, LIMIT)
        if (result.isSuccessful) {
            state.postValue(Status.Success)
            videoListLiveData.postValue(result.body())
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
