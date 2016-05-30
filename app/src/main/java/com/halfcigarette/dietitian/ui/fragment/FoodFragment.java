package com.halfcigarette.dietitian.ui.fragment;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseFragment;
import com.halfcigarette.dietitian.beans.Tags;
import com.halfcigarette.dietitian.data.SimpleFoodAdapter;
import com.halfcigarette.dietitian.data.StaticData;
import com.halfcigarette.dietitian.ui.activity.AddfoodActivity;
import com.halfcigarette.dietitian.ui.custom.TitleBar;
import com.halfcigarette.dietitian.utils.Logger;

import at.markushi.ui.CircleButton;
import butterknife.Bind;
import butterknife.ButterKnife;


public class FoodFragment extends BaseFragment {

    @Bind(R.id.food_title_bar)
    TitleBar food_title_bar;

    private View mLayoutView;


    private ListView recommendCookFood;

    private CircleButton start;
    private TextView reminder;
    private SimpleFoodAdapter simpleFoodAdapter;
    //private CookMenuAdapter cookMenuAdapter;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_food;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void initView() {
        food_title_bar.setTitle("膳食专家");

        start = (CircleButton) mLayoutView.findViewById(R.id.start);
        recommendCookFood = (ListView) mLayoutView.findViewById(R.id.recommend_food);
        reminder = (TextView) mLayoutView.findViewById(R.id.reminder);



        simpleFoodAdapter = new SimpleFoodAdapter(mLayoutView.getContext(), StaticData.foodHorizontalList);

        StaticData.foodHorizontalList.clear();
        StaticData.foodHorizontalList.add(getFoodFromHashMap("菠菜"));
        StaticData.foodHorizontalList.add(getFoodFromHashMap("白菜"));
        StaticData.foodHorizontalList.add(getFoodFromHashMap("黄豆"));
        StaticData.foodHorizontalList.add(getFoodFromHashMap("苹果"));
        StaticData.foodHorizontalList.add(getFoodFromHashMap("鸡蛋"));
        StaticData.foodHorizontalList.add(getFoodFromHashMap("鸡肉"));
        StaticData.foodHorizontalList.add(getFoodFromHashMap("番茄"));

        recommendCookFood.setAdapter(simpleFoodAdapter);

    }



    @Override
    public void onResume() {
        super.onResume();
        Logger.e("foodfragment resume");
        simpleFoodAdapter.notifyDataSetChanged();
        //mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initTitleBar() {

    }

    //继承自basefragment的fragment的这里都要这样写
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLayoutView = getCreateView(inflater, container);
        ButterKnife.bind(this, mLayoutView);
        initView();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mLayoutView.getContext(), AddfoodActivity.class));
            }
        });
        return mLayoutView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.gc();
    }

    public Tags.Food getFoodFromHashMap(String foodName) {
        Tags.Food food = StaticData.foodHashMap.get(foodName);
        food.setFoodPhoto(BitmapFactory.decodeResource(getResources(), StaticData.foodImageSource.get(foodName)));

        return food;
    }
}
