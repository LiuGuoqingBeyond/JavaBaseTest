package com.uppayplugin.unionpay.javabasetest.adapter;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mrpanda on 4/29/17.
 */

public class BankCardInfo implements Serializable, Parcelable {

    private static final long serialVersionUID = -4661789342585986897L;

    private String status;
    private String msg;
    private String serialNumber;
    private String bankName;
    private String cardType;
    private String cardId;
    private String cardNum;
    private String sysareaId;
    private String paymentOrder;
    // 000001 中付卡   000003 国际卡
    private String channelType;
    //这个是英文银行卡名称
    private String bankNameLog;

    // 预留手机号
    private String phoneNumber;

    // 当前卡余额状态
    private String cardStatus;
    //付款码付款月状态
    private String qrCardStatus;

    public String getQrCardStatus() {
        return qrCardStatus;
    }

    public void setQrCardStatus(String qrCardStatus) {
        this.qrCardStatus = qrCardStatus;
    }

    /*public OpenCountry getOpenCountry() {
        return openCountry;
    }

    public void setOpenCountry(OpenCountry openCountry) {
        this.openCountry = openCountry;
    }

    private OpenCountry openCountry;*/

    public String getBankNameLog() {
        return bankNameLog;
    }

    public void setBankNameLog(String bankNameLog) {
        this.bankNameLog = bankNameLog;
    }

    public String getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(String paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getSysareaId() {
        return sysareaId;
    }

    public void setSysareaId(String sysareaId) {
        this.sysareaId = sysareaId;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.msg);
        dest.writeString(this.serialNumber);
        dest.writeString(this.bankName);
        dest.writeString(this.cardType);
        dest.writeString(this.cardId);
        dest.writeString(this.cardNum);
        dest.writeString(this.sysareaId);
        dest.writeString(this.paymentOrder);
        dest.writeString(this.channelType);
        dest.writeString(this.bankNameLog);
        dest.writeString(this.phoneNumber);
//        dest.writeValue(this.openCountry);
        dest.writeValue(this.qrCardStatus);
    }

    public BankCardInfo() {
    }

    protected BankCardInfo(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.serialNumber = in.readString();
        this.bankName = in.readString();
        this.cardType = in.readString();
        this.cardId = in.readString();
        this.cardNum = in.readString();
        this.sysareaId = in.readString();
        this.paymentOrder = in.readString();
        this.channelType = in.readString();
        this.bankNameLog = in.readString();
        this.phoneNumber = in.readString();
        this.qrCardStatus = in.readString();
    }

    public static final Creator<BankCardInfo> CREATOR = new Creator<BankCardInfo>() {
        @Override
        public BankCardInfo createFromParcel(Parcel source) {
            return new BankCardInfo(source);
        }

        @Override
        public BankCardInfo[] newArray(int size) {
            return new BankCardInfo[size];
        }
    };


    @Override
    public String toString() {
        return "BankCardInfo{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardId='" + cardId + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", sysareaId='" + sysareaId + '\'' +
                ", paymentOrder='" + paymentOrder + '\'' +
                ", channelType='" + channelType + '\'' +
                ", bankNameLog='" + bankNameLog + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
//                ", openCountry=" + openCountry +
                ", qrCardStatus=" + qrCardStatus +
                '}';
    }
}
