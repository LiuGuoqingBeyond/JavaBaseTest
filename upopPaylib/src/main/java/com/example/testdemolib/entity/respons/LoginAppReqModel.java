package com.example.testdemolib.entity.respons;


import com.example.testdemolib.entity.respons.BaseRepModel;

/**
 * User: LiuGq
 * Date: 2017/11/15
 * Time: 23:05
 */

public class LoginAppReqModel extends BaseRepModel {
    private String sessionID;
    private String securityKey;
    private String randomNo;
    private String upopTime;

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

    public String getRandomNo() {
        return randomNo;
    }

    public void setRandomNo(String randomNo) {
        this.randomNo = randomNo;
    }

    public String getUpopTime() {
        return upopTime;
    }

    public void setUpopTime(String upopTime) {
        this.upopTime = upopTime;
    }

    @Override
    public String toString() {
        return "{" +
                "msg='" + msg + '\'' +
                ", sessionID='" + sessionID + '\'' +
                ", securityKey='" + securityKey + '\'' +
                ", randomNo='" + randomNo + '\'' +
                ", upopTime='" + upopTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
