package com.example.nijimac103.itunestracker.service.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.Handler;
import android.util.Log;

import com.example.nijimac103.itunestracker.service.model.ArtistList;
import com.example.nijimac103.itunestracker.service.repository.ArtistRepository;

import static com.example.nijimac103.itunestracker.service.util.CalcUtils.getRand;


public class ArtistListViewModel extends AndroidViewModel {

    public static final int DELAY_MINUTES = 60000;
    public static final int MAX_REQUEST_PER_MINUTE = 20;
    private int LIMIT = 30;
    public static final int ZERO = 0;
    public static final String MUSIC_VIDEO = "musicVideo";
    private LiveData<ArtistList> artistListObservable;
    private ArtistRepository repo;
    private int count;

    //TODO:決め打ちでなく、rankingからとってきてTrendとしてもいいかも
    private String[] defaultArtists = {"aliciakeys","ladygaga","caroleking","beatles","jamestaylor","ericclapton","beyonce","jamesbrown"};

    public ArtistListViewModel(Application application) {
        super(application);

        setUpRequestScheduler();

        repo = ArtistRepository.getInstance();

        artistListObservable = repo.getArtistList(defaultArtists[getRand(7)], MUSIC_VIDEO, LIMIT);
    }

    //LiveDataのゲッター
    public LiveData<ArtistList> getArtistListObservable() {

        return artistListObservable;
    }

    public void reloadArtists(CharSequence text) {
        if (count <= MAX_REQUEST_PER_MINUTE) {
            artistListObservable = repo.getArtistList(text.toString(), "musicVideo", LIMIT);
            count++;
        } else {
            Log.d("監視", count + "制限超過");
        }
    }

    private void setUpRequestScheduler() {

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // UIスレッド
                if (count > MAX_REQUEST_PER_MINUTE) {
                    Log.d("監視", count + "だから危険");
                } else {
                    Log.d("監視", count + "だから安全");
                }
                count = ZERO;
                handler.postDelayed(this, DELAY_MINUTES);
            }
        }, DELAY_MINUTES);
    }

}
