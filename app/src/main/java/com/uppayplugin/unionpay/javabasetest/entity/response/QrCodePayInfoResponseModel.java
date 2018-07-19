package com.uppayplugin.unionpay.javabasetest.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/10 10:01
 * @email：zhanghuan@xinguodu.com
 */
public class QrCodePayInfoResponseModel extends BaseRepModel implements Serializable,Parcelable {

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
    // 扣款金额
    private String billingAmt;
    // 扣款币种
    private String billingCurr;
    //交易类型
    private String merType;
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

    private String orderTime;
    // 交易时间
    private String transTime;

    // 交易订单号(银联二维码)
    private String upopOrderId;

    private String cardNum;
    private String cardType;

    private String paymentStatus;
    private String rejectionReason;
    private String paymentMsg;
    private String originalCurr;
    private String originalAmt;
    private String serialNumber;
    private String merCode;

    private String couponDes;

    // 抵扣金额-->转换成CNY币种后的金额
    private String billingCurrTxnAmt;
    // 积分抵扣金额 --->转换成CNY币种后的金额
    private String txnCurrCreditAmt;
    // 优惠金额 -->转换成CNY币种后的金额
    private String billingCurrdiscountAmt;

    // emv code
    private String emvCode;

    private String billingRate;

    protected QrCodePayInfoResponseModel(Parcel in) {
        merId = in.readString();
        orderId = in.readString();
        queryId = in.readString();
        txnTime = in.readString();
        payTime = in.readString();
        txnAmt = in.readString();
        txnCurr = in.readString();
        billingAmt = in.readString();
        billingCurr = in.readString();
        merType = in.readString();
        merName = in.readString();
        termCode = in.readString();
        bankName = in.readString();
        discountValue = in.readString();
        discountCurr = in.readString();
        creditAmt = in.readString();
        creditCurr = in.readString();
        useCredit = in.readString();
        orderTime = in.readString();
        transTime = in.readString();
        upopOrderId = in.readString();
        cardNum = in.readString();
        cardType = in.readString();
        paymentStatus = in.readString();
        rejectionReason = in.readString();
        paymentMsg = in.readString();
        originalCurr = in.readString();
        originalAmt = in.readString();
        serialNumber = in.readString();
        merCode = in.readString();
        couponDes = in.readString();
        billingCurrTxnAmt = in.readString();
        txnCurrCreditAmt = in.readString();
        billingCurrdiscountAmt = in.readString();
        emvCode = in.readString();
        billingRate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(merId);
        dest.writeString(orderId);
        dest.writeString(queryId);
        dest.writeString(txnTime);
        dest.writeString(payTime);
        dest.writeString(txnAmt);
        dest.writeString(txnCurr);
        dest.writeString(billingAmt);
        dest.writeString(billingCurr);
        dest.writeString(merType);
        dest.writeString(merName);
        dest.writeString(termCode);
        dest.writeString(bankName);
        dest.writeString(discountValue);
        dest.writeString(discountCurr);
        dest.writeString(creditAmt);
        dest.writeString(creditCurr);
        dest.writeString(useCredit);
        dest.writeString(orderTime);
        dest.writeString(transTime);
        dest.writeString(upopOrderId);
        dest.writeString(cardNum);
        dest.writeString(cardType);
        dest.writeString(paymentStatus);
        dest.writeString(rejectionReason);
        dest.writeString(paymentMsg);
        dest.writeString(originalCurr);
        dest.writeString(originalAmt);
        dest.writeString(serialNumber);
        dest.writeString(merCode);
        dest.writeString(couponDes);
        dest.writeString(billingCurrTxnAmt);
        dest.writeString(txnCurrCreditAmt);
        dest.writeString(billingCurrdiscountAmt);
        dest.writeString(emvCode);
        dest.writeString(billingRate);
    }


    public static final Creator<QrCodePayInfoResponseModel> CREATOR = new Creator<QrCodePayInfoResponseModel>() {
        @Override
        public QrCodePayInfoResponseModel createFromParcel(Parcel in) {
            return new QrCodePayInfoResponseModel(in);
        }

        @Override
        public QrCodePayInfoResponseModel[] newArray(int size) {
            return new QrCodePayInfoResponseModel[size];
        }
    };

    @Override
    public String toString() {
        return "QrCodePayInfoResponseModel{" +
                "merId='" + merId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", queryId='" + queryId + '\'' +
                ", txnTime='" + txnTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", txnAmt='" + txnAmt + '\'' +
                ", txnCurr='" + txnCurr + '\'' +
                ", billingAmt='" + billingAmt + '\'' +
                ", billingCurr='" + billingCurr + '\'' +
                ", merType='" + merType + '\'' +
                ", merName='" + merName + '\'' +
                ", termCode='" + termCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", discountValue='" + discountValue + '\'' +
                ", discountCurr='" + discountCurr + '\'' +
                ", creditAmt='" + creditAmt + '\'' +
                ", creditCurr='" + creditCurr + '\'' +
                ", useCredit='" + useCredit + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", transTime='" + transTime + '\'' +
                ", upopOrderId='" + upopOrderId + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", cardType='" + cardType + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", paymentMsg='" + paymentMsg + '\'' +
                ", originalCurr='" + originalCurr + '\'' +
                ", originalAmt='" + originalAmt + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", merCode='" + merCode + '\'' +
                ", couponDes='" + couponDes + '\'' +
                ", billingCurrTxnAmt='" + billingCurrTxnAmt + '\'' +
                ", txnCurrCreditAmt='" + txnCurrCreditAmt + '\'' +
                ", billingCurrdiscountAmt'" + billingCurrdiscountAmt + '\'' +
                ", emvCode'" + emvCode  + '\'' +
                ", billingRate'" + billingRate + '\'' +
                '}';
    }

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

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
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

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getUpopOrderId() {
        return upopOrderId;
    }

    public void setUpopOrderId(String upopOrderId) {
        this.upopOrderId = upopOrderId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNumber(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getPaymentMsg() {
        return paymentMsg;
    }

    public void setPaymentMsg(String paymentMsg) {
        this.paymentMsg = paymentMsg;
    }

    public String getOriginalCurr() {
        return originalCurr;
    }

    public void setOriginalCurr(String originalCurr) {
        this.originalCurr = originalCurr;
    }

    public String getOriginalAmt() {
        return originalAmt;
    }

    public void setOriginalAmt(String originalAmt) {
        this.originalAmt = originalAmt;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setCouponDes(String couponDes) {
        this.couponDes = couponDes;
    }

    public String getCouponDes() {
        return couponDes;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getBillingCurrTxnAmt() {
        return billingCurrTxnAmt;
    }

    public void setBillingCurrTxnAmt(String billingCurrTxnAmt) {
        this.billingCurrTxnAmt = billingCurrTxnAmt;
    }

    public String getTxnCurrCreditAmt() {
        return txnCurrCreditAmt;
    }

    public void setTxnCurrCreditAmt(String txnCurrCreditAmt) {
        this.txnCurrCreditAmt = txnCurrCreditAmt;
    }

    public String getBillingCurrdiscountAmt() {
        return billingCurrdiscountAmt;
    }

    public void setBillingCurrdiscountAmt(String billingCurrdiscountAmt) {
        this.billingCurrdiscountAmt = billingCurrdiscountAmt;
    }

    public String getEmvCode() {
        return emvCode;
    }

    public String getBillingRate() {
        return billingRate;
    }

    public void setBillingRate(String billingRate) {
        this.billingRate = billingRate;
    }
}

