package com.example.testdemolib.entity.respons;


import com.example.testdemolib.entity.respons.BaseRepModel;

/**
 * User: LiuGq
 * Date: 2017/11/15
 * Time: 22:47
 */

public class GetTemsessionReqModel extends BaseRepModel {
    private String sessionID;
    private String securityKey;

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

    @Override
    public String toString() {
        return "{" +
                "msg='" + msg + '\'' +
                ", sessionID='" + sessionID + '\'' +
                ", securityKey='" + securityKey + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
