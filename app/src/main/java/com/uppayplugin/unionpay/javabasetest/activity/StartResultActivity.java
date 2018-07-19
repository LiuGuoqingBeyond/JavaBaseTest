package com.uppayplugin.unionpay.javabasetest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uppayplugin.unionpay.javabasetest.R;

public class StartResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_result);
    }

    public void click(View view) {
        Intent intent = new Intent();
        intent.putExtra("itentdata", "这个是回传的值");
        setResult(2, intent);
        finish();
    }
}
