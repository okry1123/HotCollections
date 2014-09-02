package com.threedroid.hotcollections.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.threedroid.hotcollections.R;

/**
 * Created by mr on 14-8-26.
 */
public class TypeMainTab extends AbsTabView {

    private static String[] mTabs = {"排行", "最新"};

    public TypeMainTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getTabCount() {
        return mTabs.length;
    }

    @Override
    public int getTabDividerStyle() {
        return DIVIDER_NONE;
    }

    @Override
    public int getTabDividerResource() {
        return -1;
    }

    @Override
    public View getTabView(int index) {
        View tab = View.inflate(getContext(), R.layout.view_main_tab, null);
        TextView text = (TextView) tab.findViewById(R.id.tab_text);
        text.setText(mTabs[index]);
        return tab;
    }
}
