package com.will.willshop.logic;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

import com.will.common.tab.WiFragmentTabView;
import com.will.common.tab.WiTabViewAdapter;
import com.will.ui.tab.bottom.HiTabBottomInfo;
import com.will.ui.tab.bottom.WiTabBottomLayout;
import com.will.ui.tab.common.IHiTabLayout;
import com.will.willshop.R;
import com.will.willshop.fragment.CategoryFragment;
import com.will.willshop.fragment.FavoriteFragment;
import com.will.willshop.fragment.HomeFragment;
import com.will.willshop.fragment.ProfileFragment;
import com.will.willshop.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivityLogic {
    private WiFragmentTabView mFragmentTabView;
    private WiTabBottomLayout mTabBottomLayout;
    private List<HiTabBottomInfo<?>> mInfoList;
    private ActivityProvider mActivityProvider;
    private final static String SAVED_CURRENT_ID = "SAVED_CURRENT_ID";
    private int mCurrentItemIndex;

    public MainActivityLogic(ActivityProvider provider, Bundle savedInstanceState) {
        //fix 不保留活动导致的Fragment重叠问题
        if (savedInstanceState != null) {
            mCurrentItemIndex = savedInstanceState.getInt(SAVED_CURRENT_ID);
        }

        mActivityProvider = provider;
        initTabBottom();
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SAVED_CURRENT_ID, mCurrentItemIndex);
    }

    public WiFragmentTabView getFragmentTabView() {
        return mFragmentTabView;
    }

    public WiTabBottomLayout getTabBottomLayout() {
        return mTabBottomLayout;
    }

    public List<HiTabBottomInfo<?>> getInfoList() {
        return mInfoList;
    }

    private void initTabBottom() {
        mTabBottomLayout = mActivityProvider.findViewById(R.id.tab_bottom_layout);
        mTabBottomLayout.setAlpha(0.85f);
        mInfoList = new ArrayList<>();
        int defaultColor = mActivityProvider.getResources().getColor(R.color.tabBottomDefaultColor);
        int tintColor = mActivityProvider.getResources().getColor(R.color.tabBottomTintColor);
        HiTabBottomInfo infoHome = new HiTabBottomInfo<Integer>(
                "首页",
                "fonts/iconfont.ttf",
                mActivityProvider.getString(R.string.if_home),
                null,
                defaultColor,
                tintColor
        );
        infoHome.fragment = HomeFragment.class;
        HiTabBottomInfo infoFavorite = new HiTabBottomInfo<Integer>(
                "收藏",
                "fonts/iconfont.ttf",
                mActivityProvider.getString(R.string.if_favorite),
                null,
                defaultColor,
                tintColor
        );
        infoFavorite.fragment = FavoriteFragment.class;
        HiTabBottomInfo infoCategory = new HiTabBottomInfo<Integer>(
                "分类",
                "fonts/iconfont.ttf",
                mActivityProvider.getString(R.string.if_category),
                null,
                defaultColor,
                tintColor
        );
        infoCategory.fragment = CategoryFragment.class;
        HiTabBottomInfo infoRecommend = new HiTabBottomInfo<Integer>(
                "推荐",
                "fonts/iconfont.ttf",
                mActivityProvider.getString(R.string.if_recommend),
                null,
                defaultColor,
                tintColor
        );
        infoRecommend.fragment = RecommendFragment.class;
        HiTabBottomInfo infoProfile = new HiTabBottomInfo<Integer>(
                "我的",
                "fonts/iconfont.ttf",
                mActivityProvider.getString(R.string.if_profile),
                null,
                defaultColor,
                tintColor
        );
        infoProfile.fragment = ProfileFragment.class;

        mInfoList.add(infoHome);
        mInfoList.add(infoFavorite);
        mInfoList.add(infoCategory);
        mInfoList.add(infoRecommend);
        mInfoList.add(infoProfile);

        mTabBottomLayout.inflateInfo(mInfoList);

        initFragmentTabView();

        mTabBottomLayout.addTabSelectedChangeListener(new IHiTabLayout.OnTabSelectedListener<HiTabBottomInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @Nullable HiTabBottomInfo<?> preInfo, @NonNull HiTabBottomInfo<?> nextInfo) {
                mFragmentTabView.setCurrentPosition(index);
                mCurrentItemIndex = index;
            }
        });
        mTabBottomLayout.defaultSelected(mInfoList.get(mCurrentItemIndex));
    }

    private void initFragmentTabView() {
        WiTabViewAdapter adapter = new WiTabViewAdapter(mActivityProvider.getSupportFragmentManager(), mInfoList);
        mFragmentTabView = mActivityProvider.findViewById(R.id.fragment_tab_view);
        mFragmentTabView.setAdapter(adapter);
    }

    public interface ActivityProvider {
        <T extends View> T findViewById(@IdRes int id);

        Resources getResources();

        FragmentManager getSupportFragmentManager();

        String getString(@StringRes int resId);
    }
}
