package com.uppayplugin.unionpay.javabasetes.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/19 0019.
 */

public class BankBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bankName : 中国工商银行
         */

        private String bankName;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }
}
