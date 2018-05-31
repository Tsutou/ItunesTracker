package com.example.nijimac103.itunestracker.service.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.repository.ArtistRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            //プロジェクト一覧のFragment
            ArtistListFragment fragment = new ArtistListFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment, ArtistListFragment.TAG)
                    .commit();
        }

    }
}
