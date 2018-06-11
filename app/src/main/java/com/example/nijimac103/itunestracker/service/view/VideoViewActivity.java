package com.example.nijimac103.itunestracker.service.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.util.FragmentUtils;
import com.example.nijimac103.itunestracker.service.view.Fragment.ArtistVideoViewFragment;

import static com.example.nijimac103.itunestracker.service.view.MainActivity.URL;

public class VideoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        ArtistVideoViewFragment fragment = new ArtistVideoViewFragment();

        Intent i = getIntent();
        String artistPreviewUrl = i.getStringExtra(URL);

        addVideoViewFragment(fragment, artistPreviewUrl);

    }

    private void addVideoViewFragment(ArtistVideoViewFragment fragment, String artistPreviewUrl) {

        FragmentUtils.setArgsToFragment(fragment, URL,artistPreviewUrl);

        FragmentUtils.insertFragmentToActivity(R.id.fragment_container,getSupportFragmentManager(),fragment,ArtistVideoViewFragment.TAG);
    }

}
