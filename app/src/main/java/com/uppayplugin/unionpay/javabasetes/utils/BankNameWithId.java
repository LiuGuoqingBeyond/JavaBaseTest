package com.uppayplugin.unionpay.javabasetes.utils;

import android.graphics.Bitmap;

import java.util.HashMap;

/**
 * Created by admin on 2017/8/24.
 */

public class BankNameWithId {
    public static String setBankName(String bankName) {
        HashMap hashMap = new HashMap<String, Bitmap>();
        hashMap.put("中国工商银行", "102");
        hashMap.put("中国农业银行", "103");
        hashMap.put("中国银行", "104");
        hashMap.put("交通银行", "301");
        hashMap.put("招商银行", "308");
        hashMap.put("中国民生银行", "305");
        hashMap.put("中国建设银行", "989");
        hashMap.put("中国邮政储蓄银行", "403");
        hashMap.put("中信银行", "302");
        hashMap.put("中国信托商业银行", "600");


        hashMap.put("中国光大银行", "303");
        hashMap.put("中国农业发展银行", "203");
        hashMap.put("中信嘉华银行", "514");
        hashMap.put("中国进出口银行", "202");
        hashMap.put("渤海银行", "318");
        hashMap.put("恒丰银行", "315");
        hashMap.put("恒生银行", "504");
        hashMap.put("城市信用社", "401");
        hashMap.put("城市商业银行", "313");
        hashMap.put("重庆银行", "321");


        hashMap.put("创兴银行", "507");
        hashMap.put("大华银行", "622");
        hashMap.put("大新银行", "513");
        hashMap.put("大众银行", "508");
        hashMap.put("第一商业银行", "525");
        hashMap.put("东亚银行", "502");
        hashMap.put("东方汇理银行", "694");
        hashMap.put("广发银行", "306");
        hashMap.put("国家开发银行", "201");
        hashMap.put("华一银行", "787");


        hashMap.put("华侨永享银行", "621");
        hashMap.put("华南商业银行", "521");
        hashMap.put("华商银行", "785");
        hashMap.put("华夏银行", "304");
        hashMap.put("华美银行", "775");
        hashMap.put("花旗银行", "531");
        hashMap.put("徽商银行", "319");
        hashMap.put("汇丰银行", "501");
        hashMap.put("南洋商业银行", "503");
        hashMap.put("农村信用社", "402");


        hashMap.put("农村合作银行", "317");
        hashMap.put("农村商业银行", "314");
        hashMap.put("盘古银行", "631");
        hashMap.put("平安银行", "307");
        hashMap.put("企业银行", "596");
        hashMap.put("上海农村商业银行", "322");
        hashMap.put("上海商业银行", "511");
        hashMap.put("上海浦东发展银行", "310");
        hashMap.put("上海银行", "325");
        hashMap.put("厦门国际银行", "781");

        hashMap.put("首都银行", "616");
        hashMap.put("兴业银行", "309");
        hashMap.put("新联商业银行", "773");
        hashMap.put("星展银行", "623");
        hashMap.put("永丰商业银行", "SIN");
        hashMap.put("永亨银行", "510");
        hashMap.put("永隆银行", "512");
        hashMap.put("友利银行", "593");
        hashMap.put("玉山商业银行", "529");
        hashMap.put("兆丰国际商业银行", "527");

        hashMap.put("彰化银行", "522");
        hashMap.put("渣打银行", "671");
        hashMap.put("浙商银行", "316");
        hashMap.put("温州银行", "111");
        

        for (Object obj : hashMap.keySet()) {
            Object key = obj;
            Object value = hashMap.get(obj);
            if (bankName.equals(obj)) {
                return (String) value;
            }
        }
        return "";//没有就返回空
    }
}
