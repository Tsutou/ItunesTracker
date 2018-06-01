package com.example.nijimac103.itunestracker.service.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.repository.ArtistRepository;
import com.example.nijimac103.itunestracker.service.util.FragmentUtils;

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
}
