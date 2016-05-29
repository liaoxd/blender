package com.halfcigarette.dietitian.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.CustomBaseAdapter;
import com.halfcigarette.dietitian.beans.FindExample;

import java.util.List;

/**
 * Created by dongweihang on 2015/11/16.
 */
public class FindExampleAdapter extends CustomBaseAdapter {

    private ImageView iv_image_finditem;
    private TextView tv_title_finditem;
    private TextView tv_content_finditem;

    public FindExampleAdapter(Context context, List<FindExample> findExampleList) {
        super(context);
        setData(findExampleList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(getContext(), R.layout.find_example_item, null);

        iv_image_finditem = (ImageView) view.findViewById(R.id.iv_image_finditem);
        tv_title_finditem = (TextView) view.findViewById(R.id.tv_title_finditem);
        tv_content_finditem = (TextView) view.findViewById(R.id.tv_content_finditem);
//        int[] id = new int[4];
//        id[0] = R.drawable.find_exampleimage1;
//        id[1] = R.drawable.find_exampleimage2;
//        id[2] = R.drawable.find_exampleimage5;
//        id[3] = R.drawable.find_exampleimage6;

        FindExample findExample = (FindExample) getData().get(position);
        iv_image_finditem.setImageBitmap(findExample.getImage());
        tv_title_finditem.setText(findExample.getTitle());
        tv_content_finditem.setText(findExample.getContent());

        return view;
    }
}
