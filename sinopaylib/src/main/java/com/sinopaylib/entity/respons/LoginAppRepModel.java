package com.sinopaylib.entity.respons;

/**
 * User: LiuGq
 * Date: 2017/11/15
 * Time: 23:05
 */

public class LoginAppRepModel extends BaseRepModel {
    private String sessionID;
    private String securityKey;
    private String fillingStatus;
    private String merName;
    private String merShortName;
    private String merID;
    private String terID;
    private String termCode;
    private String email;

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTerID() {
        return terID;
    }

    public void setTerID(String terID) {
        this.terID = terID;
    }

    public String getMerID() {
        return merID;
    }

    public void setMerID(String merID) {
        this.merID = merID;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public String getFillingStatus() {
        return fillingStatus;
    }

    public String getMerShortName() {
        return merShortName;
    }

    @Override
    public String toString() {
        return "LoginAppRepModel{" +
                "msg='" + msg + '\'' +
                ", sessionID='" + sessionID + '\'' +
                ", securityKey='" + securityKey + '\'' +
                ", status='" + status + '\'' +
                ", fillingStatus='" + fillingStatus + '\'' +
                ", merName='" + merName + '\'' +
                ", merShortName='" + merShortName + '\'' +
                ", merID='" + merID + '\'' +
                ", terID='" + terID + '\'' +
                ", termCode='" + termCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
