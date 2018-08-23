package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Impl.LoginAppRequest;
import com.example.testdemolib.entity.request.LoginAppModel;
import com.example.testdemolib.entity.respons.LoginAppReqModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.example.testdemolib.utils.RSACoder;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.Impl.TodoLoginRequestImpl;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.entity.request.TodoLoginRequest;
import com.uppayplugin.unionpay.javabasetes.entity.response.TodoLoginRep;
import com.whty.xzfpos.base.AppToolBarActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class TodoMainActivity extends AppToolBarActivity {
    @BindView(R.id.account)
    AutoCompleteTextView mAccount;
    @BindView(R.id.password)
    EditText mPassword;
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_todo_main;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
    @OnClick(R.id.login)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login:
                if (isNull()) {
                    //登陆接口
                    login(mAccount.getText().toString(),mPassword.getText().toString());
                }
                break;
        }
    }

    private void login(String account, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", account);
        map.put("password", password);

        TodoLoginRequestImpl.loginRequest((TodoLoginRequest) TransMapToBeanUtils.mapToBean(map, TodoLoginRequest.class))
                .subscribe(new ProgressSubscriber<TodoLoginRep>(mContext) {
                    @Override
                    protected void _onNext(TodoLoginRep todoLoginRep) {
                        Logger.e("app登陆返回=" + todoLoginRep.toString());
                    }

                    @Override
                    protected void _onError(String message) {
                    }
                });
    }

    /**
     * 表单判空
     */
    public boolean isNull(){
        if (TextUtils.isEmpty(mAccount.getText())) {
            mAccount.setError("请输入账号");
            mAccount.setFocusable(true);
            mAccount.setFocusableInTouchMode(true);
            mAccount.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(mPassword.getText())) {
            mPassword.setError("请输入密码");
            mPassword.setFocusable(true);
            mPassword.setFocusableInTouchMode(true);
            mPassword.requestFocus();
            return false;
        }
        return true;
    }
}
