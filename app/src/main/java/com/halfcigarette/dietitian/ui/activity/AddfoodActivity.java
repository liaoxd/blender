package com.halfcigarette.dietitian.ui.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseActivity;
import com.halfcigarette.dietitian.beans.Tags;
import com.halfcigarette.dietitian.data.JsonParser1;
import com.halfcigarette.dietitian.data.SimpleFoodAdapter;
import com.halfcigarette.dietitian.data.StaticData;
import com.halfcigarette.dietitian.ui.custom.TitleBar;
import com.halfcigarette.dietitian.utils.Logger;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;

public class AddfoodActivity extends BaseActivity {
    @Bind(R.id.addFoodTitleBar)
    TitleBar addFoodTitleBar;

    private ListView DetialFoodListView;
    private SimpleFoodAdapter simpleFoodAdapter;

    private ImageView iv_MSCForName;
    private ImageView iv_MSCForWeight;
    private EditText et_MSCForName;
    private EditText et_MSCForWeight;

    private Handler handler;
    private ClientThread clientThread;

    private static String TAG = AddfoodActivity.class.getSimpleName();
    // 引擎类型
//    private String mEngineType = SpeechConstant.TYPE_CLOUD;

    // 语音听写对象
//    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_addfood);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.addfoodtitle_bar);
    }

    @Override
    public void initView() {
        addFoodTitleBar.setTitle("膳食专家");

        iv_MSCForName = (ImageView) findViewById(R.id.iv_MSCForName);
        iv_MSCForWeight = (ImageView) findViewById(R.id.iv_MSCForWeight);
        et_MSCForName = (EditText) findViewById(R.id.et_MSCForName);
        et_MSCForWeight = (EditText) findViewById(R.id.et_MSCForWeight);

        DetialFoodListView = (ListView) findViewById(R.id.DetialFoodList);
        simpleFoodAdapter = new SimpleFoodAdapter(this.getApplicationContext(), StaticData.foodList);
        DetialFoodListView.setAdapter(simpleFoodAdapter);

        initMSC();

        iv_MSCForName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示听写对话框
//                mEngineType = SpeechConstant.TYPE_CLOUD;
                mIatDialog.setListener(mRecognizerDialogListener);
                mIatDialog.show();
                showToast(getString(R.string.text_begin));
            }
        });
        iv_MSCForWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示听写对话框
//                mEngineType = SpeechConstant.TYPE_CLOUD;
                mIatDialog.setListener(mRecognizerDialogListenerForWeight);
                mIatDialog.show();
                showToast(getString(R.string.text_begin));
            }
        });

        //用于测试（注意删除）
//        StaticData.foodList.add(getFoodFromHashMap("鸡肉", 300));
//        StaticData.foodList.add(getFoodFromHashMap("木耳", 100));
//        StaticData.foodList.add(getFoodFromHashMap("白菜", 300));
//        StaticData.foodList.add(getFoodFromHashMap("红豆", 200));
//        StaticData.foodList.add(getFoodFromHashMap("西兰花", 100));
//        StaticData.foodList.add(getFoodFromHashMap("海带", 100));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.i(this.getLocalClassName() + " onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i(this.getLocalClassName() + " onresume");

        handler = new Handler() //①
        {
            @Override
            public void handleMessage(Message msg) {
                // 如果消息来自于子线程
                if (msg.what == 0x123) {
                    // 将读取的内容追加显示在文本框中
                    String recivedmsg = ResolvePackge(msg.obj.toString());
                    String type_str = recivedmsg.substring(0, 2);
                    if (recivedmsg.substring(0, 2).equals("01")) {
                        String resut_str = recivedmsg.substring(2, recivedmsg.length());

                        et_MSCForWeight.setText(Integer.valueOf(resut_str).toString());

                    } else if (recivedmsg.substring(0, 2).equals("02")) {
//                        et_MSCForName.setText(recivedmsg.substring(2,recivedmsg.length()));
                        String resut_str = recivedmsg.substring(2, recivedmsg.length());

                        switch (Integer.valueOf(resut_str)) {
                            case 1:
                                et_MSCForName.setText("白萝卜");
                                break;
                            case 2:
                                et_MSCForName.setText("红椒");
                                break;
                            case 3:
                                et_MSCForName.setText("土豆");
                                break;
                            case 4:
                                et_MSCForName.setText("鸡蛋");
                                break;
                            case 5:
                                et_MSCForName.setText("青椒");
                                break;
                            case 6:
                                et_MSCForName.setText("胡萝卜");
                                break;
                            case 7:
                                et_MSCForName.setText("白菜");
                                break;
                            case 8:
                                et_MSCForName.setText("西兰花");
                                break;
                            case 9:
                                et_MSCForName.setText("生姜");
                                break;
                            case 10:
                                et_MSCForName.setText("番茄");
                                break;
                            default:
                                et_MSCForName.setText("");
                                break;


                        }
                        String foodName = et_MSCForName.getText().toString();
                        String foodWeight = et_MSCForWeight.getText().toString();
                        int foodweight = 0;

                        foodweight = Integer.parseInt(foodWeight);
                        StaticData.foodList.add(getFoodFromHashMap(foodName, foodweight));

                        simpleFoodAdapter.notifyDataSetChanged();


//                        et_MSCForName.setText(String.valueOf(resultvalue));
                    } else {
                        ;
                    }
                }
            }
        };
        clientThread = new ClientThread(handler);
        // 客户端启动ClientThread线程创建网络连接、读取来自服务器的数据
        new Thread(clientThread).start();
