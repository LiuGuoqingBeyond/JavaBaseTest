package com.uppayplugin.unionpay.javabasetes.bean;

/**
 * User: LiuGuoqing
 * Data: 2018/9/13 0013.
 */

public class AuditTestBean {
    public String ShopName;
    public String ApplyName;
    public String MerNumber;
    public String AuditStatus;

    @Override
    public String toString() {
        return "AuditTestBean{" +
                "ShopName='" + ShopName + '\'' +
                ", ApplyName='" + ApplyName + '\'' +
                ", MerNumber='" + MerNumber + '\'' +
                ", AuditStatus='" + AuditStatus + '\'' +
                '}';
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getApplyName() {
        return ApplyName;
    }

    public void setApplyName(String applyName) {
        ApplyName = applyName;
    }

    public String getMerNumber() {
        return MerNumber;
    }

    public void setMerNumber(String merNumber) {
        MerNumber = merNumber;
    }

    public String getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        AuditStatus = auditStatus;
    }
}
