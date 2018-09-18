package com.uppayplugin.unionpay.javabasetes.bean;

/**
 * User: LiuGuoqing
 * Data: 2018/9/14 0014.
 */

public class BankTestBean {
    public String bankName;

    @Override
    public String toString() {
        return "BankTestBean{" +
                "bankName='" + bankName + '\'' +
                '}';
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
