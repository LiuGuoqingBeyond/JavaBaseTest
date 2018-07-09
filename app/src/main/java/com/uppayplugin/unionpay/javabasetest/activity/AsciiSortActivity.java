package com.uppayplugin.unionpay.javabasetest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.utils.SortUtils;

import java.util.ArrayList;
import java.util.List;

public class AsciiSortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascii_sort);

        List<String> list = new ArrayList<>();
        list.add("001");
        list.add("021");
        list.add("123");
        list.add("014");
        list.add("002");
        list.add("015");
        list.add("016");
        list.add("b01");
        list.add("a02");
        list.add("020");
        list.add("023");
        list.add("024351");
        list.add("023451");
        list.add("  我是宝哥");
        list.add("你是谁");
        List<String> listSort = SortUtils.getInstance(AsciiSortActivity.this).listSort(list);
        for (String string : listSort) {
            System.out.println(string);
        }
    }
}
