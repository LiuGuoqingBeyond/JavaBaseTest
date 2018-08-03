package com.example.testdemolib.entity.respons;

import com.example.testdemolib.entity.respons.BaseRepModel;

/**
 * User: LiuGq
 * Date: 2017/11/15
 * Time: 12:56
 */

public class GetBoundCardMobileRespons extends BaseRepModel {
    private String orderId;

    @Override
    public String toString() {
        return "{" +
                "msg='" + msg + '\'' +
                ", orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
