
package com.threedroid.hotcollections.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public abstract class AbsTabView extends LinearLayout implements OnClickListener {

    public static final int DIVIDER_NONE = 0;// 无分割线(0123)
    public static final int DIVIDER_START = 1;// 每个tab的左边使用分割线(|0|1|2|3)
    public static final int DIVIDER_END = 2;// 每个tab的右边使用分割线(0|1|2|3|)
    public static final int DIVIDER_BOTH = 3;// 每个tab的左右都有分割线(|0|1|2|3|)
    public static final int DIVIDER_MIDDLE = 4;// 每两个tab的中间都有分割线(0|1|2|3)
    public static final int DIVIDER_BORDER = 5;// 整个tab的两端有分割线(|0123|)

    private OnTabSelectListener mTabSelectListener;
    private int mCurrentIndex;
    protected Context mContext;
    private boolean mIsCreate;

    protected View[] mTabViews;

    public AbsTabView(Context context) {
        this(context, null, true);
    }

    public AbsTabView(Context context, AttributeSet attrs) {
        this(context, attrs, true);
    }

    public AbsTabView(Context context, boolean autoCreate) {
        this(context, null, autoCreate);
    }

    public AbsTabView(Context context, AttributeSet attrs, boolean autoCreate) {
        super(context, attrs);
        mIsCreate = autoCreate;
        setOrientation(HORIZONTAL);
        mContext = context;
        if (mIsCreate) {
            create();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * 创建tab，默认自动创建，不用手动调用，当需要在创建之间初始化参数时才需要手动调用
     */
    public final void create() {
        if (mTabViews != null)
            throw new RuntimeException("Tab has been created!");
        mTabViews = new View[getTabCount()];
        for (int i = 0; i < getTabCount(); i++) {
            // Before add
            if (i == 0
                    && (getTabDividerStyle() == DIVIDER_BOTH
                            || getTabDividerStyle() == DIVIDER_START || getTabDividerStyle() == DIVIDER_BORDER)) {
                addDivider();
            }
            // Adding
            LayoutParams para = new LayoutParams(0,
                    LayoutParams.WRAP_CONTENT, 1);
            View tabView = getTabView(i);
            mTabViews[i] = tabView;
            tabView.setTag(i);
            tabView.setOnClickListener(this);
            tabView.setFocusable(true);
            addView(tabView, para);
            if (i == mCurrentIndex) {
                tabView.setSelected(true);
            }
            // After added
            if (i == getTabCount() - 1) {
                // 最后一个tab
                if (getTabDividerStyle() == DIVIDER_BOTH || getTabDividerStyle() == DIVIDER_END
                        || getTabDividerStyle() == DIVIDER_BORDER) {
                    addDivider();
                }
            } else {
                if (getTabDividerStyle() == DIVIDER_BOTH || getTabDividerStyle() == DIVIDER_END
                        || getTabDividerStyle() == DIVIDER_START
                        || getTabDividerStyle() == DIVIDER_MIDDLE) {
                    addDivider();
                }
            }
        }
    }

    private void addDivider() {
        ImageView divider = new ImageView(mContext);
        divider.setScaleType(ScaleType.FIT_CENTER);
        divider.setImageResource(getTabDividerResource());
        addView(divider, new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onClick(View v) {
        int index = (Integer)v.getTag();
        if (index == mCurrentIndex) {
            if (mTabSelectListener != null) {
                mTabSelectListener.onTabReSelected(this, index);
            }
        } else {
            mTabViews[mCurrentIndex].setSelected(false);
            mCurrentIndex = index;
            mTabViews[mCurrentIndex].setSelected(true);
            if (mTabSelectListener != null) {
                mTabSelectListener.onTabSelected(this, index);
            }
        }
    }

    /**
     * 获得当前选中tab的index
     * 
     * @return
     */
    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public void setCurrentTab(int index) {
        if (index >= 0 && index < getTabCount()) {
            mTabViews[mCurrentIndex].setSelected(false);
            mCurrentIndex = index;
            mTabViews[mCurrentIndex].setSelected(true);
        }
    }

    /**
     * tab item的数量
     */
    public abstract int getTabCount();

    /**
     * 分割线样式
     *
     */
    public abstract int getTabDividerStyle();

    /**
     * 分割线样式
     * 
     * @return
     */
    public abstract int getTabDividerResource();

    /**
     * 自定义tab item的样式
     * 
     * @param index
     * @return
     */
    public abstract View getTabView(int index);

    public interface OnTabSelectListener {

        public void onTabSelected(View tabView, int index);

        public void onTabReSelected(View tabView, int index);
    }

    public void setOnTabSelectListener(OnTabSelectListener listener) {
        mTabSelectListener = listener;
    }

}
