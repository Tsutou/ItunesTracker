package jp.co.geisha.itunestracker.service.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import jp.co.geisha.itunestracker.R;
import jp.co.geisha.itunestracker.service.model.Artist;
import jp.co.geisha.itunestracker.service.util.FragmentUtils;
import jp.co.geisha.itunestracker.service.view.Fragment.ArtistListFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "URL";
    private AdView mAdViewHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            ArtistListFragment fragment = new ArtistListFragment();

            FragmentUtils.insertFragmentToActivity(
                    R.id.fragment_container,
                    getSupportFragmentManager(),
                    fragment,
                    ArtistListFragment.TAG);
        }


        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");

        mAdViewHeader = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewHeader.loadAd(adRequest);

    }

    //VideoView画面への遷移
    public void show(Artist artist) {
        if (!isEmpty(artist.previewUrl)) {
            Intent i = new Intent(getApplicationContext(), VideoViewActivity.class);
            i.putExtra(URL, artist.previewUrl);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "This Video has not Preview", Toast.LENGTH_SHORT).show();
        }
    }

}
