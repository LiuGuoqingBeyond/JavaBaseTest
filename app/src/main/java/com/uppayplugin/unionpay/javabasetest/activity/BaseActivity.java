package com.uppayplugin.unionpay.javabasetest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.utils.LoadingUtil;
import com.uppayplugin.unionpay.javabasetest.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetest.utils.language.LanguageUtil;
import com.uppayplugin.unionpay.javabasetest.utils.multilanguage.MultiLanguageUtils;
import com.uppayplugin.unionpay.javabasetest.view.BaseToolbar;

import butterknife.ButterKnife;

/**
 * Created by mrpanda on 4/19/17.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    // 遮罩层效果
    private LoadingUtil loading;

    protected View mView;

    protected BaseToolbar mToolbar;

    protected Gson mGson;

    // 初始化变量信息定义
    protected String mobile = "";
    protected String username = "";
    protected String password = "";
    protected String remember = "";
    protected String tempKey = "";
    protected String tempSessionId = "";
    protected String Key = "";
    protected String sessionID = "";
    protected String countryCode = "";
    protected String Latitude = "";//维度
    protected String Longitude = "";//精度
    protected String randomKey = "";
    private long touchTime = 0;
    protected String IMEI;
    protected String country = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // zhanghuan 设置更新系统语言
        MultiLanguageUtils.updateSystemLanguage();

        mGson = new Gson();
        if (initContentViewID() != -1) {
            mView = View.inflate(this, initContentViewID(), null);
            setContentView(mView);
            mToolbar = new BaseToolbar(mView, this);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
            ButterKnife.bind(this);
        }


        // 解决android socket编程出现的Caused by: android.os.NetworkOnMainThreadException错误
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //        MobclickAgent.setDebugMode(true);
//        MobclickAgent.updateOnlineConfig(this);
//        AnalyticsConfig.enableEncrypt(true);
        // 添加到Activity集合
        AppManager.getInstance().addActivity(this);
        //
        // 获取屏幕分辨率
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Constant.SCREEN_DENSITY = metrics.density;
        Constant.SCREEN_HEIGHT = metrics.heightPixels;
        Constant.SCREEN_WIDTH = metrics.widthPixels;

//
//        // 初始化用户信息
        PreferencesUtil prefes = new PreferencesUtil(this);
        countryCode = prefes.readPrefs(Constant.PREFES_COUNTRYCODE);
        country = prefes.readPrefs(Constant.PREFES_COUNTRY);

        countryCode = TextUtils.isEmpty(countryCode) ? "86" : countryCode;
        country = TextUtils.isEmpty(country) ? getString(R.string.text_china) : country;
        mobile = prefes.readPrefs(Constant.PREFES_MOBILE);
        username = prefes.readPrefs(Constant.PREFES_USERNAME);
        // password = DataEncryUtil.getDecryptPassWord(prefes.readPrefs(Constant.PREFES_PASSWORD));
        remember = prefes.readPrefs(Constant.PREFES_REMEMBER);
        tempKey = prefes.readPrefs(Constant.PREFES_TEMPKEY);
        tempSessionId = prefes.readPrefs(Constant.PREFES_TEMPSESSIONID);
        randomKey = prefes.readPrefs(Constant.PREFES_RANDOMKEY);
        Key = prefes.readPrefs(Constant.PREFES_KEY);
        sessionID = prefes.readPrefs(Constant.PREFES_SESSIONID);
        //设置IMEI

        IMEI = prefes.readPrefs(Constant.PREFES_IMEI_CODE);

    }

    /**
     * 网络未连接时，调用设置方法
     */
    protected void setNetwork() {
        // TODO Auto-generated method stub..

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.net_tips));
        builder.setMessage(getString(R.string.net_set));
        builder.setPositiveButton(getString(R.string.text_set), (dialog, which) -> {
            // TODO Auto-generated method stub
            Intent intent = null;
            intent = new Intent(
                    Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);
        });
        builder.setNegativeButton(getString(R.string.text_cancel), (dialog, which) -> {
            // TODO Auto-generated method stub
        });
        builder.create();
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeLoadingMask();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 键盘按键事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            // 指定Activity是退出提示
            if (this instanceof MoreActivity) {
                closeLoadingMask();
                long currentTime = System.currentTimeMillis();
                if ((currentTime - touchTime) >= 2000) {
                    Toast.makeText(BaseActivity.this, getString(R.string.press_twice_quit), Toast.LENGTH_SHORT).show();
                    touchTime = currentTime;
                } else {
                    //android.os.Process.killProcess(android.os.Process.myPid()) ;   //获取PID
                    // System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
                    //MobclickAgent.onKillProcess(this);
                    ActivityCollector.finsdhAll();
                    AppManager.getInstance().killAllActivity();
                    closeLoadingMask();
                    // ImageLoader.getInstance().clearMemoryCache();
                }
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    protected int initContentViewID() {
        return -1;
    }

    /**
     * 绑定控件id
     */
    protected abstract void findView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }


    /**
     * 通过Action启动Activity
     *
     * @param pAction
     */
    protected void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    /**
     * 通过Action启动Activity，并且含有Bundle数据
     *
     * @param pAction
     * @param pBundle
     */
    protected void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 加载遮罩层（无参数）
     */
    protected void addLoadingMask() {
        loading = new LoadingUtil(this);
        loading.show();
    }

    /**
     * 加载遮罩层（有参数）
     *
     * @param message      显示文字信息
     * @param cancelButton 显示Cancel按钮（true：显示 false：隐藏）
     */
    protected void addLoadingMask(String message, boolean cancelButton) {
        if (loading != null) {
            loading = null;
        }
        loading = new LoadingUtil(this, message);
        loading.setCancelButton(cancelButton);
        loading.show();
    }

    /**
     * 关闭遮罩层
     */
    protected void closeLoadingMask() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
            loading = null;
        }
    }

    //让输入密码框英文字体变为常规字体
    public static void EditTextDefault(EditText et){
        et.setTypeface(Typeface.DEFAULT);
        et.setTransformationMethod(new PasswordTransformationMethod());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        com.axl.android.frameworkbase.utils.PreferencesUtil preferencesUtil = new com.axl.android.frameworkbase.utils.PreferencesUtil(newBase);
        String selectedLanguage = preferencesUtil.readPrefs("language_selected");
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, selectedLanguage));
    }

}
