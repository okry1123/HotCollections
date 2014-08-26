package com.threedroid.hotcollections.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.threedroid.hotcollections.R;
import com.threedroid.hotcollections.ui.MainTab;

/**
 * Created by mr on 14-8-26.
 */
public class MainFragment extends Fragment {

    MainTab mTab;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
}
