package com.uppayplugin.unionpay.javabasetest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.testdemolib.Impl.TestDemoImpl;
import com.example.testdemolib.Interface.TestDemoInterface;
import com.example.testdemolib.Listener.TestDemoListener;
import com.example.testdemolib.entity.respons.GetTemsessionReqModel;
import com.uppayplugin.unionpay.javabasetest.R;

import java.util.Map;
import java.util.TreeMap;

public class TestSdkActivity extends AppCompatActivity {

    private Button btnSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sdk);
        btnSdk = findViewById(R.id.btn_sdk);
        btnSdk.setOnClickListener(view -> {
            runOnUiThread(() -> {
                TestDemoInterface testDemoInterface = new TestDemoImpl();
                Map<String, String> map = new TreeMap<>();
                map.put("countryCode", "86");
                map.put("mobile", "17665232288");
                testDemoInterface.getMessage(TestSdkActivity.this, map, testDemoListener);
            });
        });
    }
    TestDemoListener testDemoListener = new TestDemoListener() {
        @Override
        public void _onNext(GetTemsessionReqModel getTemsessionReqModel) {
            Toast.makeText(TestSdkActivity.this, getTemsessionReqModel.msg, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void _onError(String s) {
            Toast.makeText(TestSdkActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    };
}
