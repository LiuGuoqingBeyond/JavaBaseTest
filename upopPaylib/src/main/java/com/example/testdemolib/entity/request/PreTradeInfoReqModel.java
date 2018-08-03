package com.example.testdemolib.entity.request;

/**
 * 国际码消费请求模型
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/10 09:56
 * @email：zhanghuan@xinguodu.com
 */
public class PreTradeInfoReqModel {

    public String emvcode;
    public String countryCode;
    public String mobile;
    public String signature;
    public String sessionID;
    public String txnType;
    public String language;
}
