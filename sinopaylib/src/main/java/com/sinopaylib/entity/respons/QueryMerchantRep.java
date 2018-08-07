package com.sinopaylib.entity.respons;

/**
 * User: LiuGq
 * Date: 2017/12/7
 * Time: 11:49
 */

public class QueryMerchantRep extends BaseRepModel{

    public String country;
    public String merName;
    public String contact;
    public String phoneNumber;
    public String emvContractRate;
    public String upopContractRate;
    public String accountName;
    public String bankName;
    public String cardNum;
    public String branchNum;
    public String merCode;
    public String termCode;

    @Override
    public String toString() {
        return "QueryMerchantRep{" +
                "country='" + country + '\'' +
                ", merName='" + merName + '\'' +
                ", msg='" + msg + '\'' +
                ", contact='" + contact + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emvContractRate='" + emvContractRate + '\'' +
                ", upopContractRate='" + upopContractRate + '\'' +
                ", accountName='" + accountName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", branchNum='" + branchNum + '\'' +
                ", status='" + status + '\'' +
                ", status='" + merCode + '\'' +
                ", status='" + termCode + '\'' +
                '}';
    }
    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmvContractRate() {
        return emvContractRate;
    }

    public void setEmvContractRate(String emvContractRate) {
        this.emvContractRate = emvContractRate;
    }

    public String getUpopContractRate() {
        return upopContractRate;
    }

    public void setUpopContractRate(String upopContractRate) {
        this.upopContractRate = upopContractRate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }
}
