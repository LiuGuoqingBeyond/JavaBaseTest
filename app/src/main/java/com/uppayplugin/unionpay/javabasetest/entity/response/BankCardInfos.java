package com.uppayplugin.unionpay.javabasetest.entity.response;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class BankCardInfos {
    public String testName;

    @Override
    public String toString() {
        return "BankCardInfos{" +
                "testName='" + testName + '\'' +
                '}';
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
