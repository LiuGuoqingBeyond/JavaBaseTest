package com.uppayplugin.unionpay.javabasetest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetest.R;

public class Aactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aactivity);
        TextView clickA = findViewById(R.id.clickA);
        clickA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Aactivity.this,MainActivity.class);
                intent.putExtra("标识","收到标识了吗");
//                startActivity(intent);
                setResult(55,intent);
                finish();
//                Intent intent = new Intent(Aactivity.this,Bactivity.class);
//                startActivity(intent);
            }
        });

    }
}
