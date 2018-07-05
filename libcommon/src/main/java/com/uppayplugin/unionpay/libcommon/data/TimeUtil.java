package com.uppayplugin.unionpay.libcommon.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 *
 * @author shanxiaoping
 */
public class TimeUtil {

    public static String TIME_PATTERN = "HH:mm:ss";// 定义标准时间格式
    public static String DATE_PATTERN_1 = "yyyy/MM/dd";// 定义标准日期格式1
    public static String DATE_PATTERN_2 = "yyyy-MM-dd";// 定义标准日期格式2
    public static String DATE_PATTERN_3 = "yyyy/MM/dd HH:mm:ss";// 定义标准日期格式3，带有时间
    public static String DATE_PATTERN_4 = "yyyy/MM/dd HH:mm:ss E";// 定义标准日期格式4，带有时间和星期
    public static String DATE_PATTERN_5 = "yyyy年MM月dd日 HH:mm:ss E";// 定义标准日期格式5，带有时间和星期
    public static String DATE_PATTERN_6 = "yyyy-MM-dd HH:mm:ss";// 定义标准日期格式6，带有时间
    public static String DATE_PATTERN_7 = "yyyy年MM月dd日";// 定义标准日期格式7
    public static String DATE_PATTERN_8 = "yyyy-MM-dd HH:mm";// 定义标准日期格式8，带有时间
    public static String DATE_PATTERN_9 = "yy-MM-dd HH时";// 定义标准日期格式9，带有时间
    public static String DATE_PATTERN_10 = "yyyy-MM";// 定义标准日期格式10
    public static String DATE_PATTERN_11 = "yyyy.MM.dd";// 定义标准日期格式10
    public static String TIME_PATTERN_12 = "HH:mm";// 定义标准时间格式
    public static String TIME_PATTERN_13 = "MM-dd";
    public static String TIME_PATTERN_14 = "yyyy年MM月dd日 HH:mm";
    public static String DATE_PATTERN_15 = "MM/yy";
    public static String TIME_PATTERN_16 = "E HH:mm";


    /**
     * 定义时间类型常量
     */
    public final static int SECOND = 1;
    public final static int MINUTE = 2;
    public final static int HOUR = 3;
    public final static int DAY = 4;

    public final static long SECOND_TIME_LONG = 1000;
    public final static long MINUTE_TIME_LONG = 60 * 1000;
    public final static long HOUR_TIME_LONG = 60 * 60 * 1000;
    public final static long DAY_TIME_LONG = 24 * 60 * 60 * 1000;

    /**
     * 判断某个时间是否已经截止
     *
     * @param time    指定时间
     * @param pattern 时间格式
     * @return 是否截止
     */
    public boolean dataIsAbort(String time, String pattern) {
        return dataIsAbort(time, pattern, true);
    }

    /**
     * describe 是否截至
     *
     * @author shanxiaoping
     * @data 16/6/15 下午8:02
     */
    public boolean dataIsAbort(String time, String pattern, boolean isEqual) {
        long assignTime = geTimeLong(time, pattern);
        long currenTime = System.currentTimeMillis();
        if (isEqual) {
            return currenTime - assignTime >= 0;
        } else {
            return currenTime - assignTime > 0;
        }
    }

    public boolean dataIsDay(String time, String pattern, boolean isEqual) {
        Date assignDate = formatString(time, pattern);
        Date nowDate = new Date();
        if (nowDate.getYear() > assignDate.getYear()) {
            return true;
        }
        if (nowDate.getYear() < assignDate.getYear()) {
            return false;
        }
        if (nowDate.getMonth() > assignDate.getMonth()) {
            return true;
        }
        if (nowDate.getMonth() < assignDate.getMonth()) {
            return false;
        }
        return isEqual ? nowDate.getDay() >= assignDate.getDay() : nowDate.getDay() > assignDate.getDay();
    }

    /**
     * describe 判断月份过期
     *
     * @author shanxiaoping
     * @data 16/6/27 下午12:53
     */
    public boolean monthIsAbort(String time, String pattern) {
        Date date1 = formatString(time, pattern);
        Date date2 = new Date();
        if (date2.getYear() > date1.getYear()) {
            return true;
        }
        if (date2.getYear() < date1.getYear()) {
            return false;
        }
        return date2.getMonth() > date1.getMonth();
    }


    /**
     * 日期字符串转换成指定格式的字符串
     *
     * @param source
     * @param sourcePt
     * @param tagPt
     * @return
     */
    public String getPatternString(String source, String sourcePt, String tagPt) {
        Date data = formatString(source, sourcePt);
        return formatDate(data, tagPt);
    }

    /**
     * 获得指定日期的长度
     *
     * @param dataStr 日期字符串
     * @param pattern 格式
     * @return
     */
    public long geTimeLong(String dataStr, String pattern) {
        Date data = formatString(dataStr, pattern);
        return data.getTime();

    }

    /**
     * 指定日期字符串指定格式的时间长度
     *
     * @param source   原日期
     * @param sourcePt 原日期格式
     * @param tagPt    目标格式
     * @return
     */
    public long getTimeLong(String source, String sourcePt, String tagPt) {
        String result = getPatternString(source, sourcePt, tagPt);
        return formatString(result, tagPt).getTime();
    }



