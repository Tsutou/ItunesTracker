package com.example.nijimac103.itunestracker.service.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.databinding.FragmentArtistListBinding;
import com.example.nijimac103.itunestracker.service.callback.ArtistClickCallback;
import com.example.nijimac103.itunestracker.service.model.Artist;
import com.example.nijimac103.itunestracker.service.view.adapter.ArtistAdapter;
import com.example.nijimac103.itunestracker.service.viewModel.ArtistListViewModel;

import java.util.List;

public class ArtistListFragment extends Fragment {
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
    }

    private void observeViewModel(ArtistListViewModel viewModel) {

        viewModel.getArtistListObservable().observe(this, new Observer<List<Artist>>() {
            @Override
            public void onChanged(@Nullable List<Artist> artists) {
                if (artists != null) {
                    binding.setIsLoading(false);
                    artistListAdapter.setArtistList(artists);
                }
            }
        });
    }

    private final ArtistClickCallback artistClickCallback = new ArtistClickCallback() {
        @Override
        public void onClick(Artist artist) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//                ((MainActivity) getActivity()).show(artist);
            }
        }
    };


}
