package com.halfcigarette.dietitian.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseActivity;
import com.halfcigarette.dietitian.base.CustomBaseAdapter;
import com.halfcigarette.dietitian.beans.Families;
import com.halfcigarette.dietitian.ui.dialog.AddPersonDialog;
import com.halfcigarette.dietitian.utils.Logger;

import java.util.ArrayList;

/**
 * Created by kiplening on 16-6-2.
 */
public class InfoManager extends BaseActivity{
    private ListView familiesList;
    private Button addFamily;
    private TextView back;

    private ArrayList<Families> listItems;
    private Context context;


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.info_manager_back:
                    finish();
                    break;
                case R.id.add_family_bt:
                    AddPersonDialog dialog = new AddPersonDialog(context);
                    dialog.show();
                    break;
            }
        }
    };
    @Override
    public void initContentView() throws InterruptedException {
        setContentView(R.layout.info_manage_activity);
    }

    @Override
    public void initView() {
        familiesList = (ListView) findViewById(R.id.info_display);
        addFamily = (Button) findViewById(R.id.add_family_bt);
        back = (TextView) findViewById(R.id.info_manager_back);

        familiesList.setAdapter(new FamiliesAdapter(getApplicationContext(), listItems));
        addFamily.setOnClickListener(listener);
        back.setOnClickListener(listener);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Logger.i(this.getLocalClassName() + " ondestory");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getBaseContext();
    }

    private class FamiliesAdapter extends CustomBaseAdapter {
        private TextView nickname,age, gender,height,weight;

        public FamiliesAdapter(Context context, ArrayList<Families> listItems) {
            super(context);
            setData(listItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = View.inflate(getContext(), R.layout.family_info_list_items, null);
            nickname = (TextView) item.findViewById(R.id.family_nickname);
            gender = (TextView) item.findViewById(R.id.family_sex);
            age = (TextView) item.findViewById(R.id.family_age);
            height = (TextView) item.findViewById(R.id.family_height);
            weight = (TextView) item.findViewById(R.id.family_weight);

            Families data = (Families) getData().get(position);
            nickname.setText(data.getNickName());
            age.setText(data.getAge());
            gender.setText(data.getGender());
            height.setText(data.getHeight());
            weight.setText(data.getWeight());
            return item;
        }
    }
}