    /***
     * 获取定制的时间格式
     *
     * @param standardTime 标准格式yyyy-MM-dd HH:mm:ss时间字符串
     * @return
     */
    public String getCustomizedTime(long standardTime) {
        Date nowDate = new Date();
        Date showDate = new Date(standardTime);
        if (nowDate.getMonth() == showDate.getMonth() && nowDate.getYear() == showDate.getYear()) {
            int dateValue = nowDate.getDate() - showDate.getDate();
            if (dateValue == 0) {
                return formatLong(standardTime, TIME_PATTERN_12);
            } else if (dateValue == 1) {
                return "昨天" + formatLong(standardTime, TIME_PATTERN_12);
//            } else if (dateValue <= 2) {
//                return formatLong(standardTime, TIME_PATTERN_16);
            } else {
                return formatLong(standardTime, DATE_PATTERN_8);
            }
        } else {
            return formatLong(standardTime, DATE_PATTERN_8);
        }
    }

    /**
     * describe 日期自定义描述
     *
     * @author shanxiaoping
     * @data 16/3/22 下午2:33
     */
    public String getCustomizedData(String dataStr, String dataPattern) {
        Date nowTime = new Date();
        Date chatTime = formatString(dataStr, dataPattern);
        if (nowTime.getYear() == chatTime.getYear() && nowTime.getMonth() == chatTime.getMonth()) {
            if (nowTime.getDate() == chatTime.getDate()) {
                return "今天";
            }
            if (nowTime.getDate() - chatTime.getDate() == 1) {
                return "昨天";
            }
        }
        return dataStr;
    }

    /**
     * describe 判断是否本月
     *
     * @author shanxiaoping
     * @data 16/3/22 下午2:35
     */
    public String getCustomizedMonth(String monthStr, String monthPattern) {
        Date nowTime = new Date();
        Date chatTime = formatString(monthStr, monthPattern);
        if (nowTime.getYear() == chatTime.getYear()) {
            if (nowTime.getMonth() == chatTime.getMonth()) {
                return "本月";
            }
            return monthStr;
        }
        return monthStr;
    }


    /**
     * 把一个日期，按照某种格式 格式化输出
     *
     * @param date    日期对象
     * @param pattern 格式模型
     * @return 返回字符串类型
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 格式化输出 long 数值 日期
     *
     * @param datalong
     * @param pattern
     * @return
     */
    public static String formatLong(long datalong, String pattern) {
        Date data = new Date(datalong);
        return formatDate(data, pattern);
    }

    public String formatLong(String longStr, String pattern) {
        long longTime = Long.valueOf(longStr);
        return formatLong(longTime, pattern);
    }

