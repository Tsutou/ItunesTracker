package com.example.nijimac103.itunestracker.service.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.model.Artist;
import com.example.nijimac103.itunestracker.service.util.FragmentUtils;
import com.example.nijimac103.itunestracker.service.view.Fragment.ArtistListFragment;
import com.example.nijimac103.itunestracker.service.view.Fragment.ArtistWebViewFragment;

public class MainActivity extends AppCompatActivity {

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
    }

    //詳細画面への遷移
    public void show(Artist artist) {
        ArtistWebViewFragment fragment = new ArtistWebViewFragment();

        FragmentUtils.setArgsToFragment(fragment,"URL",artist.previewUrl);

        FragmentUtils.insertFragmentAddBackStack(
                R.id.fragment_container,
                getSupportFragmentManager(),
                fragment,
                ArtistWebViewFragment.TAG,
                "artist");
    }
}
