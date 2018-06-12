package com.example.nijimac103.itunestracker.service.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.model.Artist;
import com.example.nijimac103.itunestracker.service.model.ArtistList;
import com.example.nijimac103.itunestracker.service.util.FragmentUtils;
import com.example.nijimac103.itunestracker.service.util.LocationUtil;
import com.example.nijimac103.itunestracker.service.view.Fragment.ArtistListFragment;
import com.example.nijimac103.itunestracker.service.view.Fragment.ArtistVideoViewFragment;
import com.example.nijimac103.itunestracker.service.view.adapter.ArtistViewPagerAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "URL";
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            seUpViewPager();
        }

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        stopShimmerText();
    }

    //ViewPagerの設定
    public void seUpViewPager() {
        ArtistViewPagerAdapter adapter = new ArtistViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
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

    public void startShimmerText(){
        this.mShimmerViewContainer.startShimmer();
    }

    public void stopShimmerText(){
        this.mShimmerViewContainer.stopShimmer();
    }
}
