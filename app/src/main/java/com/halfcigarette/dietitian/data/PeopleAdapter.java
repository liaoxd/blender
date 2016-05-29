package com.halfcigarette.dietitian.data;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.CustomBaseAdapter;
import com.halfcigarette.dietitian.beans.People;

import java.util.List;

/**
 * Created by dongweihang on 2015/11/25.
 */
public class PeopleAdapter extends CustomBaseAdapter {
    private TextView tv_people;
    private ImageView iv_people;

    public PeopleAdapter(Context context, List<People> peopleList) {
        super(context);
        setData(peopleList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = View.inflate(getContext(), R.layout.people_item, null);

        tv_people = (TextView) item.findViewById(R.id.tv_people);
        iv_people = (ImageView) item.findViewById(R.id.iv_people);

        People people = (People) getData().get(position);

        if (people.isSex()) {
            iv_people.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.man));
        } else {
            iv_people.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.women));
        }

        tv_people.setText(people.getAge() + "");

        return item;
    }
}
