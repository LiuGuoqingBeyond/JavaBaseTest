package com.uppayplugin.unionpay.javabasetest.log;

import com.uppayplugin.unionpay.javabasetest.BuildConfig;

/**
 * @Description: TODO(Log工具类)
 * @author wxj
 * @date 2015-3-24 下午4:02:18
 * @version V1.0
 */
public class AppLogger {

    private static boolean DEBUG = BuildConfig.DEBUG;

    private AppLogger() {
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param msg The message you would like logged.
     */
    public static void v(String tag, String msg) {
        if (DEBUG)
            android.util.Log.v(tag, buildMessage(msg));
    }

    /**
     * Send a VERBOSE log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void v(String tag, String msg, Throwable thr) {
        if (DEBUG)
            android.util.Log.v(tag, buildMessage(msg), thr);
    }

    /**
     * Send a DEBUG log message.
     *
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (DEBUG)
            android.util.Log.d(tag, buildMessage(msg));
    }

    /**
     * Send a DEBUG log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void d(String tag, String msg, Throwable thr) {
        if (DEBUG)
            android.util.Log.d(tag, buildMessage(msg), thr);
    }

    /**
     * Send an INFO log message.
     *
     * @param msg The message you would like logged.
     */
    public static void i(String tag, String msg) {
        if (DEBUG)
            android.util.Log.i(tag, buildMessage(msg));
    }

    /**
     * Send a INFO log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void i(String tag, String msg, Throwable thr) {
        if (DEBUG)
            android.util.Log.i(tag, buildMessage(msg), thr);
    }

    /**
     * Send an ERROR log message.
     *
     * @param msg The message you would like logged.
     */
    public static void e(String tag, String msg) {
        if (DEBUG)
            android.util.Log.e(tag, buildMessage(msg));
    }

    /**
     * Send a WARN log message
     *
     * @param msg The message you would like logged.
     */
    public static void w(String tag, String msg) {
        if (DEBUG)
            android.util.Log.w(tag, buildMessage(msg));
    }

    /**
     * Send a WARN log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void w(String tag, String msg, Throwable thr) {
        if (DEBUG)
            android.util.Log.w(tag, buildMessage(msg), thr);
    }

    /**
     * Send an empty WARN log message and log the exception.
     *
     * @param thr An exception to log
     */
    public static void w(String tag, Throwable thr) {
        if (DEBUG)
            android.util.Log.w(tag, buildMessage(""), thr);
    }

    /**
     * Send an ERROR log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void e(String tag, String msg, Throwable thr) {
        if (DEBUG)
            android.util.Log.e(tag, buildMessage(msg), thr);
    }

    /**
     * Building Message
     *
     * @param msg The message you would like logged.
     * @return Message String
     */
    protected static String buildMessage(String msg) {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];

        return new StringBuilder()
                .append(caller.getClassName())
                .append(".")
                .append(caller.getMethodName())
                .append("() line:")
                .append(caller.getLineNumber())
                .append(" ")
                .append(msg).toString();
    }
}
