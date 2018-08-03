package com.example.testdemolib.entity.respons;

/**
 * User: LiuGq
 * Date: 2017/11/7
 * Time: 11:30
 */

public class BankCardMobel {
    private String status;
    private String msg;
    private String bankName;
    private String cardType;
    private String cardNum;

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

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", bankName='" + bankName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardNum='" + cardNum + '\'' +
                '}';
    }
}
