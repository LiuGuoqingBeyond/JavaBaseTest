package com.uppayplugin.unionpay.javabasetest.entity.response;

import android.text.TextUtils;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/9/30
 * Time: 11:14
 */
public class BaseRepModel {
    public String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status;

    public boolean isOk() {
        return TextUtils.equals(status, "0");
    }

    public boolean needLogin(){
        return TextUtils.equals(status, "168");
    }

    @Override
    public String toString() {
        return "BaseRepModel{" +
                "msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
