package com.halfcigarette.dietitian.ui.activity;

import android.os.Bundle;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseActivity;
import com.halfcigarette.dietitian.ui.custom.TitleBar;

import butterknife.Bind;

public class MylableActivity extends BaseActivity {
    @Bind(R.id.mylable_title_bar)
    TitleBar mylable_title_bar;

    @Override
    public void initContentView() throws InterruptedException {
        setContentView(R.layout.activity_mylable);
    }

    @Override
    public void initView() {

        mylable_title_bar.setTitle("我的标签");


    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
