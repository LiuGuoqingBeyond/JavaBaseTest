package com.uppayplugin.unionpay.javabasetes.entity.response;

import java.util.ArrayList;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/10/16
 * Time: 16:06
 */

public class CountyCodeListRepModel extends BaseRepModel {
    public ArrayList<CountyCodeModel> list;

    public ArrayList<CountyCodeModel> getList() {
        return list;
    }

    public void setList(ArrayList<CountyCodeModel> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "CountyCodeListRepModel{" +
                "list=" + list +
                '}';
    }
}
