package com.uppayplugin.unionpay.javabasetes.entity.response;

/**
 * User: LiuGuoqing
 * Data: 2018/8/27 0027.
 */

public class BlueCountRepModel {
    public String blueName;
    public String blueAddress;

    @Override
    public String toString() {
        return "BlueCountRepModel{" +
                "blueName='" + blueName + '\'' +
                ", blueAddress='" + blueAddress + '\'' +
                '}';
    }

    public String getBlueName() {
        return blueName;
    }

    public void setBlueName(String blueName) {
        this.blueName = blueName;
    }

    public String getBlueAddress() {
        return blueAddress;
    }

    public void setBlueAddress(String blueAddress) {
        this.blueAddress = blueAddress;
    }
}
