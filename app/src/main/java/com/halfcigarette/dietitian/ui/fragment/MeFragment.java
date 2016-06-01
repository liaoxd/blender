package com.halfcigarette.dietitian.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {

    private View mLayoutView;
    private RelativeLayout settings;
    private RelativeLayout messageManage;
    private RelativeLayout chooseTags;
    private RelativeLayout aboutUs;
    private View.OnClickListener listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.settings:

                    break;
                case R.id.about_us:

                    break;
                case R.id.choose_tags:

                    break;
                case R.id.message_manager:

                    break;
            }
        }
    };

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView() {

        settings = (RelativeLayout) mLayoutView.findViewById(R.id.settings);
        settings.setOnClickListener(listener);
        aboutUs = (RelativeLayout) mLayoutView.findViewById(R.id.about_us);
        aboutUs.setOnClickListener(listener);
        messageManage = (RelativeLayout) mLayoutView.findViewById(R.id.message_manager);
        messageManage.setOnClickListener(listener);
        chooseTags = (RelativeLayout) mLayoutView.findViewById(R.id.choose_tags);
        chooseTags.setOnClickListener(listener);
    }

    @Override
    public void initTitleBar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayoutView = getCreateView(inflater, container);
        ButterKnife.bind(this, mLayoutView);
        initView();
        return mLayoutView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.gc();
    }
}
