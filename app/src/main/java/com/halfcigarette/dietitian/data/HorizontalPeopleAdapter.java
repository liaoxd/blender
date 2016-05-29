package com.halfcigarette.dietitian.data;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.beans.NutritionElement;
import com.halfcigarette.dietitian.beans.People;
import com.halfcigarette.dietitian.utils.Logger;

import java.util.List;

/**
 * Created by dongweihang on 2015/11/25.
 */
public class HorizontalPeopleAdapter extends RecyclerView.Adapter<HorizontalPeopleAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<People> peopleList;
    private Resources resources;
    private Context context;
    private NutritionAdapter nutritionAdapter;

    public HorizontalPeopleAdapter(LayoutInflater layoutInflater, List<People> peopleList, Resources resources, Context context, NutritionAdapter nutritionAdapter) {
        this.layoutInflater = layoutInflater;
        this.peopleList = peopleList;
        this.resources = resources;
        this.context = context;
        this.nutritionAdapter = nutritionAdapter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View item) {
            super(item);
            item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("删除");
                    builder.setMessage("确认删除吗？");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            StaticData.peopleList.remove(getPosition());
                            notifyDataSetChanged();
                            //条图检测
                            if (!StaticData.IsNoPeople()) {
                                StaticData.nutritionList.clear();
                                StaticData.nutritionList.add(new NutritionElement("卡路里", StaticData.getfood_reliang(), StaticData.getstd_reliang()));
                                StaticData.nutritionList.add(new NutritionElement("蛋白质", StaticData.getfood_danbai(), StaticData.getstd_danbai()));
                                StaticData.nutritionList.add(new NutritionElement("脂肪", StaticData.getfood_zhifang(), StaticData.getstd_zhifang()));
                                StaticData.nutritionList.add(new NutritionElement("维生素", StaticData.getfood_wei(), StaticData.getstd_wei()));
                                StaticData.nutritionList.add(new NutritionElement("矿物质", StaticData.getfood_kuang(), StaticData.getstd_kuang()));
                                StaticData.nutritionList.add(new NutritionElement("膳食纤维", StaticData.getfood_xianwei(), StaticData.getstd_xianwei()));
                                nutritionAdapter.notifyDataSetChanged();
                            }
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                    Logger.e("longclick  " + getPosition());
                    return false;
                }
            });
        }

        ImageView mImg;
        TextView mTxt;
        TextView mTxt1;


    }

    @Override
    public HorizontalPeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final View view = layoutInflater.inflate(R.layout.people_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view.findViewById(R.id.iv_people);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.tv_people);
        viewHolder.mTxt1 = (TextView) view.findViewById(R.id.tv_people1);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HorizontalPeopleAdapter.ViewHolder holder, int position) {
        People people = peopleList.get(position);
        if (people.isSex()) {
            holder.mImg.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.man));
        } else {
            holder.mImg.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.women));
        }
        holder.mTxt.setText(people.getAge() + " 岁");
        holder.mTxt1.setText(people.getWeight() + " 公斤");


    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }
}
