package com.uppayplugin.unionpay.javabasetes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;

public class SecondActivity extends AppCompatActivity {

    private TextView editText, textView;
    private Button button, btn2, btn1;
    private String flag = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText = findViewById(R.id.evText);
        button = findViewById(R.id.btnText);
        textView = findViewById(R.id.text);
        btn2 = findViewById(R.id.btn2);
        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = "1";
                Intent intent = new Intent(SecondActivity.this, ChoseMoneyActivity.class);
                intent.putExtra("exitflag", flag);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = "2";
                Intent intent = new Intent(SecondActivity.this, ChoseMoneyActivity.class);
                intent.putExtra("exitflag", flag);
                startActivity(intent);
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra("key", editText.getText().toString());
//                setResult(RESULT_OK, intent);
//                finish();
//                Toast.makeText(SecondActivity.this,NumberChange.getInstance().number(Integer.parseInt(editText.getText().toString()))+"",Toast.LENGTH_LONG).show();
//                editText.
//                editText.setText(NumberChange.getInstance().number(Integer.parseInt(textView.getText().toString()))+"");


        //注意，这个方法只能100作为中介值，这样是按比例设置去了。按金额设置是：设置一个值，用户以那个值为中间值，大于或小于中间值。
//        NumberChange.getInstance().number(Integer.parseInt(getIntent().getStringExtra("settleMoney")))

        //点击上下两个，进到设置金额页     两个flag标识判断....注意上边那句话，所以，
        if (getIntent().getStringExtra("exitflag") != null) {
            if (getIntent().getStringExtra("exitflag").equals("1")) {
                if (getIntent().getStringExtra("flag").equals("1")) {
                    editText.setText(String.format(getString(R.string.settle_money_small), getIntent().getStringExtra("settleMoney")));
                    textView.setText(String.format(getString(R.string.settle_money_big), getIntent().getStringExtra("settleMoney")));
                } else {
                    editText.setText(String.format(getString(R.string.settle_money_big), getIntent().getStringExtra("settleMoney")));
                    textView.setText(String.format(getString(R.string.settle_money_small), getIntent().getStringExtra("settleMoney")));
                }
            } else if (getIntent().getStringExtra("exitflag").equals("2")) {
                if (getIntent().getStringExtra("flag").equals("1")) {
                    textView.setText(String.format(getString(R.string.settle_money_small), getIntent().getStringExtra("settleMoney")));
                    editText.setText(String.format(getString(R.string.settle_money_big), getIntent().getStringExtra("settleMoney")));
                } else {
                    textView.setText(String.format(getString(R.string.settle_money_big), getIntent().getStringExtra("settleMoney")));
                    editText.setText(String.format(getString(R.string.settle_money_small), getIntent().getStringExtra("settleMoney")));
                }
            }
        }


//            }
//        });


//        List<Integer> list = new ArrayList<Integer>();
//        list.add(10);
//        list.add(20);
//        list.add(30);
//        list.add(40);
//        list.add(50);
//        list.add(60);
//        list.add(70);
//        list.add(80);
//        list.add(90);
//        list.add(100);
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, list);
//        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//        Spinner sp = findViewById(R.id.spinner1);
//        Spinner sp2 = findViewById(R.id.spinner2);
//
//        sp.setAdapter(adapter);
//        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            // parent： 为控件Spinner view：显示文字的TextView position：下拉选项的位置从0开始
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //EditText tvResult = findViewById(R.id.tvResult);
//                //获取Spinner控件的适配器
//                ArrayAdapter<Integer> adapter = (ArrayAdapter<Integer>) parent.getAdapter();
//                editText.setText(String.valueOf(adapter.getItem(position)));
////                editText.setText(NumberChange.getInstance().number(adapter.getItem(position))+"");
//            }
//
//            //没有选中时的处理
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
//
//        sp2.setAdapter(adapter);
//        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            // parent： 为控件Spinner view：显示文字的TextView position：下拉选项的位置从0开始
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //EditText tvResult = findViewById(R.id.tvResult);
//                //获取Spinner控件的适配器
//                ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
//                //textView.setText(adapter.getItem(position));
//            }
//
//            //没有选中时的处理
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
////        editText.setText(NumberChange.getInstance().number(Integer.parseInt(textView.getText().toString())));
//
//
////        textView.setText(NumberChange.getInstance().number(Integer.parseInt(editText.getText().toString())));
//        textView.setText(NumberChange.getInstance().number(Integer.parseInt(editText.getText().toString()))+"");
//    }
    }
}
