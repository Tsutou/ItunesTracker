package jp.co.geisha.diggin.view.fragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import jp.co.geisha.diggin.R
import jp.co.geisha.diggin.databinding.FragmentArtistListBinding
import jp.co.geisha.diggin.callback.MusicVideoClickCallback
import jp.co.geisha.diggin.api.entity.ItunesData
import jp.co.geisha.diggin.api.entity.YouTubeResponse
import jp.co.geisha.diggin.view.activity.MainActivity
import jp.co.geisha.diggin.view.adapter.MusicVideoListAdapter
import jp.co.geisha.diggin.viewModel.MusicVideoListViewModel
import timber.log.Timber
import java.util.*

class MusicVideoListFragment : Fragment() {

    private lateinit var binding: FragmentArtistListBinding
    private var isTyping = false

    private val viewModel by lazy {
        ViewModelProvider(this).get(MusicVideoListViewModel::class.java)
    }

    private val artistClickCallback
        get() = object : MusicVideoClickCallback {
            override fun onClick(itunesData: ItunesData) {
                return if (!lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    return
                } else {
                    if (activity != null && activity is MainActivity) {
                        viewModel.insert(null, itunesData)
                        (activity as MainActivity).show(itunesData)
                    } else {
                        return
                    }
                }
            }

            override fun onClick(youtubeData: YouTubeResponse.Data) {
                return if (!lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    return
                } else {
                    if (activity != null && activity is MainActivity) {
                        viewModel.insert(youtubeData, null)
                        (activity as MainActivity).show(youtubeData)
                    } else {
                        return
                    }
                }
            }
        }

    private var musicListListAdapter: MusicVideoListAdapter = MusicVideoListAdapter(artistClickCallback)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist_list, container, false)

        binding.artistList.apply {
            setHasFixedSize(true)
            layoutManager = object : StaggeredGridLayoutManager(3, VERTICAL) {}
            adapter = musicListListAdapter
        }

        viewModel.apply {
            state.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    is MusicVideoListViewModel.Status.Success -> binding.swipeContainer.isRefreshing = false
                    is MusicVideoListViewModel.Status.Loading -> binding.swipeContainer.isRefreshing = true
                    is MusicVideoListViewModel.Status.Error -> {
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_SHORT)
                    }
                }
            })
            youTubeListLiveData.observe(viewLifecycleOwner, Observer { videos ->
                if (videos != null) {
                    musicListListAdapter.setYoutubeDataList(videos.items)
                }
            })
            itunesListLiveData.observe(viewLifecycleOwner, Observer { videos ->
                if (videos != null) {
                    musicListListAdapter.setItunesDataList(videos.data)
                }
            })

            setUpRequestScheduler()
            loadArtists("")
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.swipeContainer.apply {
            setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorWhite)
            setOnRefreshListener {
                viewModel.loadArtists(binding.searchArtist.text.toString())
            }
        }

        binding.searchArtist.addTextChangedListener(getArtistWatcher())
    }

    private fun getArtistWatcher(): TextWatcher {

        return object : TextWatcher {

            private var timer = Timer()
            private val DELAY_MILLIS: Long = 500 // milliseconds
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!isTyping) {
                    Timber.d("started typing")
                    isTyping = true
                }
                timer.cancel()
                timer = Timer()
                timer.schedule(
                        object : TimerTask() {
                            override fun run() {
                                isTyping = false
                                //タイピングが500ms停止したらArtistのReloadを実行
                                Timber.d("stopped typing")
                                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                                    viewModel.loadArtists(s.toString())
                                }
                            }
                        },
                        DELAY_MILLIS
                )
            }

            override fun afterTextChanged(s: Editable) {

            }
        }
    }

}
