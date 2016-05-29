package com.halfcigarette.dietitian.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.CustomBaseAdapter;
import com.halfcigarette.dietitian.beans.Tags;

import java.util.List;

/**
 * Created by dongweihang on 2015/11/3.
 */
public class SimpleFoodAdapter extends CustomBaseAdapter {

    private TextView nameTV;
    private TextView introduceTV;
    private ImageView photoIV;

    public SimpleFoodAdapter(Context context, List<Tags.Food> foods) {
        super(context);
        setData(foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = View.inflate(getContext(), R.layout.simplefood_item, null);

        nameTV = (TextView) item.findViewById(R.id.food_nameTV);
        //calorieTV = (TextView) item.findViewById(R.id.simplefood_numTV);
        photoIV = (ImageView) item.findViewById(R.id.food_photoIV);
        introduceTV = (TextView) item.findViewById(R.id.introduce_foodTV);

        //这里继承自一个泛型的话。怎么才能制定显示的值呢。？
        //应该是把泛型强制转换回来。

        Tags.Food food = (Tags.Food) getData().get(position);
        nameTV.setText(food.getName());
        photoIV.setImageBitmap(food.getFoodPhoto());
        introduceTV.setText(food.getIntroduce());
        return item;
    }
}
