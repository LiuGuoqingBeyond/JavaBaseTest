package com.uppayplugin.unionpay.javabasetes.utils.device;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SystemManager {

    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @param command 命令：String apkRoot="chmod 777 "+getPackageCodePath();
     * @return  0 命令执行成功
     */
    public static int RootCommand(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            int i = process.waitFor();

            Log.d("SystemManager", "i:" + i);
            return i;
        } catch (Exception e) {
            Log.d("SystemManager", e.getMessage());
            return -1;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 提升读写权限
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public static void setPermission(String filePath)  {
        String command = "chmod " + "777" + " " + filePath;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param context
     * @param apkPath 要安装的APK
     * @param rootMode 是否是Root模式
     */
    public static void install(Context context, String apkPath, boolean rootMode){
        if (rootMode){
            installRoot(context,apkPath);
        }else {
            installNormal(context,apkPath);
        }
    }

    /**
     * 通过非Root模式安装
     * @param context
     * @param apkPath
     */
    public static void install(Context context, String apkPath){
        install(context,apkPath,false);
    }

    //普通安装
    private static void installNormal(Context context, String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File file = (new File(apkPath));
            Logger.e("路径"+apkPath);
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, "com.whty.xzfpos" + ".fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(apkPath)),
                    "application/vnd.android.package-archive");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //通过Root方式安装
    private static void installRoot(Context context, String apkPath) {
        Observable.just(apkPath)
                .map(mApkPath -> "pm install -r " + mApkPath)
                .map(SystemManager::RootCommand)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    if (integer == 0) {
                        Toast.makeText(context, "安装成功", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(context, "root权限获取失败,尝试普通安装", Toast.LENGTH_SHORT).show();
                        install(context,apkPath);
                    }
                });
    }

    public static void closeIO(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 删除之前的apk
     *
     * @param apkName apk名字
     * @return
     */
    public static File clearApk(Context context, String apkName) {
        File apkFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), apkName);
        if (apkFile.exists()) {
            apkFile.delete();
        }
        return apkFile;
    }
}
