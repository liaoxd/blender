package com.halfcigarette.dietitian.ui.dialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.beans.People;
import com.halfcigarette.dietitian.data.StaticData;
import com.halfcigarette.dietitian.webViewUtils.LoopView;
import com.halfcigarette.dietitian.webViewUtils.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dongweihang on 2015/11/25.
 */
public class AddPersonDialog extends AlertDialog {
    private Context context;

    private RelativeLayout rootview1;
    private RelativeLayout rootview2;
    private RelativeLayout rootview3;
    private ButtonRectangle bt_addperson_comfirm;
    private ButtonRectangle bt_addperson_cancel;

    TextView tv_persondialog_sex;
    TextView tv_persondialog_weight;
    TextView tv_persondialog_age;

    int index1;
    int index2;
    int index3;

    private RelativeLayout.LayoutParams layoutParams;

    public AddPersonDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View dialog = View.inflate(context, R.layout.dialog_recordaddperson, null);

        rootview1 = (RelativeLayout) dialog.findViewById(R.id.rootview1);
        rootview2 = (RelativeLayout) dialog.findViewById(R.id.rootview2);
        rootview3 = (RelativeLayout) dialog.findViewById(R.id.rootview3);

        bt_addperson_comfirm = (ButtonRectangle) dialog.findViewById(R.id.bt_addperson_comfirm);
        bt_addperson_cancel = (ButtonRectangle) dialog.findViewById(R.id.bt_addperson_cancel);

        tv_persondialog_sex = (TextView) dialog.findViewById(R.id.tv_persondialog_sex);
        tv_persondialog_weight = (TextView) dialog.findViewById(R.id.tv_persondialog_weight);
        tv_persondialog_age = (TextView) dialog.findViewById(R.id.tv_persondialog_age);

        final LoopView loopView1 = new LoopView(context);
        final LoopView loopView2 = new LoopView(context);
        final LoopView loopView3 = new LoopView(context);

        final ArrayList<String> list1 = new ArrayList<>();
        list1.add("男");
        list1.add("女");
        final ArrayList<String> list2 = new ArrayList<>();
        for (int i = 1; i < 75; i++) {
            list2.add(i * 2 + "公斤");
        }
        final ArrayList<String> list3 = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            list3.add(i + "岁");
        }


        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);


        //设置是否循环播放
        loopView1.setNotLoop();
        //滚动监听
        loopView1.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index1 = index;
                tv_persondialog_sex.setText(list1.get(index) + "");
            }
        });
        //设置原始数据
        loopView1.setItems(list1);
        //设置初始位置
        loopView1.setInitPosition(0);
        //设置字体大小
        loopView1.setTextSize(18);
        rootview1.addView(loopView1, layoutParams);

        //设置是否循环播放
        loopView2.setNotLoop();
        //滚动监听
        loopView2.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index2 = index;
                tv_persondialog_weight.setText(list2.get(index));
            }
        });
        //设置原始数据
        loopView2.setItems(list2);
        //设置初始位置
        loopView2.setInitPosition(25);
        //设置字体大小
        loopView2.setTextSize(15);
        loopView2.setViewPadding(0, 0, 0, 0);
        rootview2.addView(loopView2, layoutParams);

        //设置是否循环播放
        loopView3.setNotLoop();
        //滚动监听
        loopView3.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index3 = index;
                tv_persondialog_age.setText(list3.get(index));
            }
        });
        //设置原始数据
        loopView3.setItems(list3);
        //设置初始位置
        loopView3.setInitPosition(20);
        //设置字体大小
        loopView3.setTextSize(15);
        rootview3.addView(loopView3, layoutParams);

        bt_addperson_comfirm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                try {
                    boolean sex;
                    Integer age;
                    Integer weight;
                    sex = (list1.get(index1) == "男") ? true : false;
                    String regEx = "[^0-9]";
                    String result = "";
                    String string = list2.get(index2);
                    Pattern pattern = Pattern.compile(regEx);
                    Matcher matcher = pattern.matcher(string);
                    while (matcher.find()) {
                        result += matcher.replaceAll("").trim();
                    }
                    weight = Integer.parseInt(result);

                    string = new String(list3.get(index3));
                    result = "";
                    matcher = pattern.matcher(string);
                    while (matcher.find()) {
                        result += matcher.replaceAll("").trim();
                    }
                    age = Integer.parseInt(result);

                    StaticData.peopleList.add(new People(sex, age, weight));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                dismiss();
            }
        });

        bt_addperson_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setContentView(dialog);
    }
}
