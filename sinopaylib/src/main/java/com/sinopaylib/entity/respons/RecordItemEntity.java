package com.sinopaylib.entity.respons;

/**
 * @project：ElongGradleTaskDemo-master
 * @author：- octopus on 2017/12/7 20:34
 * @email：zhanghuan@xinguodu.com
 */
public class RecordItemEntity {

    // 收款方式
    String recMethod = "";
    // 产品金额
    String txnAmt = "";
    // 交易时间
    String transTime = "";
    // 订单号id
    String orderID = "";
    // 结算金额
    String settAmt = "";

    public String getRecMethod() {
        return recMethod;
    }

    public void setRecMethod(String recMethod) {
        this.recMethod = recMethod;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getSettAmt() {
        return settAmt;
    }

    public void setSettAmt(String settAmt) {
        this.settAmt = settAmt;
    }
}
