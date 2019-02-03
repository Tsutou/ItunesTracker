package jp.co.geisha.itunestracker.service.view.fragment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.co.geisha.itunestracker.R;
import jp.co.geisha.itunestracker.databinding.FragmentArtistListBinding;
import jp.co.geisha.itunestracker.service.callback.ArtistClickCallback;
import jp.co.geisha.itunestracker.service.model.Artist;
import jp.co.geisha.itunestracker.service.model.ArtistList;
import jp.co.geisha.itunestracker.service.view.activity.MainActivity;
import jp.co.geisha.itunestracker.service.view.adapter.ArtistAdapter;
import jp.co.geisha.itunestracker.service.viewModel.ArtistListViewModel;
import timber.log.Timber;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ArtistListFragment extends Fragment {
    public static final String TAG = "ArtistListFragment";
    private ArtistAdapter artistListAdapter;
    private FragmentArtistListBinding binding;
    private boolean isTyping = false;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(Objects.requireNonNull(inflater), R.layout.fragment_artist_list, container, false);

        artistListAdapter = new ArtistAdapter(artistClickCallback);

        binding.artistList.setHasFixedSize(true);
        binding.artistList.setAdapter(artistListAdapter);

        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ArtistListViewModel viewModel =
                ViewModelProviders.of(this).get(ArtistListViewModel.class);

        observeViewModel(viewModel, false);


        binding.swipeContainer.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorWhite);
        binding.swipeContainer.setRefreshing(true);

        //TextChangeListnerセット
        binding.searchArtist.addTextChangedListener(getArtistWatcher(viewModel));

        // スワイプダウンの処理
        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swipeContainer.setRefreshing(true);
                viewModel.reloadArtists(binding.searchArtist.getText());
                observeViewModel(viewModel, true);
            }
        });
    }

    private void observeViewModel(ArtistListViewModel viewModel, final Boolean isReload) {

        viewModel.getArtistListObservable().observe(this, new Observer<ArtistList>() {
            @Override
            public void onChanged(@Nullable ArtistList artists) {
                if (artists != null) {
                    binding.setIsLoading(false);
                    artistListAdapter.setArtistList(artists.results, isReload);
                }
                binding.swipeContainer.setRefreshing(false);
            }
        });
    }

    private final ArtistClickCallback artistClickCallback = new ArtistClickCallback() {
        @Override
        public void onClick(Artist artist) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) Objects.requireNonNull(getActivity())).show(artist);
            }
        }
    };

    public TextWatcher getArtistWatcher(final ArtistListViewModel viewModel) {

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            private Timer timer = new Timer();
            private final long DELAY = 500; // milliseconds

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, final int count) {


                if (!isTyping) {
                    Timber.d("started typing");

                    isTyping = true;
                }
                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                isTyping = false;
                                //タイピングが500ms停止したらArtistのReloadを実行
                                Timber.d("stopped typing");

                                if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {

                                    viewModel.reloadArtists(s.toString());
                                    observeViewModel(viewModel, true);

                                }
                            }
                        },
                        DELAY
                );


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

}
