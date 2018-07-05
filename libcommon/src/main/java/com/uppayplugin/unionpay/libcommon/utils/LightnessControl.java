package com.uppayplugin.unionpay.libcommon.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.provider.Settings;
import android.provider.Settings.System;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;

public class LightnessControl {
    // 判断是否开启了自动亮度调节 
    public static boolean isAutoBrightness(Activity act) {
        boolean automicBrightness = false;
        ContentResolver aContentResolver = act.getContentResolver();
        try {
            automicBrightness = Settings.System.getInt(aContentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Exception e) {
            Logger.e("无法获取亮度");
        }
        return automicBrightness;
    }

    // 改变亮度
    public static void SetLightness(Activity act, int value) {
        try {
            System.putInt(act.getContentResolver(), System.SCREEN_BRIGHTNESS, value);
            WindowManager.LayoutParams lp = act.getWindow().getAttributes();
            lp.screenBrightness = (value <= 0 ? 1 : value) / 255f;
            act.getWindow().setAttributes(lp);
        } catch (Exception e) {
            Logger.e("无法改变亮度");
        }
    }


    // 获取亮度
    public static int GetLightness(Activity act) {
        return System.getInt(act.getContentResolver(), System.SCREEN_BRIGHTNESS, -1);
    }

    // 停止自动亮度调节
    public static void stopAutoBrightness(Activity activity) {
        try {
            Settings.System.putInt(activity.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        } catch (Exception e) {
            Logger.e("无法停止自动亮度调节");
        }


    }

    // 开启亮度自动调节
    public static void startAutoBrightness(Activity activity) {
        try {
            Settings.System.putInt(activity.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        } catch (Exception e) {
            Logger.e("无法开启亮度自动调节");
        }


    }
}