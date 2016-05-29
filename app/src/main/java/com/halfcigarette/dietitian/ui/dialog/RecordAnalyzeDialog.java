package com.halfcigarette.dietitian.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFlat;
import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.ui.custom.CountView;

/**
 * Created by dongweihang on 2015-12-01.
 */
public class RecordAnalyzeDialog extends Dialog {

    private Context context;

    private CountView tv_gradedialog_grade;
    private TextView tv_gradedialog_advise;
    private ButtonFlat bt_gradedialog_iknow;
    private ButtonFlat bt_gradedialog_share;

    private String analyzeStr;
    private int num;

    public RecordAnalyzeDialog(Context context, String analyzeStr, int num) {
        super(context);
        this.context = context;
        this.analyzeStr = analyzeStr;
        this.num = num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View dialog = View.inflate(context, R.layout.dialog_grade, null);

        tv_gradedialog_grade = (CountView) dialog.findViewById(R.id.tv_gradedialog_grade);
        tv_gradedialog_advise = (TextView) dialog.findViewById(R.id.tv_gradedialog_advise);
        bt_gradedialog_iknow = (ButtonFlat) dialog.findViewById(R.id.bt_gradedialog_iknow);
        bt_gradedialog_share = (ButtonFlat) dialog.findViewById(R.id.bt_gradedialog_share);

        tv_gradedialog_grade.showNumberWithAnimation(num);
        tv_gradedialog_advise.setText(analyzeStr);

        bt_gradedialog_iknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        bt_gradedialog_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        setContentView(dialog);
    }
}
