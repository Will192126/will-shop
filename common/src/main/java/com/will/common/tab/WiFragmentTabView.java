package com.will.common.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 内聚Fragment的操作，并提供通用API。
 */
public class WiFragmentTabView extends FrameLayout {
    int mCurrentPosition;
    WiTabViewAdapter mAdapter;

    public WiFragmentTabView(@NonNull Context context) {
        super(context);
    }

    public WiFragmentTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WiFragmentTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public void setCurrentPosition(int position) {
        if (null == mAdapter) {
            throw new IllegalArgumentException("please call setAdapter first.");
        }
        if (position < 0 || position > mAdapter.getCount()) {
            return;
        }
        if (mCurrentPosition != position) {
            mCurrentPosition = position;
            mAdapter.instantiateItem(this, position);
        }
    }

    public WiTabViewAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(WiTabViewAdapter adapter) {
        if (mAdapter != null) {
            return;
        }
        this.mAdapter = adapter;
        mCurrentPosition = -1;
    }

    public Fragment getCurrentFragment() {
        if (null == mAdapter) {
            throw new IllegalArgumentException("please call setAdapter first.");
        }
        return mAdapter.getmCurFragment();
    }
}
