package com.will.willshop;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.will.common.ui.component.WiBaseActivity;
import com.will.ui.tab.bottom.WiTabBottomLayout;
import com.will.willshop.logic.MainActivityLogic;

public class MainActivity extends WiBaseActivity implements MainActivityLogic.ActivityProvider {
    MainActivityLogic mActivityLogic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityLogic = new MainActivityLogic(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mActivityLogic.onSaveInstanceState(outState);
    }
}