//        clientThread.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIatDialog.cancel();
        mIatDialog.destroy();
        finish();
        Logger.i(this.getLocalClassName() + " onpause");
    }

    //对收到的字符串解包
    private String ResolvePackge(String recivedstr) {
        String result = null;
        byte[] resultbyte;
        int framindex = recivedstr.indexOf("2015BF3B");
        String type_str = recivedstr.substring(8 + framindex, 10 + framindex);
        switch (type_str) {
            case "01": {     //接收到的是重量
                String value_len_str = recivedstr.substring(framindex + 14, framindex + 16);
                int value_len = Integer.valueOf(value_len_str);
                String value_str = recivedstr.substring(framindex + 16, framindex + 16 + value_len);
                result = "01" + value_str;
//                Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
            }
            break;
            case "02": {     //接收到的是图片识别结果
                String value_len_str = recivedstr.substring(framindex + 14, framindex + 16);
                int value_len = Integer.valueOf(value_len_str);
                String value_str = recivedstr.substring(framindex + 16, framindex + 16 + value_len);
                result = "02" + value_str;
//                Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return result;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.i(this.getLocalClassName() + " onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        Logger.i(this.getLocalClassName() + " ondestory");
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addfood, menu);
        return true;
    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.AddFoodConfirmButton: {
                String foodName = et_MSCForName.getText().toString();
                String foodWeight = et_MSCForWeight.getText().toString();
                int foodweight = 0;
                if (foodName == null || foodName.trim() == "") {
                    showToast("您没有输入食物名称");
                    return;
                }
                //这里还要加入判断是否为数字。
                if (foodWeight == null || foodWeight.trim() == "") {
                    showToast("您没有输入重量");
                    return;
                }
                try {
                    foodweight = Integer.parseInt(foodWeight);
                } catch (Exception e) {
                    showToast("您输入的不是数字");
                    return;
                }
                if (StaticData.foodHashMap.containsKey(foodName)) {
                    StaticData.foodList.add(getFoodFromHashMap(foodName, foodweight));
                    et_MSCForName.setText("");
                    et_MSCForWeight.setText("");
                    simpleFoodAdapter.notifyDataSetChanged();
                } else {
                    showToast("尚未录入这个样本");
                    return;
                }

            }
            break;
            case R.id.AddFoodClearButton: {
                StaticData.foodList.clear();
                simpleFoodAdapter.notifyDataSetChanged();
            }
            break;
        }
    }

    public void initMSC() {
        SpeechUtility.createUtility(this.getApplication(), "appid=" + "561f9f80");
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(this, mInitListener);

    }

    int ret = 0; // 函数调用返回值

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showToast("初始化失败，错误码：" + code);
            }
        }
    };

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            printResult(results);
        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            showToast(error.getPlainDescription(true));
        }

    };

    private void printResult(RecognizerResult results) {
        String text = JsonParser1.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        //去除标点符号
        String string = resultBuffer.toString();
        string = string.replaceAll("。", "");


        et_MSCForName.setText(string);
//        testMSCTextview.setText(text);
//        testMSCTextview.setSelection(testMSCTextview.length());
    }

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListenerForWeight = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            printResultForWeight(results);
        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            showToast(error.getPlainDescription(true));
        }

    };

    private void printResultForWeight(RecognizerResult results) {
        String text = JsonParser1.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        //去除标点符号
        String string = resultBuffer.toString();
//        string = string.replaceAll("。", "");
        String regEx = "[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(string);
        String result = new String();
        while (matcher.find()) {
            result += matcher.replaceAll("").trim();
        }
        showToast(result);
        et_MSCForWeight.setText(result);

//        et_MSCForName.setText(string);
//        testMSCTextview.setText(text);
//        testMSCTextview.setSelection(testMSCTextview.length());
    }

    public Tags.Food getFoodFromHashMap(String foodName, int foodWeight) {
        Tags.Food food = StaticData.foodHashMap.get(foodName);

        food.setWeight(foodWeight);

        food.setReliang(food.getReliang() * foodWeight / 100);
        food.setTanshui(food.getTanshui() * foodWeight / 100);
        food.setDanbai(food.getDanbai() * foodWeight / 100);
        food.setZhifang(food.getZhifang() * foodWeight / 100);
        food.setDangu(food.getDangu() * foodWeight / 100);
        food.setYesuan(food.getYesuan() * foodWeight / 100);
        food.setYansuan(food.getYansuan() * foodWeight / 100);
        food.setXianwei(food.getXianwei() * foodWeight / 100);
        food.setHuluobo(food.getHuluobo() * foodWeight / 100);
        food.setGai(food.getGai() * foodWeight / 100);
        food.setXin(food.getXin() * foodWeight / 100);
        food.setJia(food.getJia() * foodWeight / 100);
        food.setLin(food.getLin() * foodWeight / 100);
        food.setDian(food.getDian() * foodWeight / 100);
        food.setNa(food.getNa() * foodWeight / 100);
        food.setMei(food.getMei() * foodWeight / 100);
        food.setXi(food.getXi() * foodWeight / 100);
        food.setTie(food.getTie() * foodWeight / 100);
        food.setTong(food.getTong() * foodWeight / 100);
        food.setMeng(food.getMeng() * foodWeight / 100);
        food.setWeia(food.getWeia() * foodWeight / 100);
        food.setWeib(food.getWeib() * foodWeight / 100);
        food.setWeib2(food.getWeib2() * foodWeight / 100);
        food.setWeib12(food.getWeib12() * foodWeight / 100);
        food.setWeib6(food.getWeib6() * foodWeight / 100);
        food.setWeic(food.getWeic() * foodWeight / 100);
        food.setWeie(food.getWeie() * foodWeight / 100);
        food.setWeik(food.getWeik() * foodWeight / 100);
        food.setWeid(food.getWeid() * foodWeight / 100);

        food.setFoodPhoto(BitmapFactory.decodeResource(getResources(), StaticData.foodImageSource.get(foodName)));

        return food;
    }
}
