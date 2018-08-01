package com.sinopaylib.entity.respons;

/**
 * User: LiuGq
 * Date: 2017/11/15
 * Time: 22:47
 */

public class GetTemsessionRepModel extends BaseRepModel {
    private String sessionID;
    private String securityKey;

    @Override
    public String toString() {
        return "GetTemsessionRepModel{" +
                "msg='" + msg + '\'' +
                ", sessionID='" + sessionID + '\'' +
                ", securityKey='" + securityKey + '\'' +
                ", status='" + status + '\'' +
                '}';
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
}
