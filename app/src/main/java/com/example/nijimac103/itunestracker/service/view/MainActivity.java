package com.example.nijimac103.itunestracker.service.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.model.Artist;
import com.example.nijimac103.itunestracker.service.util.FragmentUtils;
import com.example.nijimac103.itunestracker.service.view.Fragment.ArtistListFragment;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "URL";

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
