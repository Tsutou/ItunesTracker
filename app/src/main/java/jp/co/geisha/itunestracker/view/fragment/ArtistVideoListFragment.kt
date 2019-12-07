package jp.co.geisha.itunestracker.view.fragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import jp.co.geisha.itunestracker.R
import jp.co.geisha.itunestracker.databinding.FragmentArtistListBinding
import jp.co.geisha.itunestracker.callback.ArtistClickCallback
import jp.co.geisha.itunestracker.api.entity.Artist
import jp.co.geisha.itunestracker.view.activity.MainActivity
import jp.co.geisha.itunestracker.view.adapter.ArtistVideoAdapter
import jp.co.geisha.itunestracker.viewModel.ArtistListViewModel
import timber.log.Timber
import java.util.*

class ArtistVideoListFragment : Fragment() {

    private lateinit var binding: FragmentArtistListBinding
    private var isTyping = false

    private val artistClickCallback
        get() = object : ArtistClickCallback {
            override fun onClick(artist: Artist) {
                return if (!lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    return
                } else {
                    if (activity != null && activity is MainActivity)
                        (activity as MainActivity).show(artist) else return
                }
            }
        }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ArtistListViewModel::class.java)
    }

    private var artistListAdapter: ArtistVideoAdapter = ArtistVideoAdapter(artistClickCallback)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist_list, container, false)

        binding.artistList.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = artistListAdapter
        }

        viewModel.apply {
            state.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    is ArtistListViewModel.Status.Success -> binding.swipeContainer.isRefreshing = false
                    is ArtistListViewModel.Status.Loading -> binding.swipeContainer.isRefreshing = true
                    is ArtistListViewModel.Status.Error -> {
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_SHORT)
                    }
                }
            })
            videoListLiveData.observe(viewLifecycleOwner, Observer { videos ->
                if (videos != null) {
                    artistListAdapter.setArtistList(videos.data)
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
            private val DELAY: Long = 500 // milliseconds
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
                        DELAY
                )
            }

            override fun afterTextChanged(s: Editable) {

            }
        }
    }

}
