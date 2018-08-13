package com.uppayplugin.unionpay.javabasetes.utils.personal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件工具类
 */
public class FileUtil {

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 保存文件
     * @param bm
     * @throws IOException
     */
    public static File saveFile(Bitmap bm, String imagePath, String imageName) throws IOException {
        File dirFile = new File(imagePath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        File myIconFile= new File(imagePath + imageName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myIconFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myIconFile;
    }

    /**
     * 从本地获取图片
     * @param pathString 文件路径
     * @return 图片
     */
    public static Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if(file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
            // TODO: handle exception
            bitmap = null;
        }
        return bitmap;
    }

    /**
     * 检查文件是否存在
     *
     * @param filePath 文件路径
     * @return
     */
    public static boolean checkFileExist(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File dir = new File(filePath);
        if (dir.exists()) {
            return true;
        }
        return false;
    }
}
