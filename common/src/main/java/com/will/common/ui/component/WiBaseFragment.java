package com.will.common.ui.component;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.will.common.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class WiBaseFragment extends Fragment {
    protected View rootView;

    @LayoutRes
    public abstract int getRootViewId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(getRootViewId(), container, false);
        return rootView;
    }
}
