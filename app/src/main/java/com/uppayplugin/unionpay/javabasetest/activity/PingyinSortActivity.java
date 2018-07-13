package com.uppayplugin.unionpay.javabasetest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.entity.response.BankCardInfo;
import com.uppayplugin.unionpay.javabasetest.utils.PinyinSortUtils;

import java.util.ArrayList;
import java.util.List;

public class PingyinSortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingyin_sort);

        List<BankCardInfo> list = new ArrayList<>();
        BankCardInfo bankCardInfo = new BankCardInfo();
        bankCardInfo.setTestName(" 我");
        list.add(bankCardInfo);
        BankCardInfo bankCardInfo1 = new BankCardInfo();
        bankCardInfo1.setTestName("爱");
        list.add(bankCardInfo1);
        BankCardInfo bankCardInfo2 = new BankCardInfo();
        bankCardInfo2.setTestName("wo");
        list.add(bankCardInfo2);
        BankCardInfo bankCardInfo3 = new BankCardInfo();
        bankCardInfo3.setTestName("5");
        list.add(bankCardInfo3);
        BankCardInfo bankCardInfo4 = new BankCardInfo();
        bankCardInfo4.setTestName("bi");
        list.add(bankCardInfo4);

        List<BankCardInfo> bankCardInfos = new ArrayList<>();
        bankCardInfos = PinyinSortUtils.getInstance(PingyinSortActivity.this).listToSortByName(list);
        for (int i = 0; i < bankCardInfos.size(); i++) {
            Log.d("排序为：",list.get(i).getTestName());
        }
    }
}
