package com.sinopaylib.api;

import com.sinopaylib.inter.AppLoginInterface;
import com.sinopaylib.inter.MerChantInter;
import com.sinopaylib.inter.RquestQRCodeInter;
import com.sinopaylib.inter.SessionIdInterface;
import com.sinopaylib.inter.TradeRecordInter;

/**
 * 接口代理
 *
 * @project：DemoTest
 * @author：- octopus on 2018/7/27 18:16
 * @email：zhanghuan@xinguodu.com
 */
public class APIProcxy {

    /**
     * SDK初始化和终端通信操作接口
     **/
    private static SessionIdInterface mSessionIdInterface = null;
    //获取app登陆接口
    private static AppLoginInterface mAppLoginInterface = null;
    private static RquestQRCodeInter mQRCode = null;
    private static TradeRecordInter mRecord = null;
    private static APIProcxy apiProcxy;
    private static MerChantInter mMerChantInfo = null;

    public static synchronized APIProcxy getInstance() {
        if (null == apiProcxy) {
            apiProcxy = new APIProcxy();
        }
        return apiProcxy;
    }

    /**
     * 加载接口实现类
     * load realize class of interface
     *
     * @param classPath
     * @return realize class
     */
    private Class<?> loadClass(final String classPath) {
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
     * 获取temSessionId
     */
    public SessionIdInterface getmSessionIdInterface() {
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
     * app登陆
     */
    public AppLoginInterface getmAppLoginInterface() {
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

    /**
     * 获取二维码
     */
    public RquestQRCodeInter getmQRCode() {
        final String STATIC_QRCODE_PATH = "com.sinopaylib.impl.QRCodeImpl";
        Class<?> myclass = loadClass(STATIC_QRCODE_PATH);
        if (myclass == null) {
            throw new RuntimeException("QRCodeImpl not implemented.");
        }

        if (mQRCode == null) {
            try {
                mQRCode = (RquestQRCodeInter) myclass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mQRCode;
    }

    /**
     * 获取交易记录
     */
    public TradeRecordInter getmRecord() {
        final String STATIC_TRADE_PATH = "com.sinopaylib.impl.TradeRecordImpl";
        Class<?> myclass = loadClass(STATIC_TRADE_PATH);
        if (myclass == null) {
            throw new RuntimeException("TradeRecordImpl not implemented.");
        }

        if (mRecord == null) {
            try {
                mRecord = (TradeRecordInter) myclass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mRecord;
    }

    /**
     * 获取商家信息
     */
    public MerChantInter getmMerChantInfo() {
        final String STATIC_MERCHANTINFO_PATH = "com.sinopaylib.impl.MerChantImpl";
        Class<?> myclass = loadClass(STATIC_MERCHANTINFO_PATH);
        if (myclass == null) {
            throw new RuntimeException("MerChantImpl not implemented.");
        }

        if (mMerChantInfo == null) {
            try {
                mMerChantInfo = (MerChantInter) myclass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mMerChantInfo;
    }
}
