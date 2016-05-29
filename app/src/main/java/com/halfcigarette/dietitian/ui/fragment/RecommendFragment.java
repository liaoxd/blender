package com.halfcigarette.dietitian.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseFragment;

import butterknife.ButterKnife;


public class RecommendFragment extends BaseFragment {

    private View mLayoutView;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initTitleBar() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayoutView = getCreateView(inflater, container);
        ButterKnife.bind(this, mLayoutView);
        initView();
        return mLayoutView;
    }


}
