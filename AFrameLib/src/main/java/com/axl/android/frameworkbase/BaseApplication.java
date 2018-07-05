package com.axl.android.frameworkbase;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/10/26
 * Time: 15:41
 */

public class BaseApplication extends MultiDexApplication {

    private static Context sAppContext;
    private static String sCacheDir;

    // 当前系统是否设置为英文系统
    private static boolean englishSystem = false;

    // 是否开通支付地区
    private static boolean openPayCardCountry = false;

    // 支付成功
    private static boolean paySuccess = false;

    //选择绑卡入口
    private static boolean choseBindCardExit = false;

    //是否选择虚拟卡交易
    private static boolean choseVirtualCard = false;

    //解绑卡是否成功
    private static boolean boundCard = false;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            sCacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            sCacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    public static String getAppCacheDir() {
        return sCacheDir;
    }


    public static Context getAppContext() {
        return sAppContext;
    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static void setEnglishSystem(boolean englishSystem) {
        BaseApplication.englishSystem = englishSystem;
    }

    public static boolean isEnglishSystem() {
        return englishSystem;
    }

    public static void setOpenPayCardCountry(boolean openPayCardCountry) {
        BaseApplication.openPayCardCountry = openPayCardCountry;
    }

    public static boolean isOpenPayCardCountry() {
        return openPayCardCountry;
    }

    public static boolean isPaySuccess() {
        return paySuccess;
    }

    public static void setPaySuccess(boolean paySuccess) {
        BaseApplication.paySuccess = paySuccess;
    }

    public static boolean isChoseBindCardExit(){
        return choseBindCardExit;
    }

    public static void setChoseBindExit(boolean choseBindCardExit){
        BaseApplication.choseBindCardExit = choseBindCardExit;
    }

    public static boolean isChoseVirtualCard(){
        return choseVirtualCard;
    }

    public static void setChoseVirtualCard(boolean choseVirtualCard){
        BaseApplication.choseVirtualCard = choseVirtualCard;
    }

    public static boolean isBoundCard(){
        return boundCard;
    }

    public static void setBoundCard(boolean boundCard){
        BaseApplication.boundCard = boundCard;
    }
}