    /**
     * 将字符串类型的时间转换为Date类型
     *
     * @param str     时间字符串
     * @param pattern 格式
     * @return 返回Date类型
     */
    public Date formatString(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date time = null;
        // 需要捕获ParseException异常，如不要捕获，可以直接抛出异常，或者抛出到上层
        try {
            try {
                time = sdf.parse(str);
            } catch (java.text.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }


    /**
     * 将一个表示时间段的数转换为毫秒数
     *
     * @param num  时间差数值,支持小数
     * @param type 时间类型：1->秒,2->分钟,3->小时,4->天
     * @return long类型时间差毫秒数，当为-1时表示参数有错
     */
    public long formatToTimeMillis(double num, int type) {
        if (num <= 0)
            return 0;
        switch (type) {
            case SECOND:
                return (long) (num * 1000);
            case MINUTE:
                return (long) (num * 60 * 1000);
            case HOUR:
                return (long) (num * 60 * 60 * 1000);
            case DAY:
                return (long) (num * 24 * 60 * 60 * 1000);
            default:
                return -1;
        }
    }

    /**
     * 只输出一个时间中的月份
     *
     * @param date
     * @return 返回int数值类型
     */
    public int getMonth(Date date) {
        String month = formatDate(date, "MM");// 只输出时间
        return Integer.parseInt(month);
    }

    /**
     * 只输出一个时间中的年份
     *
     * @param date
     * @return 返回数值类型
     */
    public int getYear(Date date) {
        String year = formatDate(date, "yyyy");// 只输出年份
        return Integer.parseInt(year);
    }

    /**
     * 得到一个日期函数的格式化时间
     *
     * @param date 日期对象
     * @return
     */
    public String getTimeByDate(Date date) {
        return formatDate(date, TIME_PATTERN);
    }

    /**
     * 获取当前的时间，new Date()获取当前的日期
     *
     * @return
     */
    public String getNowTime() {
        return getNowTime(TIME_PATTERN);
    }

    /**
     * 获取当前的时间，new Date()获取当前的日期
     *
     * @return
     */
    public String getNowTime(String pattern) {
        return formatDate(new Date(), pattern);
    }

    /**
     * 获取某一指定时间的前一段时间
     *
     * @param num  时间差数值
     * @param type 时间差类型：1->秒,2->分钟,3->小时,4->天
     * @param date 参考时间
     * @return 返回格式化时间字符串
     */
    public String getPreTimeStr(double num, int type, Date date) {
        long nowLong = date.getTime();// 将参考日期转换为毫秒时间
        Date time = new Date(nowLong - formatToTimeMillis(num, type));// 减去时间差毫秒数
        return getTimeByDate(time);
    }

    /**
     * 获取某一指定时间的前一段时间
     *
     * @param num  时间差数值
     * @param type 时间差类型：1->秒,2->分钟,3->小时,4->天
     * @param date 参考时间
     * @return 返回Date对象
     */
    public Date getPreTime(double num, int type, Date date) {
        long nowLong = date.getTime();// 将参考日期转换为毫秒时间
        Date time = new Date(nowLong - formatToTimeMillis(num, type));// 减去时间差毫秒数
        return time;
    }

    /**
     * 获取某一指定时间的后一段时间
     *
     * @param num  时间差数值
     * @param type 时间差类型：1->秒,2->分钟,3->小时,4->天
     * @param date 参考时间
     * @return 返回格式化时间字符串
     */
    public String getNextTimeStr(double num, int type, Date date) {
        long nowLong = date.getTime();// 将参考日期转换为毫秒时间
        Date time = new Date(nowLong + formatToTimeMillis(num, type));// 加上时间差毫秒数
        return getTimeByDate(time);
    }

    /**
     * 获取某一指定时间的后一段时间
     *
     * @param num  时间差数值
     * @param type 时间差类型：1->秒,2->分钟,3->小时,4->天
     * @param date 参考时间
     * @return 返回Date对象
     */
    public Date getNextTime(double num, int type, Date date) {
        long nowLong = date.getTime();// 将参考日期转换为毫秒时间
        Date time = new Date(nowLong + formatToTimeMillis(num, type));// 加上时间差毫秒数
        return time;
    }

    /**
     * 得到前几个月的现在 利用GregorianCalendar类的set方法来实现
     *
     * @param num
     * @param date
     * @return
     */
    public Date getPreMonthTime(int num, Date date) {
        GregorianCalendar gregorianCal = new GregorianCalendar();
        gregorianCal
                .set(Calendar.MONTH, gregorianCal.get(Calendar.MONTH) - num);
        return gregorianCal.getTime();
    }

    /**
     * 得到后几个月的现在时间 利用GregorianCalendar类的set方法来实现
     *
     * @param num
     * @param date
     * @return
     */
    public Date getNextMonthTime(int num, Date date) {
        GregorianCalendar gregorianCal = new GregorianCalendar();
        gregorianCal
                .set(Calendar.MONTH, gregorianCal.get(Calendar.MONTH) + num);
        return gregorianCal.getTime();
    }

    /**
     * 判断时间是否在一定的区间内
     *
     * @param num
     * @param type
     * @param dataSoure
     * @param dataCompare
     * @return
     */
    public boolean judgeData(int num, int type, Date dataSoure, Date dataCompare) {
        long nowLong = dataSoure.getTime();
        long beforeLong = dataCompare.getTime();
        long gapLong = formatToTimeMillis(num, type);
        if (Math.abs(nowLong - beforeLong) < gapLong) {
            return true;
        }
        return false;
    }

    /**
     * 返回时间对象
     */
    private static TimeUtil instance = null;

    public static TimeUtil getInstance() {
        if (instance == null) {
            instance = new TimeUtil();
        }
        return instance;
    }

    /**
     * 获得时间长度
     *
     * @param timeLong
     * @param type
     * @return
     */
    public long geTimeLong(long timeLong, int type) {
        switch (type) {
            case SECOND:
                return subSecond(timeLong);
            case MINUTE:
                return subMinute(timeLong);
            case HOUR:
                return subHour(timeLong);
            case DAY:
                return subDay(timeLong);
        }
        return 0;
    }


    class TimeDescIndex {
        int startIndex;
        int endIndex;

    }

    /**
     * 计算秒
     *
     * @param timelong
     * @return
     */
    private long subSecond(long timelong) {
        return timelong / SECOND_TIME_LONG;

    }

    /**
     * 计算分
     *
     * @param timelong
     * @return
     */
    private static long subMinute(long timelong) {
        return timelong / MINUTE_TIME_LONG;

    }

    /**
     * 计算小时
     *
     * @param timelong
     * @return
     */
    private long subHour(long timelong) {
        return timelong / HOUR_TIME_LONG;

    }

    /**
     * 计算天
     *
     * @param timeLong
     * @return
     */
    private long subDay(long timeLong) {
        return timeLong / DAY_TIME_LONG;

    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public String gettimeStamp() {

        return String.valueOf(System.currentTimeMillis());
    }

    public static String getNowMDHMSTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "MM-dd HH:mm:ss");
        return mDateFormat.format(new Date());
    }


    public static String getTimeString(long time) {
//        if (System.currentTimeMillis() - time < MINUTE_TIME_LONG * 1000) {
//            return subMinute(System.currentTimeMillis() - time) + "分钟前";
//        } else {
            return TimeUtil.getInstance().getCustomizedTime(time);
//        }
    }
}
