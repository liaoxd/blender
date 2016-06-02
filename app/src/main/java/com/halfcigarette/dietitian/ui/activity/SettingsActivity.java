package com.halfcigarette.dietitian.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseActivity;

/**
 * Created by kiplening on 16-6-2.
 */
public class SettingsActivity extends BaseActivity{
    private TextView countManager,feedback;
    private Button logOut;

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.feed_back:

                    break;
                case R.id.count_manager:

                    break;
                case R.id.log_out:

                    break;
            }
        }
    };
    @Override
    public void initContentView() throws InterruptedException {
        setContentView(R.layout.settings_activity);
    }

    @Override
    public void initView() {
        countManager = (TextView) findViewById(R.id.count_manager);
        feedback = (TextView) findViewById(R.id.feed_back);
        logOut = (Button) findViewById(R.id.log_out);

        countManager.setOnClickListener(listener);
        feedback.setOnClickListener(listener);
        logOut.setOnClickListener(listener);
    }

    @Override
    public void initPresenter() {

    }
}
