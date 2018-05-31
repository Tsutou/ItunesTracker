package com.example.nijimac103.itunestracker.service.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.nijimac103.itunestracker.service.model.Artist;
import com.example.nijimac103.itunestracker.service.repository.ArtistRepository;

import java.util.List;

public class ArtistListViewModel extends AndroidViewModel {

    private final LiveData<List<Artist>> artistListObservable;
    private int LIMIT = 30;

    public ArtistListViewModel(Application application) {
        super(application);

        artistListObservable = ArtistRepository.getInstance().getArtistList("AliciaKeys", "MusicVideo", LIMIT);
    }

    public LiveData<List<Artist>> getArtistListObservable(){
        return artistListObservable;
    }
}
