package com.halfcigarette.dietitian.ui.custom;

/**
 * Created by dongweihang on 2015-12-01.
 */

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

/**
 * 动画显示数字
 * Created by fhp on 15/1/7.
 */
public class CountView extends TextView {
    //动画时长 ms
    int duration = 1500;
    float number;

    public CountView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void showNumberWithAnimation(float number) {
        //修改number属性，会调用setNumber方法
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "number", 0, number);
        objectAnimator.setDuration(duration);
        //加速器，从慢到快到再到慢
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
        setText(String.format("%1$03.1f", number));
    }
}
