package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uppayplugin.unionpay.javabasetes.R;

public class HyalinizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyalinize);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HyalinizeActivity.this.finish();
            }
        }, 3000);
    }
}
