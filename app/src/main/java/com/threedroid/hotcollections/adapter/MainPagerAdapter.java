package com.threedroid.hotcollections.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import com.threedroid.hotcollections.fragment.InfoListFragment;

/**
 * Created by mr on 14-8-26.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case 0:
                ListFragment f = new ListFragment();
                f.setListAdapter(new MainListAdapter());
                return f;
            case 1:
                return new InfoListFragment();
            case 2:
                return new InfoListFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
