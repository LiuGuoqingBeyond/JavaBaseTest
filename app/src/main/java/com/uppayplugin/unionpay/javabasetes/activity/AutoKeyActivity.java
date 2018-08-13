package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.PublicMethodUtils;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutoKeyActivity extends AppCompatActivity {

    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.click)
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_key);

        ButterKnife.bind(AutoKeyActivity.this);

        /*etText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);//输入邮箱的Type
        etText .setImeOptions(EditorInfo.IME_ACTION_DONE);*/

        //不让其输入中文
        PublicMethodUtils.setEdNoChinaese(etText);
        //不让其输入量字符"&"
        PublicMethodUtils.setEditTextInhibitInputSpeChat(etText);
    }
    @OnClick({R.id.click})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.click:
                ToastUtils.showLong(etText.getText().toString());
                break;
        }
    }
}
