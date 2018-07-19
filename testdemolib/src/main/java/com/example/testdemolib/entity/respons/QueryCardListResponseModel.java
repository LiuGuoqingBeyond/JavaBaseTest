package com.example.testdemolib.entity.respons;

import java.util.ArrayList;

/**
 * User: LiuGq
 * Date: 2017/11/4
 * Time: 18:32
 */

public class QueryCardListResponseModel extends BaseRepModel {

    /**
     * status : 0
     * msg : 成功
     * list : [{"serialNumber":"1","bankName":"建设银行","cardType":"1","cardNum":"Yf25Gow+qRgOU4nXebs/469S/cTz3ULT","sysareaId":null,"cardId":"12345678912345678912345678912345","paymentOrder":null,"channelType":"000001"},{"serialNumber":"2","bankName":"Sinopay","cardType":"1","cardNum":"qmfkmjdaeW68roQXi6+PT5s5GEnyFdbU","sysareaId":null,"cardId":"a4940a4cbfc611e78dd9000c2939bb25","paymentOrder":null,"channelType":"000003"}]
     * totalCredit : null
     */
    private String totalCredit;

    public ArrayList<BankCardInfo> list;

    @Override
    public String toString() {
        return "{" +
                "msg='" + msg + '\'' +
                ", totalCredit='" + totalCredit + '\'' +
                ", status='" + status + '\'' +
                ", list='" + list + '\'' +
                '}';
    }

    public String getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(String totalCredit) {
        this.totalCredit = totalCredit;
    }

    public ArrayList<BankCardInfo> getList() {
        return list;
    }

    public void setList(ArrayList<BankCardInfo> list) {
        this.list = list;
    }

}
