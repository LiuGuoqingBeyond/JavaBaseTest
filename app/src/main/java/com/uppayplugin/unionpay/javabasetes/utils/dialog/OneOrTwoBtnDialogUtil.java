package com.uppayplugin.unionpay.javabasetes.utils.dialog;

import android.content.Context;
import android.content.DialogInterface;

/**
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/14 15:54
 * @email：zhanghuan@xinguodu.com
 */
public class OneOrTwoBtnDialogUtil {

    private static OneOrTwoBtnDialogUtil instance;
    private OneOrTwoBtnDialogUtil() {}

    public interface OnListener{
        void listener(DialogInterface dialogInterface, int i);
    }

    public static OneOrTwoBtnDialogUtil getDialogInstance() {
        if(null == instance) {
            synchronized (OneOrTwoBtnDialogUtil.class){
                if(null == instance) {
                    instance = new OneOrTwoBtnDialogUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 显示提醒弹出框
     *
     * @param context           上下文
     * @param title             提示标题
     * @param message           提示内容
     * @param btnMessage        按钮文字
     * @param onTouchCancelable 触摸是否可以取消弹窗
     */
    public void dialogToShow(final Context context, String title, String message,
                             String btnMessage, boolean onTouchCancelable, final OneOrTwoBtnDialogUtil.OnListener listener) {
        TwinsBtnDialogUtil.Builder pwd_builder = new TwinsBtnDialogUtil.Builder(context);
        pwd_builder.setTitle(title);
        pwd_builder.setMessage(message);
        pwd_builder.setCerternButton(btnMessage, (dialogInterface, i) -> {
            // 在这里吧点击事件传出去
            listener.listener(dialogInterface, 1);
        });
        pwd_builder.setCanceledOnTouchOutside(onTouchCancelable);
        pwd_builder.create().show();
    }

    /**
     * 两个按钮
     *
     * @param context           上下文
     * @param title             提示标题
     * @param message           提示内容
     * @param btnMessage        按钮文字
     * @param onTouchCancelable 触摸是否可以取消弹窗
     */
    public void dialogToShow(final Context context, String title, String message,
                             String btnMessage, String btnRightMesssage, boolean onTouchCancelable, final OneOrTwoBtnDialogUtil.OnListener listener) {
        TwinsBtnDialogUtil.Builder  pwd_builder1 = new TwinsBtnDialogUtil.Builder(context);
        pwd_builder1.setTitle(title);
        pwd_builder1.setMessage(message);
        pwd_builder1.setCerternButton(btnMessage, (dialogInterface, i) -> listener.listener(dialogInterface,1));
        pwd_builder1.setTwoButton(btnRightMesssage, (dialogInterface, i) -> listener.listener(dialogInterface,2));
        pwd_builder1.setCanceledOnTouchOutside(onTouchCancelable);
        pwd_builder1.create().show();
    }
}
