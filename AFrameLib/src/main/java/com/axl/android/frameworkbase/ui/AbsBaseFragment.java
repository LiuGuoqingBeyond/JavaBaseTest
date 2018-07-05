package com.axl.android.frameworkbase.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * User: chengxingjiang
 * Date: 2017/11/2
 * Time: 18:36
 */

public abstract class AbsBaseFragment extends RxFragment {

    protected Unbinder unbinder;
    protected View fragmentView;
    protected Context mContext;
    protected LayoutInflater mInflater;

    protected Gson mGson;

    /**
     * loading view controller
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mContext = getContext();
        mGson = new Gson();
        fragmentView = inflater.inflate(setContentView(), container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        initView();
        initData();
        return fragmentView;
    }

    protected abstract int setContentView();

    protected abstract void initView();

    protected abstract void initData();

    /********************************
     * 【跳转到其他界面】
     *******************************************/
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null, false);
    }

    public void openActivity(Class<?> pClass, boolean isFinish) {
        openActivity(pClass, null, isFinish);
    }

    public void openActivity(Class<?> pClass, Bundle pBundle) {
        openActivity(pClass, pBundle, false);
    }

    public void openActivity(Class<?> pClass, Bundle pBundle, boolean isFinish) {
        Intent intent = new Intent(getActivity(), pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }

        startActivity(intent);
        if (isFinish) {
            getActivity().finish();
        }
    }


    public void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    public void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }


    /**
     * show toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        //防止遮盖虚拟按键
        if (null != msg && !TextUtils.isEmpty(msg)) {
            showToastShort(msg);
        }
    }

    public void showToastLong(CharSequence str) {
        try {
            Snackbar.make(fragmentView, str, Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToastShort(CharSequence str) {
        try {
            Snackbar.make(fragmentView, str, Snackbar.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToastLong(int resId) {
        try {
            showToastLong(getString(resId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToastShort(int resId) {
        try {
            showToastShort(getString(resId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     */
    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if ((imm != null && imm.isActive()) && getActivity().getCurrentFocus() != null) {
            if (getActivity().getWindow() != null) {
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    //显示虚拟键盘
    public static void ShowKeyboard(View v) {
        Observable.just("")
                .delay(500, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    InputMethodManager inputManager =
                            (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputManager != null) {
                        inputManager.showSoftInput(v, 0);
                    }
                }, error -> Logger.e(error.getMessage()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //让输入密码框英文字体变为常规字体
    public static void EditTextDefault(EditText et){
        et.setTypeface(Typeface.DEFAULT);
        et.setTransformationMethod(new PasswordTransformationMethod());
    }
}
