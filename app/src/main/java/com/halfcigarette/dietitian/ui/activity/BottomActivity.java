package com.halfcigarette.dietitian.ui.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseActivity;
import com.halfcigarette.dietitian.base.BaseFragment;
import com.halfcigarette.dietitian.beans.Tags;
import com.halfcigarette.dietitian.data.StaticData;
import com.halfcigarette.dietitian.ui.fragment.FindFragment;
import com.halfcigarette.dietitian.ui.fragment.FoodFragment;
import com.halfcigarette.dietitian.ui.fragment.MeFragment;
import com.halfcigarette.dietitian.ui.fragment.RecordFragment;

import java.util.HashMap;

public class BottomActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;

    private FoodFragment foodFragment;
    private MeFragment meFragment;
    private RecordFragment recordFragment;
    private FindFragment findFragment;

    //    private Set<BaseFragment> fragmentSet;
    private BaseFragment dispFragment;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_bottom);
    }

    @Override
    public void initView() {

        radioGroup = (RadioGroup) findViewById(R.id.mainRadioGroup);

        ((RadioButton) radioGroup.getChildAt(0)).toggle();
        foodFragment = new FoodFragment();
        dispFragment = foodFragment;
//        addFragment(R.id.fragmentcontainer, foodFragment);
        replaceFragment(R.id.fragmentcontainer, foodFragment);

        radioButton1 = (RadioButton) findViewById(R.id.radioBT11);
        radioButton2 = (RadioButton) findViewById(R.id.radioBT22);
        //radioButton3 = (RadioButton) findViewById(R.id.radioBT33);
        radioButton4 = (RadioButton) findViewById(R.id.radioBT44);

        Drawable selector1 = getResources().getDrawable(R.drawable.food_layout);
        selector1.setBounds(0, 0, 120, 120);
        radioButton1.setCompoundDrawables(null, selector1, null, null);

        Drawable selector2 = getResources().getDrawable(R.drawable.record_layout);
        selector2.setBounds(0, 0, 120, 120);
        radioButton2.setCompoundDrawables(null, selector2, null, null);

        //Drawable selector3 = getResources().getDrawable(R.drawable.find_layout);
        //selector3.setBounds(0, 0, 120, 120);
        //radioButton3.setCompoundDrawables(null, selector3, null, null);

        Drawable selector4 = getResources().getDrawable(R.drawable.me_layout);
        selector4.setBounds(0, 0, 120, 120);
        radioButton4.setCompoundDrawables(null, selector4, null, null);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case (R.id.radioBT11): {
                        replaceFragment(R.id.fragmentcontainer, foodFragment);
//                        hideFragment(dispFragment);
//                        showFragment(foodFragment);
//                        dispFragment = foodFragment;
                    }
                    break;
                    case (R.id.radioBT22): {
                        if (recordFragment == null) {
                            recordFragment = new RecordFragment();
                            replaceFragment(R.id.fragmentcontainer, recordFragment);
                        } else {
                            replaceFragment(R.id.fragmentcontainer, recordFragment);
                        }
//                        if (recordFragment == null) {
//                            hideFragment(dispFragment);
//
//                            recordFragment = new RecordFragment();
//                            dispFragment = recordFragment;
//                            addFragment(R.id.fragmentcontainer, recordFragment);
//                        } else {
//                            hideFragment(dispFragment);
//                            showFragment(recordFragment);
//                            dispFragment = recordFragment;
//                        }

                    }
                    break;
                    //case (R.id.radioBT33): {
                    //    if (findFragment == null) {
                    //        findFragment = new FindFragment();
                    //        replaceFragment(R.id.fragmentcontainer, findFragment);
                    //    } else {
                    //        replaceFragment(R.id.fragmentcontainer, findFragment);
                    //    }
//                        if (findFragment == null) {
//                            hideFragment(dispFragment);
//
//                            findFragment = new FindFragment();
//                            dispFragment = findFragment;
//                            addFragment(R.id.fragmentcontainer, findFragment);
//                        } else {
//                            hideFragment(dispFragment);
//                            showFragment(findFragment);
//                            dispFragment = findFragment;
//                        }
                    //}
                    //break;
                    case (R.id.radioBT44): {
                        if (meFragment == null) {
                            meFragment = new MeFragment();
                            replaceFragment(R.id.fragmentcontainer, meFragment);
                        } else {
                            replaceFragment(R.id.fragmentcontainer, meFragment);
                        }
//                        if (meFragment == null) {
//                            hideFragment(dispFragment);
//
//                            meFragment = new MeFragment();
//                            dispFragment = meFragment;
//                            addFragment(R.id.fragmentcontainer, meFragment);
//                        } else {
//                            hideFragment(dispFragment);
//                            showFragment(meFragment);
//                            dispFragment = meFragment;
//                        }
                    }
                    break;
                }
            }
        });

        //用于测试

    }

    @Override
    public void initPresenter() {
        StaticData.foodList.add(new Tags.Food("羊肉", "益气补虚、温中暖下、促消化、补肾壮阳", 2, 203.0, 0.2, 14.6, 3.9, 60.0, 0.0, 5.2, 0.0, 0.0, 9.0, 6.06, 403.0, 196.0, 0.0, 69.4, 17.0, 7.18, 3.9, 0.11, 0.0, 11.0, 0.15, 0.16, 0.0, 0.0, 1.0, 0.31, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("猪肉", "滋养脏腑、滑润肌肤、补中益气", 2, 395.0, 1.1, 14.6, 30.8, 69.0, 0.0, 2.8, 0.0, 0.0, 11.0, 0.84, 162.0, 130.0, 0.0, 57.5, 12.0, 2.94, 2.4, 0.0, 0.0, 16.0, 0.26, 0.11, 0.0, 0.0, 0.0, 0.95, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("鸡肉", "滋补血液、补肾益精", 2, 167.0, 1.4, 18.5, 9.6, 187.0, 0.0, 5.0, 0.0, 0.0, 17.0, 1.29, 340.0, 160.0, 0.0, 72.4, 7.0, 5.4, 0.9, 0.1, 0.0, 42.0, 0.07, 0.08, 0.0, 0.0, 3.0, 0.2, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("牛肉", "补中益气、滋养脾胃、强健筋骨", 2, 125.0, 0.2, 17.8, 2.0, 122.0, 0.0, 4.1, 0.0, 0.0, 6.0, 1.77, 270.0, 150.0, 0.0, 48.6, 17.0, 6.26, 2.2, 0.0, 0.0, 3.0, 0.02, 0.24, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("鲫鱼", "和中开胃、通乳催奶、活血通络", 2, 108.0, 3.8, 17.1, 2.7, 130.0, 0.0, 2.5, 0.0, 0.0, 79.0, 1.94, 290.0, 193.0, 0.0, 41.2, 41.0, 14.31, 1.3, 0.08, 0.06, 17.0, 0.04, 0.09, 0.0, 0.1, 0.0, 0.68, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("鸡蛋", "健脑益智、延年益寿、防癌", 3, 144.0, 2.8, 13.3, 8.8, 585.0, 0.0, 0.2, 0.0, 0.0, 56.0, 1.1, 154.0, 130.0, 0.0, 131.5, 10.0, 14.34, 2.0, 0.15, 0.04, 234.0, 0.11, 0.27, 0.0, 0.0, 0.0, 1.84, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("大米", "健脾养胃、补中益气", 0, 346.0, 77.9, 7.4, 0.8, 0.0, 0.0, 1.9, 0.7, 0.0, 13.0, 1.7, 103.0, 110.0, 0.0, 3.8, 34.0, 2.23, 2.3, 0.3, 1.29, 0.0, 0.12, 0.05, 0.0, 0.0, 0.0, 0.46, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("黄豆", "抗癌防癌、降低胆固醇", 0, 359.0, 34.2, 35.0, 16.0, 0.0, 0.0, 2.1, 15.5, 0.0, 191.0, 3.34, 1503.0, 465.0, 9.7, 2.2, 199.0, 6.16, 8.2, 1.35, 2.26, 37.0, 0.41, 0.2, 0.0, 0.0, 0.0, 18.9, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("玉米", "降压降糖、美容护肤、健脾开胃", 0, 196.0, 22.8, 4.0, 1.2, 0.0, 0.0, 1.8, 2.9, 0.34, 1.0, 0.9, 238.0, 117.0, 0.0, 1.1, 32.0, 1.63, 1.1, 0.09, 0.0, 63.0, 0.16, 0.12, 0.0, 0.0, 16.0, 0.46, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("红薯", "和血补中、宽肠通便、益气生津", 0, 102.0, 80.5, 4.7, 0.8, 0.0, 0.0, 0.6, 2.0, 0.0, 23.0, 0.15, 130.0, 39.0, 0.0, 28.5, 12.0, 0.48, 0.5, 0.18, 0.11, 0.0, 0.0, 0.0, 0.0, 0.0, 26.0, 0.28, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("红豆", "解酒利尿、解毒抗癌", 0, 309.0, 63.4, 20.2, 0.6, 0.0, 0.0, 2.0, 7.7, 80.0, 74.0, 2.2, 860.0, 305.0, 7.8, 2.2, 138.0, 3.8, 7.4, 0.64, 1.33, 13.0, 0.16, 0.13, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("花生", "止血散瘀、消肿、润肺和胃、敛肺止咳", 3, 298.0, 13.0, 12.0, 25.4, 0.0, 0.0, 14.1, 7.7, 10.0, 8.0, 1.79, 0.0, 250.0, 0.0, 3.7, 110.0, 4.5, 3.4, 0.68, 0.65, 2.0, 0.0, 0.04, 0.0, 0.0, 14.0, 2.93, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("豌豆", "养血益气、补肾润肺、纳气平喘", 3, 105.0, 21.2, 7.4, 0.3, 0.0, 0.0, 2.3, 3.0, 220.0, 21.0, 1.29, 332.0, 127.0, 0.0, 1.2, 43.0, 1.74, 1.7, 0.22, 0.65, 37.0, 0.43, 0.09, 0.0, 0.0, 14.0, 1.21, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("番茄", "润肺生津、健胃消食、养阴凉血、提高食欲", 1, 19.0, 4.0, 0.9, 0.2, 0.0, 5.6, 0.0, 0.5, 550.0, 10.0, 0.13, 0.0, 23.0, 2.5, 5.0, 9.0, 0.15, 0.4, 0.06, 0.08, 92.0, 0.03, 0.03, 0.0, 0.06, 19.0, 0.57, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("土豆", "健脾健胃、益气和中", 1, 76.0, 17.2, 2.0, 0.2, 0.0, 0.0, 1.1, 0.7, 30.0, 8.0, 0.37, 342.0, 40.0, 0.0, 2.7, 23.0, 0.78, 0.8, 0.12, 0.14, 5.0, 0.08, 0.04, 0.0, 0.0, 27.0, 0.34, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("白菜", "养胃利肠、解酒利便、降脂清热、除烦解渴、防癌抗癌", 1, 18.0, 3.2, 1.5, 0.1, 0.0, 0.0, 0.6, 0.8, 120.0, 50.0, 0.38, 0.0, 31.0, 0.0, 57.5, 11.0, 0.49, 0.7, 0.05, 0.15, 20.0, 0.04, 0.05, 0.0, 0.0, 31.0, 0.76, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("黄瓜", "清热解毒、利水消肿、生津止渴", 1, 15.0, 2.9, 0.8, 0.2, 0.0, 0.0, 0.2, 0.5, 90.0, 24.0, 0.18, 102.0, 24.0, 0.0, 4.9, 15.0, 0.38, 0.5, 0.05, 0.06, 15.0, 0.02, 0.03, 0.0, 0.0, 9.0, 0.49, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("茄子", "清热凉血、消肿解毒", 1, 21.0, 4.9, 1.1, 0.2, 0.0, 0.0, 0.6, 1.3, 50.0, 24.0, 0.23, 142.0, 23.0, 0.0, 5.4, 13.0, 0.48, 0.5, 0.1, 0.13, 8.0, 0.0, 0.04, 0.0, 0.0, 5.0, 1.13, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("西兰花", "防癌抗癌、增强免疫力", 1, 33.0, 4.3, 4.1, 0.6, 0.0, 29.8, 0.9, 1.6, 721.0, 67.0, 0.78, 17.0, 72.0, 0.0, 18.8, 17.0, 0.7, 1.0, 0.03, 0.24, 0.0, 0.09, 0.13, 0.0, 0.17, 51.0, 0.91, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("生姜", "发表散寒、温胃止呕、解毒", 1, 273.0, 46.3, 9.1, 5.7, 0.0, 0.0, 0.0, 17.7, 6.3, 62.0, 2.3, 41.0, 22.0, 0.0, 9.9, 0.0, 3.1, 85.0, 0.96, 10.65, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("青椒", "散寒除湿、缓解疲劳、防止便秘、预防坏血病", 1, 22.0, 5.4, 1.0, 0.2, 0.0, 0.0, 0.0, 1.4, 340.0, 14.0, 0.19, 142.0, 20.0, 0.0, 33.0, 12.0, 0.38, 0.8, 0.09, 0.0, 0.0, 0.03, 0.03, 0.0, 0.0, 72.0, 0.0, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("香菇", "化痰理气、益胃和中、托疹解毒、滋味助食", 1, 19.0, 61.7, 2.2, 0.3, 0.0, 0.0, 2.0, 3.3, 0.0, 2.0, 0.66, 20.0, 53.0, 0.0, 1.4, 11.0, 2.58, 0.3, 0.12, 0.25, 0.0, 0.0, 0.08, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("胡萝卜", "健脾消食、补肝明目、润肠通便、清热解毒、降气止咳", 1, 37.0, 8.8, 1.0, 0.2, 0.0, 4.8, 0.0, 3.2, 3620.0, 30.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.6, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("香菜", "健胃、驱风解毒、促进周身血液循环", 1, 31.0, 6.2, 1.8, 0.4, 0.0, 0.0, 2.2, 1.2, 116.0, 101.0, 0.45, 272.0, 49.0, 1.5, 48.5, 33.0, 0.53, 2.9, 0.21, 0.28, 193.0, 0.04, 0.14, 0.0, 0.0, 48.0, 0.8, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("木耳", "养血驻颜、祛病延年、补气益智、滋养强壮、止血活血、滋阴润燥", 1, 205.0, 65.6, 12.1, 1.5, 0.0, 0.0, 2.5, 29.9, 100.0, 247.0, 3.18, 757.0, 292.0, 0.0, 48.5, 152.0, 3.72, 97.4, 0.32, 8.86, 17.0, 0.0, 0.0, 0.0, 0.0, 0.0, 11.34, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("苹果", "提神醒脑、消除压抑、润肺除烦", 1, 52.0, 13.5, 0.2, 0.2, 0.0, 0.0, 0.2, 1.2, 20.0, 4.0, 0.19, 119.0, 12.0, 0.0, 1.6, 4.0, 0.12, 0.6, 0.06, 0.03, 3.0, 0.06, 0.02, 0.0, 0.0, 4.0, 2.12, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("香蕉", "纤体美肤、滋养润肠、润肺止咳、清热解毒", 1, 91.0, 22.0, 1.4, 0.2, 0.0, 0.0, 0.7, 1.2, 60.0, 7.0, 0.0, 256.0, 28.0, 2.5, 0.8, 43.0, 0.87, 0.4, 0.14, 0.65, 10.0, 0.02, 0.04, 0.0, 0.0, 8.0, 0.24, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("菠菜", "滋阴补血、养肝明目", 1, 24.0, 4.5, 2.6, 0.3, 0.0, 0.0, 0.6, 1.7, 292.0, 66.0, 0.85, 311.0, 47.0, 0.0, 85.2, 58.0, 0.97, 2.9, 0.1, 0.66, 487.0, 0.04, 0.11, 0.0, 0.0, 32.0, 1.74, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("韭菜", "温中开胃、行气活血、补肾助阳、调和脏腑", 1, 26.0, 4.6, 2.4, 0.4, 0.0, 61.2, 0.8, 1.4, 141.0, 42.0, 0.43, 247.0, 38.0, 0.0, 8.1, 25.0, 1.38, 1.6, 0.08, 0.43, 235.0, 0.02, 0.09, 0.0, 0.2, 24.0, 0.96, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("海带", "补碘御寒、利尿消肿、减少辐射、抗癌防癌", 1, 12.0, 2.1, 1.2, 0.1, 0.0, 0.0, 1.3, 0.5, 0.0, 46.0, 0.16, 246.0, 22.0, 113.9, 8.6, 25.0, 9.54, 0.9, 0.0, 0.07, 0.0, 0.02, 0.15, 0.0, 0.0, 0.0, 1.85, 0.0, 0.0));
        StaticData.foodList.add(new Tags.Food("南瓜", "补益肝肾、解毒杀虫、降糖止咳、防癌抗癌", 1, 22.0, 5.3, 0.7, 0.1, 0.0, 0.0, 0.4, 0.8, 890.0, 16.0, 0.14, 145.0, 24.0, 0.0, 0.8, 8.0, 0.46, 0.4, 0.03, 0.08, 148.0, 0.03, 0.04, 0.0, 0.0, 8.0, 0.36, 0.0, 0.0));
//        StaticData.foodList.add(new Food("白萝卜", "健脾消食、补肝明目、润肠通便、清热解毒、降气止咳", 1, 37.0, 8.8, 1.0, 0.2, 0.0, 4.8, 0.0, 3.2, 3620.0, 30.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.6, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
//        StaticData.foodList.add(new Food("柠檬", "止渴生津、祛暑安胎、健胃止痛", 1, 19.0, 4.0, 0.9, 0.2, 0.0, 5.6, 0.0, 0.5, 550.0, 10.0, 0.13, 0.0, 23.0, 2.5, 5.0, 9.0, 0.15, 0.4, 0.06, 0.08, 92.0, 0.03, 0.03, 0.0, 0.06, 19.0, 0.57, 0.0, 0.0));
//        StaticData.foodList.add(new Food("红椒", "散寒除湿、缓解疲劳、防止便秘、预防坏血病", 1, 22.0, 5.4, 1.0, 0.2, 0.0, 0.0, 0.0, 1.4, 340.0, 14.0, 0.19, 142.0, 20.0, 0.0, 33.0, 12.0, 0.38, 0.8, 0.09, 0.0, 0.0, 0.03, 0.03, 0.0, 0.0, 72.0, 0.0, 0.0, 0.0));
//        StaticData.foodList.add(new Food("橘子", "开胃理气、生精润肺、化痰止咳", 1, 52.0, 13.5, 0.2, 0.2, 0.0, 0.0, 0.2, 1.2, 20.0, 4.0, 0.19, 119.0, 12.0, 0.0, 1.6, 4.0, 0.12, 0.6, 0.06, 0.03, 3.0, 0.06, 0.02, 0.0, 0.0, 4.0, 2.12, 0.0, 0.0));

        StaticData.foodList.add(new Tags.Food("柠檬", "止渴生津、祛暑安胎、疏滞、健胃、止痛", 1, 35.00, 6.20, 1.10, 1.20, 0.00, 0.00, 0.60, 1.30, 0.00, 101.00, 101.00, 209.00, 22.00, 0.00, 1.10, 37.00, 0.50, 0.80, 0.14, 0.05, 0.00, 0.05, 0.02, 0.00, 0.00, 22.00, 0.00, 0.00, 0.00));
        StaticData.foodList.add(new Tags.Food("白萝卜", "防癌抗癌、提高免疫力、调理肠胃、开胃消食", 1, 21.00, 5.00, 0.90, 0.10, 0.00, 6.80, 0.30, 1.00, 20.00, 36.00, 0.30, 173.00, 26.00, 0.00, 61.80, 16.00, 0.61, 0.50, 0.04, 0.09, 3.00, 0.02, 0.03, 0.00, 0.06, 21.00, 0.92, 0.00, 0.00));
        StaticData.foodList.add(new Tags.Food("红椒", "散寒除湿、缓解疲劳、防止便秘、预防坏血病", 1, 22.00, 5.40, 1.00, 0.20, 0.00, 0.00, 0.00, 1.40, 340.00, 14.00, 0.19, 142.00, 20.00, 0.00, 33.00, 12.00, 0.38, 0.80, 0.09, 0.00, 0.00, 0.03, 0.03, 0.00, 0.00, 72.00, 0.00, 0.00, 0.00));
        StaticData.foodList.add(new Tags.Food("橘子", "通络理气、活血化痰、抗氧化", 1, 43.00, 10.20, 0.80, 0.10, 0.00, 0.00, 0.00, 0.50, 490.00, 24.00, 0.13, 128.00, 18.00, 0.00, 0.80, 14.00, 0.70, 0.20, 0.11, 0.03, 82.00, 0.04, 0.03, 0.03, 0.00, 35.00, 1.22, 0.00, 0.00));
        StaticData.foodList.add(new Tags.Food("香梨", "生津、清热、止咳、润燥、解酒", 1, 44.00, 13.30, 0.40, 0.20, 0.00, 0.00, 0.00, 3.10, 33.00, 9.00, 0.46, 92.00, 14.00, 0.70, 2.10, 8.00, 1.14, 0.50, 0.62, 0.07, 6.00, 0.00, 0.06, 0.00, 0.00, 6.00, 1.34, 0.00, 0.00));

        for (int i = 0; i < StaticData.foodList.size(); i++) {
            StaticData.foodHashMap.put(StaticData.foodList.get(i).getName(), StaticData.foodList.get(i));
            Integer id = 0;
            switch (StaticData.foodList.get(i).getName()) {
                case ("羊肉"):
                    id = R.mipmap.yangrou;
                    break;
                case ("猪肉"):
                    id = R.mipmap.zhurou;
                    break;
                case ("鸡肉"):
                    id = R.mipmap.jirou;
                    break;
                case ("牛肉"):
                    id = R.mipmap.niurou;
                    break;
                case ("鲫鱼"):
                    id = R.mipmap.jiyu;
                    break;
                case ("鸡蛋"):
                    id = R.mipmap.jidan;
                    break;
                case ("大米"):
                    id = R.mipmap.dami;
                    break;
                case ("黄豆"):
                    id = R.mipmap.huangdou;
                    break;
                case ("玉米"):
                    id = R.mipmap.yumi;
                    break;
                case ("红薯"):
                    id = R.mipmap.hongshu;
                    break;
                case ("红豆"):
                    id = R.mipmap.hongdou;
                    break;
                case ("花生"):
                    id = R.mipmap.huasheng;
                    break;
                case ("豌豆"):
                    id = R.mipmap.wandou;
                    break;
                case ("番茄"):
                    id = R.mipmap.fanqie;
                    break;
                case ("土豆"):
                    id = R.mipmap.tudou;
                    break;
                case ("白菜"):
                    id = R.mipmap.baicai;
                    break;
                case ("黄瓜"):
                    id = R.mipmap.huanggua;
                    break;
                case ("茄子"):
                    id = R.mipmap.qiezi;
                    break;
                case ("西兰花"):
                    id = R.mipmap.xilanhua;
                    break;
                case ("生姜"):
                    id = R.mipmap.shengjiang;
                    break;
                case ("青椒"):
                    id = R.mipmap.qingjiao;
                    break;
                case ("香菇"):
                    id = R.mipmap.xianggu;
                    break;
                case ("胡萝卜"):
                    id = R.mipmap.huluobo;
                    break;
                case ("香菜"):
                    id = R.mipmap.xiangcai;
                    break;
                case ("木耳"):
                    id = R.mipmap.muer;
                    break;
                case ("苹果"):
                    id = R.mipmap.pingguo;
                    break;
                case ("香蕉"):
                    id = R.mipmap.xiangjiao;
                    break;
                case ("菠菜"):
                    id = R.mipmap.bocai;
                    break;
                case ("韭菜"):
                    id = R.mipmap.jiucai;
                    break;
                case ("海带"):
                    id = R.mipmap.haidai;
                    break;
                case ("南瓜"):
                    id = R.mipmap.nangua;
                    break;
                case ("白萝卜"):
                    id = R.mipmap.bailuobo;
                    break;
                case ("柠檬"):
                    id = R.mipmap.ningmeng;
                    break;
                case ("红椒"):
                    id = R.mipmap.hongjiao;
                    break;
                case ("橘子"):
                    id = R.mipmap.juzi;
                    break;
                case ("香梨"):
                    id = R.mipmap.xiangli;
                    break;
                default:
                    id = null;
                    break;
            }
            if (id != null) {
                StaticData.foodImageSource.put(StaticData.foodList.get(i).getName(), id);
            }
        }

        StaticData.foodList.clear();

        //初始化营养值摄入标准
//        StaticData.nutritionStandardHashMap.put ("tanshui", new HashMap<String,Double>(){{put("min",150.0);put("recommend",250.0);put("max",350.0);put("limit",null);}});
        StaticData.nutritionStandardHashMap.put("danbai", new HashMap<String, Double>() {{
            put("min", 75.0);
            put("recommend", 82.0);
            put("max", 90.0);
            put("limit", null);
        }});
        StaticData.nutritionStandardHashMap.put("zhifang", new HashMap<String, Double>() {{
            put("min", 48.0);
            put("recommend", 60.0);
            put("max", 72.0);
            put("limit", null);
        }});
//        StaticData.nutritionStandardHashMap.put ("dangu", new HashMap<String,Double>(){{put("min",0.0);put("recommend",0.0);put("max",300.0);put("limit",0.0);}});
//        StaticData.nutritionStandardHashMap.put ("yesuan", new HashMap<String,Double>(){{put("min",0.0);put("recommend",0.0);put("max",0.0);put("limit",0.0);}});
        StaticData.nutritionStandardHashMap.put("yansuan", new HashMap<String, Double>() {{
            put("min", 10.0);
            put("recommend", 12.0);
            put("max", 14.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("xianwei", new HashMap<String, Double>() {{
            put("min", 20.0);
            put("recommend", 25.0);
            put("max", 30.0);
            put("limit", 0.0);
        }});
//        StaticData.nutritionStandardHashMap.put ("huluobo", new HashMap<String,Double>(){{put("min",0.0);put("recommend",0.0);put("max",0.0);put("limit",0.0);}});
        StaticData.nutritionStandardHashMap.put("gai", new HashMap<String, Double>() {{
            put("min", 700.0);
            put("recommend", 800.0);
            put("max", 900.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("xin", new HashMap<String, Double>() {{
            put("min", 10.0);
            put("recommend", 13.0);
            put("max", 15.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("jia", new HashMap<String, Double>() {{
            put("min", 1.0);
            put("recommend", 2.0);
            put("max", 3.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("lin", new HashMap<String, Double>() {{
            put("min", 600.0);
            put("recommend", 700.0);
            put("max", 800.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("dian", new HashMap<String, Double>() {{
            put("min", 50.0);
            put("recommend", 150.0);
            put("max", 200.0);
            put("limit", 0.0);
        }});
//        StaticData.nutritionStandardHashMap.put ("na", new HashMap<String,Double>(){{put("min",2000.0);put("recommend",0.0);put("max",2400.0);put("limit",0.0);}});
        StaticData.nutritionStandardHashMap.put("mei", new HashMap<String, Double>() {{
            put("min", 300.0);
            put("recommend", 350.0);
            put("max", 400.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("xi", new HashMap<String, Double>() {{
            put("min", 40.0);
            put("recommend", 50.0);
            put("max", 60.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("tie", new HashMap<String, Double>() {{
            put("min", 15.0);
            put("recommend", 20.0);
            put("max", 30.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("tong", new HashMap<String, Double>() {{
            put("min", 1.0);
            put("recommend", 2.0);
            put("max", 3.0);
            put("limit", 0.0);
        }});
//        StaticData.nutritionStandardHashMap.put ("meng", new HashMap<String,Double>(){{put("min",0.0);put("recommend",0.0);put("max",0.0);put("limit",0.0);}});
        StaticData.nutritionStandardHashMap.put("weia", new HashMap<String, Double>() {{
            put("min", 700.0);
            put("recommend", 750.0);
            put("max", 800.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("weib", new HashMap<String, Double>() {{
            put("min", 1.0);
            put("recommend", 1.2);
            put("max", 1.5);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("weib2", new HashMap<String, Double>() {{
            put("min", 1.0);
            put("recommend", 1.2);
            put("max", 1.5);
            put("limit", 0.0);
        }});
//        StaticData.nutritionStandardHashMap.put ("weib12", new HashMap<String,Double>(){{put("min",0.0);put("recommend",0.0);put("max",0.0);put("limit",0.0);}});
//        StaticData.nutritionStandardHashMap.put ("weib6", new HashMap<String,Double>(){{put("min",0.0);put("recommend",0.0);put("max",0.0);put("limit",0.0);}});
        StaticData.nutritionStandardHashMap.put("weic", new HashMap<String, Double>() {{
            put("min", 80.0);
            put("recommend", 90.0);
            put("max", 100.0);
            put("limit", 0.0);
        }});
        StaticData.nutritionStandardHashMap.put("weie", new HashMap<String, Double>() {{
            put("min", 12.0);
            put("recommend", 14.0);
            put("max", 16.0);
            put("limit", 0.0);
        }});
//        StaticData.nutritionStandardHashMap.put ("weid", new HashMap<String,Double>(){{put("min",0.0);put("recommend",0.0);put("max",0.0);put("limit",0.0);}});
//        StaticData.nutritionStandardHashMap.put ("weik", new HashMap<String,Double>(){{put("min",0.0);put("recommend",0.0);put("max",0.0);put("limit",0.0);}});


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /* 从png文件得到Bitmap
    * 并且将尺寸调整为宽=w、高=h
    */
    public Bitmap getMyBitmap(int imagesourceid, int w, int h) {
        Bitmap oldbmp = BitmapFactory.decodeResource(this.getResources(), imagesourceid);
        if (oldbmp != null) {
            int width = oldbmp.getWidth();
            int height = oldbmp.getHeight();
            Matrix matrix = new Matrix();
            float scaleWidth = ((float) w / width);
            float scaleHeight = ((float) h / height);
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
            return newbmp;
        } else {
            return null;
        }
    }
}
