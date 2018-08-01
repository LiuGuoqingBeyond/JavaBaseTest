package com.sinopaylib;

import com.sinopaylib.inter.AppLoginInterface;
import com.sinopaylib.inter.SessionIdInterface;

/**
 * 接口代理
 *
 * @project：DemoTest
 * @author：- octopus on 2018/7/27 18:16
 * @email：zhanghuan@xinguodu.com
 */
public class APIProcxy {

    /** SDK初始化和终端通信操作接口 **/
    private static SessionIdInterface mSessionIdInterface = null;
    //获取app登陆接口
    private static AppLoginInterface mAppLoginInterface = null;

    /**
     * 加载接口实现类
     * load realize class of interface
     *
     * @param classPath
     *
     * @return realize class
     */
    private static Class<?> loadClass(final String classPath) {
        ClassLoader classLoader = APIProcxy.class.getClassLoader();
        Class<?> myClass1 = null;
        try {
            myClass1 = classLoader.loadClass(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (myClass1 != null) {
            return myClass1;
        }

        if (myClass1 != null) {
            return myClass1;
        }
        return null;
    }

    /**
     * 获取SDK初始化和终端通讯接口
     *
     * @return 返回终端操作对象
     */
    public static SessionIdInterface getmSessionIdInterface() {
        final String STATIC_TRADE_SERVICE_PATH = "com.sinopaylib.impl.GetSessionIdImpl";
        Class<?> myclass = loadClass(STATIC_TRADE_SERVICE_PATH);
        if (myclass == null) {
            throw new RuntimeException("GetSessionIdImpl not implemented.");
        }

        if (mSessionIdInterface == null) {
            try {
                mSessionIdInterface = (SessionIdInterface) myclass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mSessionIdInterface;
    }

    /**
     * 获取app登陆接口
     *
     * @return 返回终端操作对象
     */
    public static AppLoginInterface getmAppLoginInterface() {
        final String STATIC_APP_LOGIN_PATH = "com.sinopaylib.impl.AppLoginImpl";
        Class<?> myclass = loadClass(STATIC_APP_LOGIN_PATH);
        if (myclass == null) {
            throw new RuntimeException("GetSessionIdImpl not implemented.");
        }

        if (mAppLoginInterface == null) {
            try {
                mAppLoginInterface = (AppLoginInterface) myclass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mAppLoginInterface;
    }

}
