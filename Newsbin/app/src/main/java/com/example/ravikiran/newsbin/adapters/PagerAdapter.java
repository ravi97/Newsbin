package com.example.ravikiran.newsbin.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.ravikiran.newsbin.fragments.FetchnewsFragment;
import com.example.ravikiran.newsbin.fragments.NotifierFragment;
import com.example.ravikiran.newsbin.fragments.OfflinenewsFragment;

/**
 * Created by Ravikiran on 7/17/2016.
 */
public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FetchnewsFragment.getInstance();

            case 1:
                return NotifierFragment.getInstance();

            case 2:
                return OfflinenewsFragment.getInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Read news";
            case 1:

                return "Read later";
            case 2:
                return "offline articles";
            default:
                return null;
        }
    }
}
