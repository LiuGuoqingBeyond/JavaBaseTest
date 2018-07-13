package com.uppayplugin.unionpay.javabasetest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetest.Interface.CheckedTextViewInterface;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.adapter.MyListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CheckedTextViewTwoActivity extends Aactivity implements View.OnClickListener {
    private CheckedTextView ctv1;
    private CheckedTextView ctv2;
    private boolean ctv2Checked = false;
    public ListView listView;
    private CheckedTextView checkedTextViewMul1;
    private MyListViewAdapter myListViewAdapter;
    private Button button;
    private String messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_text_view_two);
        button = findViewById(R.id.btn_click);

        /*checkedTextViewMul1 = findViewById(R.id.checktv_title);

        View.OnClickListener checkedTextViewMulListenerRef = new View.OnClickListener() {
            public void onClick(View arg0) {
                CheckedTextView checkedTextView= ((CheckedTextView) arg0);
                checkedTextView.toggle();
            }
        };

        checkedTextViewMul1.setOnClickListener(checkedTextViewMulListenerRef);*/


        initWidget();
    }

    private void initWidget() {
        List<String> banks = new ArrayList<>();
        banks.add("选择银行");
        banks.add("中国工商银行");
        banks.add("中国建设银行");
        banks.add("中国银行");
        banks.add("中国农业银行");
        banks.add("民生银行");
        banks.add("中信银行");
        banks.add("平安银行");
        banks.add("华夏银行");
        banks.add("交通银行");
        banks.add("招商银行");
        banks.add("中国邮政储蓄银行");
        banks.add("兴业银行");
        banks.add("浦发银行");
        banks.add("深发银行");
        banks.add("广发银行");
        ctv1 = findViewById(R.id.main_ctv1);
        ctv1.setOnClickListener(this);
        ctv1.setChecked(true);// 设置为选中，不设置默认为不选中

        ctv2 = findViewById(R.id.main_ctv2);
        ctv2.setOnClickListener(this);
        // 更改图标
        ctv2.setCheckMarkDrawable(android.R.drawable.arrow_down_float);

        listView = findViewById(R.id.main_lv);
        myListViewAdapter = new MyListViewAdapter(CheckedTextViewTwoActivity.this,banks,checkedTextViewInterface);
        listView.setAdapter(myListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CheckedTextViewTwoActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ctv1.isChecked()){
                    Toast.makeText(CheckedTextViewTwoActivity.this, "选中了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v == ctv1) {
            ctv1.toggle();// 反转状态
        } else if (v == ctv2) {
            ctv2Checked = !ctv2Checked;
            changeCtv2State();
        }
    }

    private void changeCtv2State() {
        if (ctv2Checked) {
            ctv2.setCheckMarkDrawable(android.R.drawable.arrow_up_float);
        } else {
            ctv2.setCheckMarkDrawable(android.R.drawable.arrow_down_float);
        }
    }

    CheckedTextViewInterface checkedTextViewInterface = new CheckedTextViewInterface() {
        @Override
        public void getisCheckedMessage(String message) {
            Toast.makeText(CheckedTextViewTwoActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

}
