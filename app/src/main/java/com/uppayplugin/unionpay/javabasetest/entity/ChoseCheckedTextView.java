package com.uppayplugin.unionpay.javabasetest.entity;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public class ChoseCheckedTextView {
    public String bankName;

    @Override
    public String toString() {
        return "ChoseCheckedTextView{" +
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
