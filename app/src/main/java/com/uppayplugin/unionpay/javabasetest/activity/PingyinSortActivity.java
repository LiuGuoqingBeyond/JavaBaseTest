package com.uppayplugin.unionpay.javabasetest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.entity.response.BankCardInfos;
import com.uppayplugin.unionpay.javabasetest.utils.PinyinSortUtils;

import java.util.ArrayList;
import java.util.List;

public class PingyinSortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingyin_sort);

        List<BankCardInfos> list = new ArrayList<>();
        BankCardInfos bankCardInfo = new BankCardInfos();
        bankCardInfo.setTestName(" 我");
        list.add(bankCardInfo);
        BankCardInfos bankCardInfo1 = new BankCardInfos();
        bankCardInfo1.setTestName("爱");
        list.add(bankCardInfo1);
        BankCardInfos bankCardInfo2 = new BankCardInfos();
        bankCardInfo2.setTestName("wo");
        list.add(bankCardInfo2);
        BankCardInfos bankCardInfo3 = new BankCardInfos();
        bankCardInfo3.setTestName("5");
        list.add(bankCardInfo3);
        BankCardInfos bankCardInfo4 = new BankCardInfos();
        bankCardInfo4.setTestName("bi");
        list.add(bankCardInfo4);

        List<BankCardInfos> bankCardInfos = new ArrayList<>();
        bankCardInfos = PinyinSortUtils.getInstance(PingyinSortActivity.this).listToSortByName(list);
        for (int i = 0; i < bankCardInfos.size(); i++) {
            Log.d("排序为：",list.get(i).getTestName());
        }
    }
}
