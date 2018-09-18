package com.uppayplugin.unionpay.javabasetes.bean;

/**
 * User: LiuGuoqing
 * Data: 2018/9/13 0013.
 */

public class TradeTestBean {
    public String merNumber;
    public String tradeMoney;
    public String tradeFastPay;
    public String tradeTime;
    public String merName;
    public String tradestatus;

    @Override
    public String toString() {
        return "TradeTestBean{" +
                "merNumber='" + merNumber + '\'' +
                ", tradeMoney='" + tradeMoney + '\'' +
                ", tradeFastPay='" + tradeFastPay + '\'' +
                ", tradeTime='" + tradeTime + '\'' +
                ", merName='" + merName + '\'' +
                ", tradestatus='" + tradestatus + '\'' +
                '}';
    }

    public String getMerNumber() {
        return merNumber;
    }

    public void setMerNumber(String merNumber) {
        this.merNumber = merNumber;
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getTradeFastPay() {
        return tradeFastPay;
    }

    public void setTradeFastPay(String tradeFastPay) {
        this.tradeFastPay = tradeFastPay;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(String tradestatus) {
        this.tradestatus = tradestatus;
    }
}
