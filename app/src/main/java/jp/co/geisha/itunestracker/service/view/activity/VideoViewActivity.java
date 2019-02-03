package jp.co.geisha.itunestracker.service.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jp.co.geisha.itunestracker.R;
import jp.co.geisha.itunestracker.service.util.FragmentUtils;
import jp.co.geisha.itunestracker.service.view.fragment.ArtistVideoViewFragment;

import static jp.co.geisha.itunestracker.service.view.activity.MainActivity.URL;

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
