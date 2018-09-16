package com.uppayplugin.unionpay.javabasetes.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/15 0015.
 */

public class GetData {
    public static final String TYPE_ONE = "type_one";
    public static final String TYPE_TWE = "type_twe";
    public static final String TYPE_THREE = "type_three";

    public List<String> GetData(String type) {
        List<String> data = new ArrayList<>();
        switch (type) {
            case TYPE_ONE:
                data = loadTypeOne();
                break;
            case TYPE_TWE:
                data=loadTypeTwe();
                break;
            case TYPE_THREE:
                data=loadTypeThree();
                break;
        }
        return data;
    }

    private List<String> loadTypeOne() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add("第"+i+"条数据");
        }
        return data;
    }

    private List<String> loadTypeTwe() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("第"+i+"条数据");
        }
        return data;
    }

    private List<String> loadTypeThree() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            data.add("第"+i+"条数据");
        }
        return data;
    }
}
