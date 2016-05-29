package com.halfcigarette.dietitian.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseFragment;
import com.halfcigarette.dietitian.ui.activity.MylableActivity;
import com.halfcigarette.dietitian.ui.custom.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {

    @Bind(R.id.me_title_bar)
    TitleBar me_title_bar;

    private View mLayoutView;

    private RelativeLayout bt_mylableclick;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView() {
        me_title_bar.setTitle("膳食专家");
        bt_mylableclick = (RelativeLayout) mLayoutView.findViewById(R.id.bt_mylableclick);
        bt_mylableclick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getBaseActivity().getApplicationContext(), MylableActivity.class));
                    }
                }
        );
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
