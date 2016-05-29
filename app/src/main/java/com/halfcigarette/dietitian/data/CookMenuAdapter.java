package com.halfcigarette.dietitian.data;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.CustomBaseAdapter;
import com.halfcigarette.dietitian.beans.Cook;

import java.util.List;

/**
 * Created by dongweihang on 2015/11/23.
 */
public class CookMenuAdapter extends CustomBaseAdapter {

    private ImageView iv_cookmenu;
    private TextView tv_cookmenuname;
    private Resources resources;

    public CookMenuAdapter(Context context, List<Cook> cookList, Resources resources) {
        super(context);
        this.resources = resources;
        setData(cookList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(getContext(), R.layout.cookmenu_item, null);

        iv_cookmenu = (ImageView) view.findViewById(R.id.iv_cookmenu);
        tv_cookmenuname = (TextView) view.findViewById(R.id.tv_cookmenuname);

        Cook cook = (Cook) getData().get(position);

        iv_cookmenu.setImageBitmap(BitmapFactory.decodeResource(resources, cook.getSourceid()));
        tv_cookmenuname.setText(cook.getName());

        return view;
    }
}
