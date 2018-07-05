package com.uppayplugin.unionpay.javabasetest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetest.R;

import java.util.ArrayList;
import java.util.List;

public class CheckedTextViewActivity extends AppCompatActivity {

    private ArrayAdapter arrayAdapter;
    private Spinner spBankList;

    /**
     * 这个只是spinner的应用，CheckedTextView的应用在CheckedTextViewTwo
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_text_view);

        spBankList = findViewById(R.id.spBankList);

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
        arrayAdapter = new ArrayAdapter(CheckedTextViewActivity.this, R.layout.item_spinner, banks);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBankList.setAdapter(arrayAdapter);
        spBankList.setSelection(0);//默认选中第一个
        spBankList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CheckedTextViewActivity.this, position+""+arrayAdapter.getItem(position), Toast.LENGTH_SHORT).show();//获取到position及对应的item文字
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
