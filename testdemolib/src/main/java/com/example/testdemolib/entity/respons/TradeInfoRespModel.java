package com.example.testdemolib.entity.respons;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/12/20 11:14
 * @email：zhanghuan@xinguodu.com
 */
public class TradeInfoRespModel extends BaseRepModel implements Serializable,Parcelable {

    private static final long serialVersionUID = 4547075729347623034L;

    // 商户号
    private String merId;
    // 订单号
    private String orderId;
    // 流水号
    private String queryId;
    // 订单时间
    private String txnTime;
    // 订单支付时间
    private String payTime;
    // 交易金额
    private String txnAmt;
    // 交易币种
    private String txnCurr;
    // 商户名
    private String merName;
    // 终端号
    private String termCode;
    // 扣款银行
    private String bankName;
    // 优惠金额
    private String discountValue;
    // 优惠金额币种
    private String discountCurr;
    // 积分金额
    private String creditAmt;
    // 积分币种
    private String creditCurr;
    // 是否使用积分
    private String useCredit;
    // 银联Mcc
    private String mcc;
    // 交易时间
    private String transTime;
    // 交易订单号(银联二维码)
    private String upopOrderId;

    // 订单有效期
    public String payTimeout;
    // 扣账金额
    public String billingAmt;
    // 扣账币种
    public String billingCurr;
    // 扣账费率
    public String billingRate;
    // 国际编码
    public String sysareaId;

    protected TradeInfoRespModel(Parcel in) {
        msg = in.readString();
        status = in.readString();
        merId = in.readString();
        orderId = in.readString();
        queryId = in.readString();
        txnTime = in.readString();
        payTime = in.readString();
        txnAmt = in.readString();
        txnCurr = in.readString();
        merName = in.readString();
        termCode = in.readString();
        bankName = in.readString();
        discountValue = in.readString();
        discountCurr = in.readString();
        creditAmt = in.readString();
        creditCurr = in.readString();
        useCredit = in.readString();
        mcc = in.readString();
        transTime = in.readString();
        upopOrderId = in.readString();
        payTimeout = in.readString();
        billingAmt = in.readString();
        billingCurr = in.readString();
        billingRate = in.readString();
        sysareaId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(status);
        dest.writeString(merId);
        dest.writeString(orderId);
        dest.writeString(queryId);
        dest.writeString(txnTime);
        dest.writeString(payTime);
        dest.writeString(txnAmt);
        dest.writeString(txnCurr);
        dest.writeString(merName);
        dest.writeString(termCode);
        dest.writeString(bankName);
        dest.writeString(discountValue);
        dest.writeString(discountCurr);
        dest.writeString(creditAmt);
        dest.writeString(creditCurr);
        dest.writeString(useCredit);
        dest.writeString(mcc);
        dest.writeString(transTime);
        dest.writeString(upopOrderId);
        dest.writeString(payTimeout);
        dest.writeString(billingAmt);
        dest.writeString(billingCurr);
        dest.writeString(billingRate);
        dest.writeString(sysareaId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TradeInfoRespModel> CREATOR = new Creator<TradeInfoRespModel>() {
        @Override
        public TradeInfoRespModel createFromParcel(Parcel in) {
            return new TradeInfoRespModel(in);
        }

        @Override
        public TradeInfoRespModel[] newArray(int size) {
            return new TradeInfoRespModel[size];
        }
    };

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getTxnCurr() {
        return txnCurr;
    }

    public void setTxnCurr(String txnCurr) {
        this.txnCurr = txnCurr;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountCurr() {
        return discountCurr;
    }

    public void setDiscountCurr(String discountCurr) {
        this.discountCurr = discountCurr;
    }

    public String getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(String creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getCreditCurr() {
        return creditCurr;
    }

    public void setCreditCurr(String creditCurr) {
        this.creditCurr = creditCurr;
    }

    public String getUseCredit() {
        return useCredit;
    }

    public void setUseCredit(String useCredit) {
        this.useCredit = useCredit;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setUpopOrderId(String upopOrderId) {
        this.upopOrderId = upopOrderId;
    }

    public String getUpopOrderId() {
        return upopOrderId;
    }

    public String getPayTimeout() {
        return payTimeout;
    }

    public void setPayTimeout(String payTimeout) {
        this.payTimeout = payTimeout;
    }

    public String getBillingAmt() {
        return billingAmt;
    }

    public void setBillingAmt(String billingAmt) {
        this.billingAmt = billingAmt;
    }

    public String getBillingCurr() {
        return billingCurr;
    }

    public void setBillingCurr(String billingCurr) {
        this.billingCurr = billingCurr;
    }

    public String getBillingRate() {
        return billingRate;
    }

    public void setBillingRate(String billingRate) {
        this.billingRate = billingRate;
    }

    public String getSysareaId() {
        return sysareaId;
    }

    public void setSysareaId(String sysareaId) {
        this.sysareaId = sysareaId;
    }

    @Override
    public String toString() {
        return "{" +
                "msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                ", merId='" + merId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", queryId='" + queryId + '\'' +
                ", txnTime='" + txnTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", txnAmt='" + txnAmt + '\'' +
                ", txnCurr='" + txnCurr + '\'' +
                ", merName='" + merName + '\'' +
                ", termCode='" + termCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", discountValue='" + discountValue + '\'' +
                ", discountCurr='" + discountCurr + '\'' +
                ", creditAmt='" + creditAmt + '\'' +
                ", creditCurr='" + creditCurr + '\'' +
                ", useCredit='" + useCredit + '\'' +
                ", mcc='" + mcc + '\'' +
                ", transTime='" + transTime + '\'' +
                ", upopOrderId='" + upopOrderId + '\'' +
                ", payTimeout='" + payTimeout + '\'' +
                ", billingAmt='" + billingAmt + '\'' +
                ", billingCurr='" + billingCurr + '\'' +
                ", billingRate='" + billingRate + '\'' +
                ", sysareaId='" + sysareaId + '\'' +
                '}';
    }
}
