package com.halfcigarette.dietitian.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.beans.Tags;

import java.util.List;

/**
 * Created by dongweihang on 2015/11/23.
 */
public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Tags.Food> foods;

    public HorizontalRecyclerViewAdapter(LayoutInflater layoutInflater, List<Tags.Food> foods) {
        this.layoutInflater = layoutInflater;
        this.foods = foods;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView mTxt;
    }

    @Override
    public HorizontalRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.foodhorizontal_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view.findViewById(R.id.iv_foodhorizontal);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.tv_foodhorizontal);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HorizontalRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mImg.setImageBitmap(foods.get(i).getFoodPhoto());
        viewHolder.mTxt.setText(foods.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
