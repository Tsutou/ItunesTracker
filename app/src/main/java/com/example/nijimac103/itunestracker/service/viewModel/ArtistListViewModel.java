package com.example.nijimac103.itunestracker.service.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.Handler;
import android.util.Log;

import com.example.nijimac103.itunestracker.service.model.ArtistList;
import com.example.nijimac103.itunestracker.service.repository.ArtistRepository;


public class ArtistListViewModel extends AndroidViewModel {

    private LiveData<ArtistList> artistListObservable;
    private ArtistRepository repo;
    private int LIMIT = 50;
    private int count;

    public ArtistListViewModel(Application application) {
        super(application);

        setUpRequestScheduler();

        repo = ArtistRepository.getInstance();

        artistListObservable = repo.getArtistList("sample", "musicVideo",LIMIT);
    }

    //LiveDataのゲッター
    public LiveData<ArtistList> getArtistListObservable(){

        return artistListObservable;
    }

    public void reloadArtists(CharSequence text){
        if (count <= 20) {
            artistListObservable = repo.getArtistList(text.toString(), "musicVideo", LIMIT);
            count += 1;
        } else {
            Log.d("監視",count + "制限超過");
        }
    }

    private void setUpRequestScheduler() {

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // UIスレッド
                if (count > 18) {
                    Log.d("監視", count + "だから危険");
                } else {
                    Log.d("監視", count + "だから安全");
                }
                count = 0;
                handler.postDelayed(this,60000);
            }
        }, 60000);
    }
}
