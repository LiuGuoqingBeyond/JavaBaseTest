package com.uppayplugin.unionpay.javabasetes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.uppayplugin.unionpay.javabasetes.R;

public class ChoseMoneyActivity extends AppCompatActivity {

    private EditText settleMoney;
    private ImageView img1,img2;
    private Button btnConfirm;
    private String flag = "1";
    private String exitflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle_money);

        settleMoney = findViewById(R.id.settle_money);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        btnConfirm = findViewById(R.id.btn_confirm);
        exitflag = getIntent().getStringExtra("exitflag");

        img1.setBackgroundResource(R.drawable.icon_select_highlight);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundResource(R.drawable.icon_select_highlight);
                img2.setBackgroundResource(R.drawable.icon_select_normal);
                flag = "1";
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundResource(R.drawable.icon_select_normal);
                img2.setBackgroundResource(R.drawable.icon_select_highlight);
                flag = "2";
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoseMoneyActivity.this,SecondActivity.class);
                intent.putExtra("flag",flag);
                intent.putExtra("settleMoney",settleMoney.getText().toString());
                intent.putExtra("exitflag",exitflag);
                startActivity(intent);
                finish();
            }
        });
    }
}
