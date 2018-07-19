package com.example.testdemolib.entity.respons;

/**
 * User: chengxingjiang
 * Date: 2017/11/10
 * Time: 下午8:15
 */

public class TradeRecordMonthRequest {

    private String countryCode;
    private String mobile;
    private String sessionID;
    private String txnType;
    private String startDate;
    private String userKey;
    private String orderId;
    private String version;
    private String beginNum;
    private String queryRows;
    public String signature;
    public String language;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBeginNum() {
        return beginNum;
    }

    public void setBeginNum(String beginNum) {
        this.beginNum = beginNum;
    }

    public String getQueryRows() {
        return queryRows;
    }

    public void setQueryRows(String queryRows) {
        this.queryRows = queryRows;
    }


}
