package com.example.nijimac103.itunestracker.service.view.Fragment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.databinding.FragmentArtistListBinding;
import com.example.nijimac103.itunestracker.service.callback.ArtistClickCallback;
import com.example.nijimac103.itunestracker.service.model.Artist;
import com.example.nijimac103.itunestracker.service.model.ArtistList;
import com.example.nijimac103.itunestracker.service.view.MainActivity;
import com.example.nijimac103.itunestracker.service.view.adapter.ArtistAdapter;
import com.example.nijimac103.itunestracker.service.viewModel.ArtistListViewModel;


public class ArtistListFragment extends Fragment{
    public static final String TAG = "ArtistListFragment";
    private ArtistAdapter artistListAdapter;
    private FragmentArtistListBinding binding;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist_list, container, false);

        artistListAdapter = new ArtistAdapter(artistClickCallback);

        binding.artistList.setAdapter(artistListAdapter);

        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ArtistListViewModel viewModel =
                ViewModelProviders.of(this).get(ArtistListViewModel.class);

        observeViewModel(viewModel);

        binding.searchArtist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.reloadArtists(s.toString());
                observeViewModel(viewModel);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void observeViewModel(ArtistListViewModel viewModel) {

        viewModel.getArtistListObservable().observe(this, new Observer<ArtistList>() {
            @Override
            public void onChanged(@Nullable ArtistList artists) {
                if (artists != null) {
                    binding.setIsLoading(false);
                    artistListAdapter.setArtistList(artists.results);
                }
            }
        });
    }

    private final ArtistClickCallback artistClickCallback = new ArtistClickCallback() {
        @Override
        public void onClick(Artist artist) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(artist);
            }
        }
    };
}
