package com.uppayplugin.unionpay.javabasetes.entity.response;

/**
 * User: LiuGuoqing
 * Data: 2018/9/17 0017.
 */

public class LoginRepModel {
    public String returnCode;
    public String returnMsg;
    public String bizCode;

    @Override
    public String toString() {
        return "LoginRepModel{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", bizCode='" + bizCode + '\'' +
                '}';
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
}
