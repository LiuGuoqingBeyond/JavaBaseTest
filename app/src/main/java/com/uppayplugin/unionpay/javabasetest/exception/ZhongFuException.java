package com.uppayplugin.unionpay.javabasetest.exception;

/**
 * Created by admin on 2017/8/29.
 */

public class ZhongFuException extends Exception {
    private String mMessage;
    public ZhongFuException(String message) {
        super(message);
        mMessage = message;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    @Override
    public String toString() {
        return "ZhongFuException{" +
                "mMessage='" + mMessage + '\'' +
                '}';
    }
}
