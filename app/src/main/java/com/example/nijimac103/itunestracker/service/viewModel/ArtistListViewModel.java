package com.example.nijimac103.itunestracker.service.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.nijimac103.itunestracker.service.model.ArtistList;
import com.example.nijimac103.itunestracker.service.repository.ArtistRepository;


public class ArtistListViewModel extends AndroidViewModel {

    private LiveData<ArtistList> artistListObservable;
    private ArtistRepository repo;
    private int LIMIT = 50;

    public ArtistListViewModel(Application application) {
        super(application);

        repo = ArtistRepository.getInstance();

        artistListObservable = repo.getArtistList("sample", "musicVideo",LIMIT);
    }

    //LiveDataのゲッター
    public LiveData<ArtistList> getArtistListObservable(){

        return artistListObservable;
    }

    public void reloadArtists(CharSequence text){
        Log.d("テキストおおおお",text.toString());
        artistListObservable = repo.getArtistList(text.toString(), "musicVideo",LIMIT);
    }
}
