package com.threedroid.hotcollections.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.threedroid.hotcollections.fragment.NewListFragment;
import com.threedroid.hotcollections.fragment.RankingListFragment;
import com.threedroid.hotcollections.fragment.TypeGridFragment;
import com.threedroid.hotcollections.fragment.TypeNewListFragment;
import com.threedroid.hotcollections.fragment.TypeRankingListFragment;

/**
 * Created by mr on 14-8-26.
 */
public class TypeMainPagerAdapter extends FragmentPagerAdapter {

    private int mGameType;

    public TypeMainPagerAdapter(FragmentManager fm, int gametype) {
        super(fm);

        mGameType = gametype;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case 0:
                return TypeNewListFragment.launch(mGameType);
            case 1:
                return TypeRankingListFragment.launch(mGameType);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
