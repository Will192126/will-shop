package com.will.common.tab;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.will.ui.tab.bottom.HiTabBottomInfo;

import java.util.List;

public class WiTabViewAdapter {
    private List<HiTabBottomInfo<?>> mInfoList;
    private Fragment mCurFragment;
    private FragmentManager mFragmentManager;

    public WiTabViewAdapter(FragmentManager fragmentManager, List<HiTabBottomInfo<?>> infoList) {
        this.mInfoList = infoList;
        this.mFragmentManager = fragmentManager;
    }

    public void instantiateItem(View container, int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mCurFragment != null) {
            transaction.hide(mCurFragment);
        }
        String name = container.getId() + ":" + position;
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            transaction.show(fragment);
        } else {
            fragment = getItem(position);
            if (null == fragment) {
                return;
            }

            if (!fragment.isAdded()) {
                transaction.add(container.getId(), fragment, name);
            }
        }

        mCurFragment = fragment;
        transaction.commitNowAllowingStateLoss();
    }

    public Fragment getmCurFragment() {
        return mCurFragment;
    }

    public Fragment getItem(int position) {
        try {
            return mInfoList.get(position).fragment.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCount() {
        return null == mInfoList ? 0 : mInfoList.size();
    }
}
