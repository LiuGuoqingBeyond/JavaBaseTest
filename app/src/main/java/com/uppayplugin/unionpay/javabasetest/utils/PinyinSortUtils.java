package com.uppayplugin.unionpay.javabasetest.utils;

import android.content.Context;

import com.uppayplugin.unionpay.javabasetest.entity.response.BankCardInfo;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class PinyinSortUtils {

    private static Context mContext;
    private static PinyinSortUtils instance;

    public PinyinSortUtils(){}
    public static PinyinSortUtils getInstance(Context context){
        mContext = context;
        if (instance == null){
            synchronized (PinyinSortUtils.class){
                if (instance == null){
                    instance = new PinyinSortUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 根据数组里面首字母排序
     * @param data
     * @return
     */
    public String[] sort(String [] data){
        if(data==null || data.length==0){
            return null;
        }
        Comparator<Object> comparator = Collator.getInstance(java.util.Locale.CHINA);
        Arrays.sort(data, comparator);
        return data;
    }
    /**
     * @TODO 将一个装有person对象的 list 根据name 首字母排序
     * @param list 排序前的数据源
     * @return list排序后的数据
     */
    public List<BankCardInfo> listToSortByName(List<BankCardInfo> list){
        if(list==null || list.size()==0){
            return null;
        }
        Map<String, BankCardInfo> map = new HashMap<String, BankCardInfo>();
        String names[] = new String[list.size()];
        for(int i=0;i<list.size();i++){
            String name = list.get(i).getTestName().trim();
            String alphabet = name.substring(0, 1);
            /*判断首字符是否为中文，如果是中文便将首字符拼音的首字母和&符号加在字符串前面*/
            if (alphabet.matches("[\\u4e00-\\u9fa5]+")) {
                name = getAlphabet(name) + "&" + name;
                names[i] = name;
            }else{
                names[i]=name;
            }
            //names[i] = name;
            map.put(name, list.get(i));
        }
        names = sort(names);
        list.clear();
        for(String name : names){
            if(map.containsKey(name.trim()))
                list.add(map.get(name));
        }
        return list;
    }
    /**
     * 调用汉子首字母转化为拼音的根据类，，需要在项目中导入pinyin4j.jar包
     * @param str
     * @return
     */
    public String getAlphabet(String str) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出拼音全部小写
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不带声调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String pinyin = null;
        try {
            pinyin = PinyinHelper.toHanyuPinyinStringArray(str.charAt(0), defaultFormat)[0];
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pinyin.substring(0, 1);
    }
}
