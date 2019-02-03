package jp.co.geisha.itunestracker.service.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import jp.co.geisha.itunestracker.service.model.ArtistList;
import jp.co.geisha.itunestracker.service.repository.ArtistRepository;
import timber.log.Timber;

import static jp.co.geisha.itunestracker.service.util.CalcUtils.getRand;


public class ArtistListViewModel extends AndroidViewModel {

    private static final int DELAY_MINUTES = 60000;
    private static final int MAX_REQUEST_PER_MINUTE = 20;
    private int LIMIT = 100;
    private static final int ZERO = 0;
    private static final String MUSIC_VIDEO = "musicVideo";
    private LiveData<ArtistList> artistListObservable;
    private ArtistRepository repo;
    private int count;

    //TODO:決め打ちでなく、rankingからとってきてTrendとしてもいいかも
    private String[] defaultArtists =
            {
                    "alicia keys",
                    "lady gaga",
                    "michael jackson",
                    "beatles",
                    "stevie wonder",
                    "eric clapton",
                    "beyonce",
                    "james brown",
                    "sting",
                    "oasis",
                    "2pac",
                    "Nas",
                    "bob marley",
                    "billy joel",
                    "elton john",
                    "bruno mars",
                    "joe",
                    "justin timberlake",
                    "TLC",
                    "SWV",
                    "blackstreet",
                    "jackson5",
                    "ed sheeran",
                    "boyz 2 men",
                    "india arie",
                    "talor swift",
                    "norah jones",
                    "frank sinatra",
                    "marvin gaye",
                    "mariah carey",
                    "diana ross",
                    "jamiroquai",
                    "john lenon",
                    "tuxedo"
            };

    public ArtistListViewModel(Application application) {
        super(application);

        setUpRequestScheduler();

        repo = ArtistRepository.getInstance();

        artistListObservable = repo.getArtistList(defaultArtists[getRand(defaultArtists.length)], MUSIC_VIDEO, LIMIT);
    }

    //LiveDataのゲッター
    public LiveData<ArtistList> getArtistListObservable() {

        return artistListObservable;
    }

    public void reloadArtists(CharSequence text) {
        if (count <= MAX_REQUEST_PER_MINUTE) {
            if (TextUtils.isEmpty(text)) {
                artistListObservable = repo.getArtistList(defaultArtists[getRand(defaultArtists.length)], MUSIC_VIDEO, LIMIT);
            } else {
                artistListObservable = repo.getArtistList(text.toString(), "musicVideo", LIMIT);
                count++;
            }
        } else {
            Timber.d("%s制限超過", count);
        }
    }

    private void setUpRequestScheduler() {

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // UIスレッド
                if (count > MAX_REQUEST_PER_MINUTE) {
                    Timber.d("%sだから危険", count);
                } else Timber.d("%sだから安全", count);
                count = ZERO;
                handler.postDelayed(this, DELAY_MINUTES);
            }
        }, DELAY_MINUTES);
    }

}
