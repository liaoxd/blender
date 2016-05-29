package com.halfcigarette.dietitian.ui.fragment;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseFragment;
import com.halfcigarette.dietitian.beans.Tags;
import com.halfcigarette.dietitian.data.HorizontalPeopleAdapter;
import com.halfcigarette.dietitian.data.NutritionAdapter;
import com.halfcigarette.dietitian.beans.NutritionElement;
import com.halfcigarette.dietitian.data.StaticData;
import com.halfcigarette.dietitian.ui.custom.TitleBar;
import com.halfcigarette.dietitian.ui.dialog.AddPersonDialog;
import com.halfcigarette.dietitian.ui.dialog.RecordAnalyzeDialog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends BaseFragment {
    @Bind(R.id.record_title_bar)
    TitleBar record_title_bar;

    private View mLayoutView;
    private PieChart pieChart;
    private ListView recordElementListView;

    private TextView tv_runtime;
    private TextView tv_cycletime;
    private TextView tv_swimtime;
    private TextView tv_badmintontime;

    private ButtonRectangle recordAnalyzeButton;
    private ButtonFloat bt_addpersondialog;

    private NutritionAdapter nutritionAdapter;

    private RecyclerView rv_horizontalpeople;
    private HorizontalPeopleAdapter horizontalpeopleAdapter;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_record;
    }

    @Override
    public void initView() {
        record_title_bar.setTitle("膳食专家");

        Field[] fields;
        //食物数据转换成营养数据
        try {
            fields = Class.forName("com.halfcigarette.dietitian.beans.Tags.Food").getDeclaredFields();
            for (Field field : fields) {
                StaticData.nutritionHashMap.put(field.getName(), 0.0);
            }

            for (Tags.Food food : StaticData.foodList) {
                HashMap<String, Double> hashMap = food.GetNutritionHashMap();
                for (Field field : fields) {
                    if (hashMap.containsKey(field.getName())) {
                        Double num = StaticData.nutritionHashMap.get(field.getName()) + hashMap.get(field.getName());
                        StaticData.nutritionHashMap.put(field.getName(), num);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StaticData.nutritionList.add(new NutritionElement("卡路里", 0, 50));
        StaticData.nutritionList.add(new NutritionElement("蛋白质", 0, 100));
        StaticData.nutritionList.add(new NutritionElement("脂肪", 0, 100));
        StaticData.nutritionList.add(new NutritionElement("维生素", 0, 100));
        StaticData.nutritionList.add(new NutritionElement("矿物质", 0, 100));
        StaticData.nutritionList.add(new NutritionElement("膳食纤维", 0, 100));
//        StaticData.nutritionList.add(new NutritionElement("胆固醇", 100));


        recordElementListView = (ListView) mLayoutView.findViewById(R.id.recordElementListView);
        nutritionAdapter = new NutritionAdapter(mLayoutView.getContext(), StaticData.nutritionList);
        recordElementListView.setAdapter(nutritionAdapter);
        nutritionAdapter.notifyDataSetChanged();
        recordAnalyzeButton = (ButtonRectangle) mLayoutView.findViewById(R.id.recordAnalyzeButton);
        bt_addpersondialog = (ButtonFloat) mLayoutView.findViewById(R.id.bt_addpersondialog);
        tv_runtime = (TextView) mLayoutView.findViewById(R.id.tv_runtime);
        tv_cycletime = (TextView) mLayoutView.findViewById(R.id.tv_cycletime);
        tv_swimtime = (TextView) mLayoutView.findViewById(R.id.tv_swimtime);
        tv_badmintontime = (TextView) mLayoutView.findViewById(R.id.tv_badmintontime);

        pieChart = (PieChart) mLayoutView.findViewById(R.id.myTestPieChart);
        PieData mdata = getPieData(4);
        showChart(pieChart, mdata);

        rv_horizontalpeople = (RecyclerView) mLayoutView.findViewById(R.id.rv_horizontalpeople);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_horizontalpeople.setLayoutManager(linearLayoutManager);
        horizontalpeopleAdapter = new HorizontalPeopleAdapter(getBaseActivity().getLayoutInflater(), StaticData.peopleList, getResources(), getBaseActivity(), nutritionAdapter);
        rv_horizontalpeople.setAdapter(horizontalpeopleAdapter);

    }

    @Override
    public void initTitleBar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayoutView = getCreateView(inflater, container);
        ButterKnife.bind(this, mLayoutView);
        initView();

        recordAnalyzeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                double radio_reliang = StaticData.getfood_reliang() / StaticData.getstd_reliang();
//                double radio_danbai = StaticData.getfood_danbai() / StaticData.getstd_danbai();
//                double radio_zhifang = StaticData.getfood_zhifang() / StaticData.getstd_zhifang();
//                double radio_wei = StaticData.getfood_wei() / StaticData.getstd_wei();
//                double radio_kuang = StaticData.getfood_kuang() / StaticData.getstd_kuang();
//                double radio_xianwei = StaticData.getfood_xianwei() / StaticData.getstd_xianwei();
                if (StaticData.nutritionList.size() != 0) {
                    double radio_reliang = StaticData.nutritionList.get(0).getContent() / StaticData.nutritionList.get(0).getStandard();
                    double radio_danbai = StaticData.nutritionList.get(1).getContent() / StaticData.nutritionList.get(1).getStandard();
                    double radio_zhifang = StaticData.nutritionList.get(2).getContent() / StaticData.nutritionList.get(2).getStandard();
                    double radio_wei = StaticData.nutritionList.get(3).getContent() / StaticData.nutritionList.get(3).getStandard();
                    double radio_kuang = StaticData.nutritionList.get(4).getContent() / StaticData.nutritionList.get(4).getStandard();
                    double radio_xianwei = StaticData.nutritionList.get(5).getContent() / StaticData.nutritionList.get(5).getStandard();

                    ArrayList<String> str_lack = new ArrayList<String>();
                    ArrayList<String> str_lackserious = new ArrayList<String>();
                    ArrayList<String> str_more = new ArrayList<String>();
                    ArrayList<String> strings_more = new ArrayList<String>();
                    ArrayList<String> strings_lack = new ArrayList<String>();
                    strings_more.clear();
                    strings_lack.clear();

                    if (0.3 < radio_reliang && radio_reliang < 0.5) {
                        str_lack.add("卡路里 ");
                        strings_lack.add("吃些糕点补充能量");
                    } else if (radio_reliang > 1.6) {
                        str_more.add("卡路里");
                        strings_more.add("多多运动，避免长膘");
                    }

                    if (0.4 < radio_danbai && radio_danbai < 0.7) {
                        str_lack.add("蛋白质");
                        strings_lack.add("喝一杯牛奶");
                    } else if (radio_danbai <= 0.4) {
                        str_lackserious.add("蛋白质");
                        strings_lack.add("喝一大杯牛奶或吃两个鸡蛋");
                    } else if (radio_danbai > 1.5) {
                        str_more.add("蛋白质");
                        strings_more.add("喝一杯酸奶帮助消化");
                    }

                    if (0.3 < radio_zhifang && radio_zhifang < 0.5) {
                        str_lack.add("脂肪");
                    } else if (radio_zhifang <= 0.3) {
                        str_lackserious.add("脂肪");
                    } else if (radio_zhifang > 1.3) {
                        str_more.add("脂肪");
                        strings_more.add("多喝绿茶，帮助分解胆固醇");
                    }

                    if (0.3 < radio_wei && radio_wei < 0.7) {
                        str_lack.add("维生素");
                        strings_lack.add("适量吃些水果");
                    } else if (radio_wei <= 0.3) {
                        str_lackserious.add("维生素");
                        strings_lack.add("多吃水果");
                    } else if (radio_wei > 2.2) {
                        str_more.add("维生素");
                    }

                    if (0.3 < radio_kuang && radio_kuang < 0.6) {
                        str_lack.add("矿物质");
                    } else if (radio_kuang <= 0.3) {
                        str_lackserious.add("矿物质");
                    } else if (radio_kuang > 1.5) {
                        str_more.add("矿物质");
                    }

                    if (0.3 < radio_xianwei && radio_xianwei < 0.7) {
                        str_lack.add("纤维素");
                        strings_lack.add("吃一片果蔬纤维素嚼片");
                    } else if (radio_xianwei <= 0.3) {
                        str_lackserious.add("纤维素");
                        strings_lack.add("吃一片果蔬纤维素嚼片");
                    } else if (radio_xianwei > 2.3) {
                        str_more.add("纤维素");
                    }
                    String analyzeStr = new String("");
                    String analyzeStr1 = new String("");
                    String analyzeStr2 = new String("");
                    String analyzeStr3 = new String("");
                    double analyzeNum = 0;
//                    analyzeNum = Math.abs(1 - radio_danbai) * 25 + Math.abs(1 - radio_wei) * 25 + Math.abs(1 - radio_xianwei) * 20
//                            + Math.abs(1 - radio_kuang) * 15 + Math.abs(1 - radio_zhifang) * 7.5 + Math.abs(1 - radio_reliang) * 7.5;

                    analyzeNum = getAnalyzeNum(radio_danbai) * 20 + getAnalyzeNum(radio_wei) * 25 + getAnalyzeNum(radio_xianwei) * 20 +
                            getAnalyzeNum(radio_kuang) * 15 + getAnalyzeNum(radio_zhifang) * 7.5 + getAnalyzeNum(radio_reliang) * 12.5;


                    analyzeStr = "本次晚餐，您";
                    if (str_lack.size() != 0) {
                        for (String s : str_lack) {
                            analyzeStr1 += s + "、";
                        }
                        analyzeStr1 = analyzeStr1.substring(0, analyzeStr1.length() - 1);
                        analyzeStr += analyzeStr1 + "摄入过少，";
                    }
                    if (str_lackserious.size() != 0) {
                        for (String s : str_lackserious) {
                            analyzeStr2 += s + "、";
                        }
                        analyzeStr2 = analyzeStr2.substring(0, analyzeStr2.length() - 1);
                        analyzeStr += analyzeStr2 + "严重缺乏，";
                    }
                    if (str_more.size() != 0) {
                        for (String s : str_more) {
                            analyzeStr3 += s + "、";
                        }
                        analyzeStr3 = analyzeStr3.substring(0, analyzeStr3.length() - 1);
                        analyzeStr += analyzeStr3 + "摄入过多，";
                    }
                    if (str_lack.size() == 0 && str_lackserious.size() == 0 && str_more.size() == 0) {
                        analyzeStr += "的膳食搭配非常合理，希望您再接再厉，合理膳食，健康生活。";
                    } else {
                        if (strings_lack.size() == 0 && strings_more.size() == 0) {
                            analyzeStr += "总体来说较为健康。";
                        } else {
                            analyzeStr += "建议您：\n";
                            ArrayList<String> arrayList = new ArrayList<String>();
                            for (String string : strings_lack) {
                                arrayList.add(string);
                            }
                            for (String string : strings_more) {
                                arrayList.add(string);
                            }
                            for (int i = 0; i < arrayList.size(); i++) {
                                analyzeStr += (i + 1) + "." + arrayList.get(i) + "\n";
                            }
                        }
                    }


                    RecordAnalyzeDialog dialog = new RecordAnalyzeDialog(mLayoutView.getContext(), analyzeStr, (int) analyzeNum);
                    dialog.show();
                }


            }
        });

        bt_addpersondialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPersonDialog dialog = new AddPersonDialog(mLayoutView.getContext());

                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        horizontalpeopleAdapter.notifyDataSetChanged();
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
                    }
                });
            }
        });

        return mLayoutView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.gc();
    }


    @Override
    public void onResume() {
        super.onResume();


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

        nutritionAdapter.notifyDataSetChanged();

        //运动时间刷新
        tv_runtime.setText("慢跑" + String.format("%.1f", StaticData.getfood_reliang() * 0.3 / 655) + "小时");
        tv_cycletime.setText("骑车" + String.format("%.1f", StaticData.getfood_reliang() * 0.3 / 455) + "小时");
        tv_swimtime.setText("游泳" + String.format("%.1f", StaticData.getfood_reliang() * 0.3 / 600) + "小时");
        tv_badmintontime.setText("羽毛球" + String.format("%.1f", StaticData.getfood_reliang() * 0.3 / 555) + "小时");

        //饼图检测
        PieData mdata = getPieData(4);
        StaticData.allCalorie = 0;
        for (Tags.Food food : StaticData.foodList) {
            StaticData.allCalorie += food.getReliang();
        }
        showChart(pieChart, mdata);
        horizontalpeopleAdapter.notifyDataSetChanged();
    }


    public double getAnalyzeNum(double num) {
        if (num < 1.1 && num > 0.9) {
            return 1;
        } else if (num < 1.3 && num > 0.7) {
            return 0.96;
        } else if (num < 1.5 && num > 0.5) {
            return 0.8;
        } else if (num < 1.8 && num > 0.2) {
            return 0.6;
        } else if (num < 2 && num > 0.1){
            return 0.4;
        }else {
            return 0.1;
        }
    }

    //标志位：判断是否有数据
    boolean pieChartHasData = false;

    /**
     * @param pieChart
     * @param pieData
     */
    private void showChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(60f);  //半径
        pieChart.setTransparentCircleRadius(70f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        pieChart.setDescription("食物营养分布");
        pieChart.setDescriptionTextSize(13);

        // mChart.setDrawYValues(true);
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

//      mChart.setOnAnimationListener(this);
        if (pieChartHasData) {
            pieChart.setCenterText("您的营养分配");  //饼状图中间的文字
        } else {
            pieChart.setCenterText("标准营养分配");  //饼状图中间的文字

        }
        pieChart.setCenterTextSize(14);

        //设置数据
        pieChart.setData(pieData);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = pieChart.getLegend();  //设置比例图
//        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);//最下边显示
//        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360);
    }

    private int getXindex(String string, ArrayList<String> xValues) {
        int index = 0;
        for (int i = 0; i < xValues.size(); i++) {
            if (string == xValues.get(i)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * @param count 分成几部分
     */
    private PieData getPieData(int count) {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容


        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        float quarterly1 = 0;
        float quarterly2 = 0;
        float quarterly3 = 0;
        float quarterly4 = 0;
        for (int i = 0; i < StaticData.foodList.size(); i++) {
            switch (StaticData.foodList.get(i).getAttribute()) {
                case (0): {
                    quarterly1 += StaticData.foodList.get(i).getWeight();
                }
                break;
                case (1): {
                    quarterly2 += StaticData.foodList.get(i).getWeight();
                }
                break;
                case (2): {
                    quarterly3 += StaticData.foodList.get(i).getWeight();
                }
                break;
                case (3): {
                    quarterly4 += StaticData.foodList.get(i).getWeight();
                }
                break;
                default:
                    break;
            }
        }

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
//        colors.add(Color.rgb(205, 205, 205));
//        colors.add(Color.rgb(114, 188, 223));
//        colors.add(Color.rgb(255, 123, 124));
//        colors.add(Color.rgb(57, 135, 200));

        colors.clear();

        if (quarterly1 != 0) {
            xValues.add("谷薯类");
            yValues.add(new Entry(quarterly1, getXindex("谷薯类", xValues)));
            colors.add(Color.rgb(248, 203, 86));     //谷薯类   f8cb56
        }
        if (quarterly2 != 0) {
            xValues.add("蔬果类");
            yValues.add(new Entry(quarterly2, getXindex("蔬果类", xValues)));
            colors.add(Color.rgb(111, 193, 69));     //蔬果类   6fc145

        }
        if (quarterly3 != 0) {
            xValues.add("鱼肉类");
            yValues.add(new Entry(quarterly3, getXindex("鱼肉类", xValues)));
            colors.add(Color.rgb(230, 86, 52));     //肉鱼蛋类  e65634

        }
        if (quarterly4 != 0) {
            xValues.add("奶豆类");
            yValues.add(new Entry(quarterly4, getXindex("奶豆类", xValues)));
            colors.add(Color.rgb(76, 183, 235));     //豆奶类   4cb7eb

        }

        //如果没有数据，那么显示标准的膳食宝塔的数据
        if (yValues.size() == 0) {
            pieChartHasData = false;
            yValues.add(new Entry(400, 0));
            yValues.add(new Entry(600, 1));
            yValues.add(new Entry(150, 2));
            yValues.add(new Entry(100, 3));

            colors.add(Color.rgb(248, 203, 86));     //谷薯类   f8cb56
            colors.add(Color.rgb(111, 193, 69));     //蔬果类   6fc145
            colors.add(Color.rgb(230, 86, 52));     //肉鱼蛋类  e65634
            colors.add(Color.rgb(76, 183, 235));     //豆奶类   4cb7eb

            String[] strings;
            strings = new String[]{"谷薯类", "蔬果类", "鱼肉类", "奶豆类", "油盐调料"};

            for (int i = 0; i < count; i++) {
//            xValues.add("Quarterly" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
                xValues.add(strings[i]);
            }

        } else {
            pieChartHasData = true;
        }

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "各类营养物"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离


        pieDataSet.setColors(colors);
        pieDataSet.setValueTextSize(12);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }

}
