package com.halfcigarette.dietitian.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.CustomBaseAdapter;
import com.halfcigarette.dietitian.beans.NutritionElement;

import java.util.List;

/**
 * Created by dongweihang on 2015/11/12.
 */
public class NutritionAdapter extends CustomBaseAdapter {

    private TextView nutritionNameTextView;
    private LinearLayout pPercentLinearLayout;
    private LinearLayout nPercentLinearLayout;

    public NutritionAdapter(Context context, List<NutritionElement> nutritionList) {
        super(context);
        setData(nutritionList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = View.inflate(this.getContext(), R.layout.nutrition_item, null);

        nutritionNameTextView = (TextView) item.findViewById(R.id.nutritionNameTextView);
        pPercentLinearLayout = (LinearLayout) item.findViewById(R.id.pPercentLinearLayout);
        nPercentLinearLayout = (LinearLayout) item.findViewById(R.id.nPercentLinearLayout);

        NutritionElement nutritionElement = (NutritionElement) getData().get(position);
        nutritionNameTextView.setText(nutritionElement.getName());

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) pPercentLinearLayout.getLayoutParams();
        params.weight = (float) nutritionElement.getContent();
        pPercentLinearLayout.setLayoutParams(params);

        params = (LinearLayout.LayoutParams) nPercentLinearLayout.getLayoutParams();
        params.weight = (float) (nutritionElement.getStandard() * 2 - nutritionElement.getContent());
        nPercentLinearLayout.setLayoutParams(params);

        return item;
    }
}
