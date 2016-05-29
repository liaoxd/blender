package com.halfcigarette.dietitian.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseActivity;


public class LaunchActivity extends BaseActivity {
    private ImageView launchImageView;
    private Animation animation;
    private Bitmap bitmap;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_launch);

    }

    @Override
    public void initView() {
        launchImageView = (ImageView) findViewById(R.id.launchImageView);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.launchimage2);
        launchImageView.setImageBitmap(bitmap);
        animation = AnimationUtils.loadAnimation(this, R.anim.enlarge);
        animation.setFillAfter(true);
        launchImageView.startAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(getApplication(), BottomActivity.class));
                    close();
                }
            }
        }).start();

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bitmap = null;
        launchImageView.destroyDrawingCache();
        System.gc();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
