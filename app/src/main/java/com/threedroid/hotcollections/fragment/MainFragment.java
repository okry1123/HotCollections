package com.threedroid.hotcollections.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.threedroid.hotcollections.R;
import com.threedroid.hotcollections.adapter.MainPagerAdapter;
import com.threedroid.hotcollections.ui.AbsTabView;
import com.threedroid.hotcollections.ui.MainTab;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr on 14-8-26.
 */
public class MainFragment extends Fragment {

    @InjectView(R.id.main_tab)
    MainTab mTab;
    @InjectView(R.id.main_pager)
    ViewPager mPager;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTab.setOnTabSelectListener(new AbsTabView.OnTabSelectListener() {
            @Override
            public void onTabSelected(View tabView, int index) {
                mPager.setCurrentItem(index);
            }

            @Override
            public void onTabReSelected(View tabView, int index) {

            }
        });
        mPager.setAdapter(new MainPagerAdapter(getFragmentManager()));
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                mTab.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
