package com.example.testdemolib.entity.respons;


/**
 * User: LiuGq
 * Date: 2017/11/6
 * Time: 19:50
 */

public class BankTypeModel extends BaseRepModel {
    private String bankName;
    private String cardType;


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

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", bankName='" + bankName + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
