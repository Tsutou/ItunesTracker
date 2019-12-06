package jp.co.geisha.itunestracker.service.view.fragment

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.co.geisha.itunestracker.R
import jp.co.geisha.itunestracker.databinding.FragmentArtistListBinding
import jp.co.geisha.itunestracker.service.callback.ArtistClickCallback
import jp.co.geisha.itunestracker.service.model.Artist
import jp.co.geisha.itunestracker.service.view.activity.MainActivity
import jp.co.geisha.itunestracker.service.view.adapter.ArtistAdapter
import jp.co.geisha.itunestracker.service.viewModel.ArtistListViewModel
import timber.log.Timber
import java.util.*

class ArtistListFragment : Fragment() {

    private var artistListAdapter: ArtistAdapter? = null
    private lateinit var binding: FragmentArtistListBinding
    private var isTyping = false

    private val artistClickCallback
        get() = object : ArtistClickCallback {
            override fun onClick(artist: Artist) {
                return if (!lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    return
                } else {
                    if (activity != null)
                        (activity as MainActivity).show(artist) else return
                }
            }
        }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(Objects.requireNonNull(inflater), R.layout.fragment_artist_list, container, false)

        artistListAdapter = ArtistAdapter(artistClickCallback)

        binding.artistList.setHasFixedSize(true)
        binding.artistList.adapter = artistListAdapter

        binding.isLoading = true

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(ArtistListViewModel::class.java)

        observeViewModel(viewModel, false)

        binding.swipeContainer.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorWhite)
        binding.swipeContainer.isRefreshing = true

        //TextChangeListnerセット
        binding.searchArtist.addTextChangedListener(getArtistWatcher(viewModel))

        // スワイプダウンの処理
        binding.swipeContainer.setOnRefreshListener {
            binding.swipeContainer.isRefreshing = true
            viewModel.reloadArtists(binding.searchArtist.text)
            observeViewModel(viewModel, true)
        }
    }

    private fun observeViewModel(viewModel: ArtistListViewModel, isReload: Boolean?) {

        viewModel.artistListObservable?.observe(this, Observer { artists ->
            if (artists != null) {
                binding.isLoading = false
                artistListAdapter?.setArtistList(artists.results, isReload) ?: return@Observer
            }
            binding.swipeContainer.isRefreshing = false
        }) ?: return
    }

    private fun getArtistWatcher(viewModel: ArtistListViewModel): TextWatcher {

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

                                    viewModel.reloadArtists(s.toString())
                                    observeViewModel(viewModel, true)

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
