package com.example.nijimac103.itunestracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.nijimac103.itunestracker.service.repository.ArtistRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArtistRepository.getInstance().getArtistList("Alicia keys","musicVideo",30);

    }
}
