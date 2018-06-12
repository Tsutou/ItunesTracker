package com.example.nijimac103.itunestracker.service.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nijimac103.itunestracker.service.view.Fragment.ArtistListFragment;

/*
 * ViewPager用のAdapter
 */

public class ArtistViewPagerAdapter extends FragmentPagerAdapter {

    private CharSequence[] tabTitles = {"search", "feed"};

    public ArtistViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ArtistListFragment();
            case 1:
                return new ArtistListFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
