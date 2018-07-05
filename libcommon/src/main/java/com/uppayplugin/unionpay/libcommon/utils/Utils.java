package com.uppayplugin.unionpay.libcommon.utils;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class Utils {

	public static final DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * Judge the String whether a empty
	 * true: empty false: not empty
	 * @param str  String
	 * @return
	 */
	public static boolean isEmpty(final String str) {
		if ((str == null) || (str.length() == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * Judge the Collection whether a empty
	 * true: empty false: not empty
	 * @param col     Collection
	 * @return
	 */
	public static boolean isEmpty(final Collection<?> col) {
		if ((col == null) || (col.size() == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * Judge the Object whether empty
	 * true: empty false: not empty
	 * @param obj   Object
	 * @return
	 */
	public static boolean isEmpty(final Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	/**
	 * Judge the Map whether empty
	 * true: empty false: not empty
	 * @param map  Map
	 * @return
	 */
	public static boolean isEmpty(final Map<?,?> map) {
		if ((map == null) || (map.size() == 0)) {
			return true;
		}
		return false;
	}

	public static String getDateByMins(long longtime){
		Date date=new Date(longtime);


		return dateFormat.format(date);

	}


	/**
	 * 生成xml文件时的时间格式设定
	 */
	public static String getCommentTime(){
		String s = "MMM dd yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s,Locale.US);
		return simpleDateFormat.format(new Date());
	}
	/**
	 * 生成xml文件时的时间格式设定
	 */
	public static String getCommentDate(){
		String s = "EEE MMM dd HH:mm:ss yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s,Locale.US);
		return simpleDateFormat.format(new Date());
	}

	public static String getCurrentDate(){
		String s = "MM.dd.yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s,Locale.US);
		return simpleDateFormat.format(new Date());
	}

	public static String getCurrentDatetime(){
		String s = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s,Locale.US);
		return simpleDateFormat.format(new Date());
	}

	public static String getDateStr(){
		String s = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s,Locale.US);
		return simpleDateFormat.format(new Date());
	}
	/**
	 * 验证所给的字符串是否为数字
	 * @param _temp
	 * @return
	 */
	public static boolean isNumeric(String _temp){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(_temp).matches();
	}
	/**
	 * 验证所给的字符串格式
	 * eg: 555.32 or 555
	 * @param var
	 * @return
	 */
	public static boolean isVar(String var){
		Pattern pattern = Pattern.compile("[0-9]+|[0-9]+.[0-9]+");
		return pattern.matcher(var).matches();
	}

	/**
	 * 去掉所有空格和制表符号
	 * @param str
	 * @return
	 */
	public static  String removeAllBlank(String str){

	   str=str.replaceAll("(\\s|\\t)*", "");

	    return str;
	}


	public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
        if (c[i] == 12288) {
        c[i] = (char) 32;
        continue;
         }
         if (c[i] > 65280 && c[i] < 65375)
            c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

}
