package com.example.nijimac103.itunestracker.service.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.model.Artist;

public class ArtistListFragment {
    public static final String TAG = "ArticleListFragment";
    private ArtistListAdapter artistListAdapter;
    private FragmentArtistListBinding binding;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist_list);

        artistListAdapter = new ArtistListAdapter(artistClickCallback);

        binding.artistList.setAdapter(artistListAdapter);





    }
}
