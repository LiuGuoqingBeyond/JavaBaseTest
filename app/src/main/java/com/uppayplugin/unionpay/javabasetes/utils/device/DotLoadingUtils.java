package com.uppayplugin.unionpay.javabasetes.utils.device;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 小数点加载动画
 *
 * @project：xzfpos
 * @author：- Richard on 2017/11/28 17:29
 * @email：zhangh@qtopay.cn
 */
public class DotLoadingUtils {

    private static final int LOADING_TIMEOUT = 35 * 1000;
    private static TextView textView;
    private static int count = 0;

    private static DotLoadingUtils dotLoadingUtils;

    private DotLoadingUtils(){}

    public static DotLoadingUtils getInstance() {
        if(null == dotLoadingUtils) {
            dotLoadingUtils = new DotLoadingUtils();
        }
        return dotLoadingUtils;
    }

    public void loadingDotView(TextView tv) {
        if(null == tv) return;
        textView = tv;
        count = 0;
        timer.start();
    }

    public void cancleLoadingDotView() {
        if(null != timer) {
            timer.cancel();
        }
    }


    /** 搜索计时器 **/
    private static CountDownTimer timer = new CountDownTimer(LOADING_TIMEOUT,
            1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            count++;
            if(count > 3)  count = 0;
            String dotString = "";
            switch (count%3) {
                case 0:
                    dotString = "...";
                    break;
                case 1:
                    dotString = ".";
                    break;
                case 2:
                    dotString = "..";
                    break;
                default:
                    break;
            }
            textView.setText("search" + dotString);
        }

        @Override
        public void onFinish() {
            // 搜索超时
            textView.setText("search" + "...");
        }
    };

}
