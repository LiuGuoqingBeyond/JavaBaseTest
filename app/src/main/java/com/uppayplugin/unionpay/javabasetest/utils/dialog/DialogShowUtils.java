package com.uppayplugin.unionpay.javabasetest.utils.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.utils.PreferencesUtil;

/**
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/14 15:54
 * @email：zhanghuan@xinguodu.com
 */
public class DialogShowUtils {

    // 用户本地配置文件
    private static PreferencesUtil prefs;

    /**
     * 重新登录提示
     *
     * @param context
     * @param msg
     */
    public static void showReloginDailog(Context context, String msg) {
        prefs = new PreferencesUtil(context);
        DialogUtils.Builder pwd_builder = new DialogUtils.Builder(context);
        pwd_builder.setTitle(context.getString(R.string.text_tips));
        pwd_builder.setMessage(context.getString(R.string.dialogutils_relogin));
        pwd_builder.setCerternButton(context.getString(R.string.text_confirm_msg), (dialog13, which) -> {
            dialog13.dismiss();
            prefs.writePrefs(Constant.PREFES_AUTO, "");
            /*Intent intent1 = new Intent(context, LoginActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
            ((Activity) context).finish();*/
            ToastUtils.showLong("这个是重新录提示");
        });
        pwd_builder.setCanceledOnTouchOutside(false);
        pwd_builder.create().show();
    }

    /**
     * 显示普通弹出框
     *
     * @param context
     * @param msg
     */
    public static void showCommonDialog(Context context, String msg) {
        OneOrTwoBtnDialogUtil.getDialogInstance().dialogToShow(context, "", msg, context.getString(R.string.text_confirm_msg), false, (dialogInterface, i) -> {
            if (i == 1){
                dialogInterface.dismiss();
            }
        });
    }

    /**
     * 显示普通弹出框，并退出当前Activity
     *
     * @param context
     * @param msg
     */
    public static void showCommonDialogWithFinish(Context context, String msg) {
        OneOrTwoBtnDialogUtil.getDialogInstance().dialogToShow(context, "", msg, context.getString(R.string.text_confirm_msg), "", false, (dialogInterface, i) -> {
            if (i == 1){
                dialogInterface.dismiss();
                ((Activity) context).finish();
            }
        });
    }

    /**
     * 显示提醒弹出框
     *
     * @param context    上下文
     * @param title      提示标题
     * @param message    提示内容
     * @param btnMessage 按钮文字
     * @param onTouchCancelable    触摸是否可以取消弹窗
     */
    public static void showNoticeDialog(Context context, String title, String message,
                                        String btnMessage, boolean onTouchCancelable) {
        OneOrTwoBtnDialogUtil.getDialogInstance().dialogToShow(context, title, message, btnMessage, onTouchCancelable, (dialogInterface, i) -> {
            if (i == 1){
                dialogInterface.dismiss();
            }
        });
    }

    /**
     * 显示提醒弹出框,确定后退出当前页面
     * @param context    上下文
     * @param title      提示标题
     * @param message    提示内容
     * @param btnMessage 按钮文字
     * @param onTouchCancelable    触摸是否可以取消弹窗
     */
    public static void showNoticeDialogWithFinish(Context context, String title, String message,
                                        String btnMessage, boolean onTouchCancelable) {
        OneOrTwoBtnDialogUtil.getDialogInstance().dialogToShow(context, title, message, btnMessage, onTouchCancelable, (dialogInterface, i) -> {
            if (i == 1){
                dialogInterface.dismiss();
                ((Activity) context).finish();
            }
        });
    }
}
