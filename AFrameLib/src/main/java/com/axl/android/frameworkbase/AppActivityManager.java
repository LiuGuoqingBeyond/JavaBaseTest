package com.axl.android.frameworkbase;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/10/24
 * Time: 09:31
 */

public class AppActivityManager {

    private AppActivityManager() {

    }

    public static AppActivityManager getInstance() {
        if (null == instance) {
            synchronized (AppActivityManager.class) {
                if (null == instance) {
                    instance = new AppActivityManager();
                }
            }
        }
        return instance;
    }

    private static Stack<Activity> activityStack;
    private static AppActivityManager instance;

    /**
     * 添加Activity到堆栈
     */
    public void add(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除堆栈中指定Activity
     */
    public void remove(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.remove(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity top() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finish() {
        Activity activity = activityStack.lastElement();
        finish(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finish(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finish(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finish(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAll() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            Activity activity = activityStack.get(i);
            if (null != activity && !activity.isFinishing()) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 获取指定的Activity
     */
    public static Activity getActivity(Class<?> cls) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAll();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
