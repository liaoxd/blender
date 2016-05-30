package com.halfcigarette.dietitian.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.ui.custom.TitleBar;
import com.halfcigarette.dietitian.base.BaseActivity;
import com.halfcigarette.dietitian.ui.fragment.FoodFragment;

import butterknife.Bind;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ListView simplefoodLV;

    private PieChart pieChart;


    //listview 中要显示的蔬果
//    public static List<Food> simpleFoods;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void initContentView() {
        // 设置布局文件
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.maintitle_bar);
    }

    @Override
    public void initView() {
//        titleBar.setTitle("首页");
//        tvContent.setText("Hello MyAndroid!");
        //        tvContent = (TextView) findViewById(R.id.tv_content);
//        titleBar.setTitle("测试页面");
        FoodFragment foodFragment = new FoodFragment();
        simplefoodLV = (ListView) findViewById(R.id.simplefoodLV);
//        simplefoodLV = (ListView) foodFragment.FoodFragmentLayoutView.findViewById(R.id.fragfoodLV);

//        MainActivity.simpleFoods = new ArrayList<>();
//        Food simpleFood = new Food();
//        simpleFood.setName("豌豆");
//        simpleFood.setCalorie(300);
//        simpleFood.setFoodPhoto(BitmapFactory.decodeResource(getResources(), R.drawable.chandou));
//        MainActivity.simpleFoods.add(simpleFood);
//        MainActivity.simpleFoods.add(new Food("苹果", 150, BitmapFactory.decodeResource(getResources(), R.drawable.apple)));
//        MainActivity.simpleFoods.add(new Food("南瓜", 250, BitmapFactory.decodeResource(getResources(), R.drawable.nangua)));
//        MainActivity.simpleFoods.add(simpleFood);
//        MainActivity.simpleFoods.add(new Food("苹果", 150, BitmapFactory.decodeResource(getResources(), R.drawable.apple)));
//        MainActivity.simpleFoods.add(new Food("南瓜", 250, BitmapFactory.decodeResource(getResources(), R.drawable.nangua)));

//        simplefoodLV.setAdapter(new SimpleFoodAdapter(getApplicationContext(), simpleFoods));



    }

    @Override
    public void initPresenter() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.radioBT1):
//                tvContent.setText("radioBT1");
                showToast("radioBT1");
                pieChart = (PieChart) findViewById(R.id.mainPieChart);
                PieData mdata = getPieData(4, 100);
                showChart(pieChart, mdata);

                pieChart = (PieChart) findViewById(R.id.mainPieChart1);
                mdata = getPieData(5, 100);
                showChart(pieChart, mdata);
//                replaceFragment(R.id.foodLL, new FoodFragment());
                break;
            case (R.id.radioBT2):
//                tvContent.setText("radioBT2");
                showToast("radioBT2");
//                replaceFragment(R.id.foodLL, new BlankFragment());
                break;
            case (R.id.radioBT3):
//                tvContent.setText("radioBT3");
                showToast("radioBT3");
                break;
            case (R.id.radioBT4):
//                tvContent.setText("radioBT4");
                showToast("radioBT4");
                break;

        }
    }

    /**
     * @param pieChart
     * @param pieData
     */
    private void showChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(60f);  //半径
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        pieChart.setDescription("食物营养分布");

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

        pieChart.setCenterText("Quarterly Revenue");  //饼状图中间的文字

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

    /**
     * @param count 分成几部分
     * @param range
     */
    private PieData getPieData(int count, float range) {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容

        for (int i = 0; i < count; i++) {
            xValues.add("Quarterly" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        }

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        float quarterly1 = 14;
        float quarterly2 = 14;
        float quarterly3 = 34;
        float quarterly4 = 38;

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));
        yValues.add(new Entry(quarterly3, 2));
        yValues.add(new Entry(quarterly4, 3));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "Quarterly Revenue 2014"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }

}
