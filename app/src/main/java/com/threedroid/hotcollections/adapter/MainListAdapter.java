package com.threedroid.hotcollections.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by mr on 14-8-26.
 */
public class MainListAdapter extends BaseArrayAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(parent.getContext());
        tv.setHeight(100);
        tv.setText("position:" + position);
        return tv;
    }

    @Override
    public int getCount() {
        return 20;
    }
}
