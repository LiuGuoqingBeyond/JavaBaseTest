package com.uppayplugin.unionpay.javabasetes.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.entity.auth.ImageTypeEnum;
import com.uppayplugin.unionpay.javabasetes.utils.ImageTool;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetes.utils.personal.FileUtil;
import com.whty.xzfpos.base.AppToolBarActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

import static com.uppayplugin.unionpay.javabasetes.utils.personal.FileUtil.getRealFilePathFromUri;

public class CompressImgActivity extends AppToolBarActivity {
    @BindView(R.id.img_compress)
    ImageView imgCompress;
    @BindView(R.id.btn_click)
    Button btnClick;
    @BindView(R.id.gridview)
    GridView gridView;
    ImageTypeEnum imageTypeEnum = null;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片文件
    private File tempFile;
    List<String> photos;
    private String bankCode;
    private List<String> list;
    private static final String IMG_ADD_TAG = "a";
    private static final int IMG_COUNT = 8;
    //选择分行回调
    private static final int REQUEST_BRANCH = 105;
    private GVAdapter adapter;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_compress_img;
    }

    @Override
    protected void initViewsAndEvents() {
        initData();
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage(ImageTypeEnum.IDCARDIMAGE);
            }
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**
     * 上传照片信息
     */
    public void uploadImage(ImageTypeEnum imageTypeEnums) {
        try {
            this.imageTypeEnum = imageTypeEnums;
            View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
            TextView btnCarema = view.findViewById(R.id.btn_camera);
            TextView btnPhoto = view.findViewById(R.id.btn_photo);
            TextView btnCancel = view.findViewById(R.id.btn_cancel);
            final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
            popupWindow.setOutsideTouchable(true);
            View parent = LayoutInflater.from(this).inflate(R.layout.activity_compress_img, null);
            popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            //popupWindow在弹窗的时候背景半透明
            final WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 0.5f;
            getWindow().setAttributes(params);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    params.alpha = 1.0f;
                    getWindow().setAttributes(params);
                }
            });

            btnCarema.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //权限判断
                    if ((ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                            ) {
                        //申请WRITE_EXTERNAL_STORAGE权限  READ_EXTERNAL_STORAGE
                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);

                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到调用系统相机
                        gotoCamera();
                    }
                    popupWindow.dismiss();
                }
            });
            btnPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //权限判断
                    if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到相册
                        gotoPhoto();
                    }
                    popupWindow.dismiss();
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
    }

    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, "com.uppayplugin.unionpay.javabasetes" + ".fileprovider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************open photo********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.personal_select_img)), REQUEST_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    /*Uri uri = Uri.fromFile(tempFile);
                    if (uri == null) {
                        return;
                    }

                    photos = new ArrayList<>();
                    String path = getRealFilePathFromUri(mContext, uri);
                    photos.add(path);*/

                    final Uri uri = Uri.fromFile(tempFile);
                    String path = ImageTool.getImageAbsolutePath(this, uri);
                    System.out.println(path);
                    if (list.size() == IMG_COUNT) {
                        removeItem();
                        refreshAdapter();
                        return;
                    }
                    removeItem();
                    list.add(path);
                    list.add(IMG_ADD_TAG);
                    refreshAdapter();

//                    compressWithRx(photos);

                    // gotoClipActivity(uri);
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    /*Uri uri = intent.getData();
                    // gotoClipActivity(uri);

                    photos = new ArrayList<>();
                    String path = getRealFilePathFromUri(mContext, uri);
                    photos.add(path);

                    compressWithRx(photos);*/
                    final Uri uri = intent.getData();
                    String path = ImageTool.getImageAbsolutePath(this, uri);
                    System.out.println(path);
                    if (list.size() == IMG_COUNT) {
                        removeItem();
                        refreshAdapter();
                        return;
                    }
                    removeItem();
                    list.add(path);
                    list.add(IMG_ADD_TAG);
                    refreshAdapter();
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    /*final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);

                    // bitMap = ImageCompressUtils.bitmapImageCompress(bitMap);
                    setImageValue(bitMap);
                    //此处后面可以将bitMap转为二进制上传后台网络
//                    uploadImageToWeb(bitMap);*/
                    final Uri uri = intent.getData();
                    String path = ImageTool.getImageAbsolutePath(this, uri);
                    System.out.println(path);
                    if (list.size() == IMG_COUNT) {
                        removeItem();
                        refreshAdapter();
                        return;
                    }
                    removeItem();
                    list.add(path);
                    list.add(IMG_ADD_TAG);
                    refreshAdapter();
                }
                break;
            case REQUEST_BRANCH:
                if (resultCode == RESULT_OK) {
                    //获得回调数据
                    bankCode = intent.getStringExtra("bankCode");
                    String branchName = intent.getStringExtra("branchName");
//                    text_bankBranch.setText(branchName);
                }
                break;
            case 0:
                final Uri uri = intent.getData();
                String path = ImageTool.getImageAbsolutePath(this, uri);
                System.out.println(path);
                if (list.size() == IMG_COUNT) {
                    removeItem();
                    refreshAdapter();
                    return;
                }
                removeItem();
                list.add(path);
                list.add(IMG_ADD_TAG);
                refreshAdapter();
                break;
        }
    }

    private void removeItem() {
        if (list.size() != IMG_COUNT) {
            if (list.size() != 0) {
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 压缩图片
     *
     * @param photos
     */
    private void compressWithRx(final List<String> photos) {
        Flowable.just(photos)
                .observeOn(Schedulers.io())
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(@NonNull List<String> list) throws Exception {
                        return Luban.with(mContext).load(list).get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(@NonNull List<File> list) throws Exception {
                        for (File file : list) {
                            /*int[] thumbSize = PublicMethodUtils.computeSize(file.getAbsolutePath());
                            String thumbArg = String.format(Locale.CHINA, "压缩后参数：%d*%d, %dk", thumbSize[0], thumbSize[1], file.length() >> 10);*/

                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                            int h = bitmap.getHeight();
                            int w = bitmap.getWidth();
                            if (w < 960 && h < 720) {
                                setImageValue(bitmap);
                                //此处后面可以将bitMap转为二进制上传后台网络
//                                uploadImageToWeb(bitmap);
                            } else {
                                float hSize = (float) (960.0 / w);
                                float wSize = (float) (720.0 / h);
                                float size;
                                if (hSize < wSize) {
                                    size = hSize;
                                } else {
                                    size = wSize;
                                }
                                int height = (int) (bitmap.getHeight() * size);
                                int width = (int) (bitmap.getWidth() * size);

                                bitmap = Bitmap.createScaledBitmap(
                                        bitmap, width, height, true);

                                setImageValue(bitmap);
                                //此处后面可以将bitMap转为二进制上传后台网络
//                                uploadImageToWeb(bitmap);
                            }
                        }
                    }
                });
    }

    /**
     * 设置图片框内容
     *
     * @param bitmap
     */
    public void setImageValue(Bitmap bitmap) {
        if (null == bitmap) return;
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        try {
            imgCompress.setImageBitmap(bitmap);
           /* switch (imageTypeEnum) {
                case IDCARDIMAGE:
                    iv_idCardNum.setBackgroundResource(0);

                    iv_idCardNum.setScaleType(ImageView.ScaleType.FIT_XY);
                    iv_idCardNum.setMinimumHeight((int) (wm.getDefaultDisplay().getHeight() * 0.28));

                    iv_idCardNum.setImageBitmap(bitmap);
                    iv_addIdCard.setVisibility(View.GONE);
                    break;
                case SHUIWUIMAGE:
                    iv_shuiwuDengJi.setBackgroundResource(0);

                    iv_shuiwuDengJi.setScaleType(ImageView.ScaleType.FIT_XY);
                    iv_shuiwuDengJi.setMinimumHeight((int) (wm.getDefaultDisplay().getHeight() * 0.28));

                    iv_shuiwuDengJi.setImageBitmap(bitmap);
                    iv_addShuiwu.setVisibility(View.GONE);
                    break;
                case BANKSETTLE:
                    iv_bankSettle.setBackgroundResource(0);

                    iv_bankSettle.setScaleType(ImageView.ScaleType.FIT_XY);
                    iv_bankSettle.setMinimumHeight((int) (wm.getDefaultDisplay().getHeight() * 0.28));

                    iv_bankSettle.setImageBitmap(bitmap);
                    iv_addBankSettle.setVisibility(View.GONE);
                    break;
                case MERCHANTADDRESSIMAGE:
                    iv_merchantAddress.setBackgroundResource(0);

                    iv_merchantAddress.setScaleType(ImageView.ScaleType.FIT_XY);
                    iv_merchantAddress.setMinimumHeight((int) (wm.getDefaultDisplay().getHeight() * 0.28));

                    iv_merchantAddress.setImageBitmap(bitmap);
                    iv_addMerchantAddress.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    //压缩成100k以下上传
    public static String getcomImageBase64(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 100是压缩率不压缩，如果是30就是压缩70%，压缩后的存放在baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        byte[] bytes = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Base64.encodeToString(bytes, Base64.DEFAULT);

    }

    //******************************************************************************************华丽的分割线**********************************************************************************************************
    private void initData() {
        if (list == null) {
            list = new ArrayList<>();
            list.add(IMG_ADD_TAG);
        }
        adapter = new GVAdapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).equals(IMG_ADD_TAG)) {
                    if (list.size() < IMG_COUNT) {
                        uploadImage(ImageTypeEnum.IDCARDIMAGE);
                        /*Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, 0);*/
                    } else
                        ToastUtils.showLong("最多只能选择7张照片！");
                }
            }
        });
        refreshAdapter();
    }

    private void refreshAdapter() {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (adapter == null) {
            adapter = new GVAdapter();
        }
        adapter.notifyDataSetChanged();
    }

    private class GVAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplication()).inflate(R.layout.activity_add_photo_gv_items, parent, false);
                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.main_gridView_item_photo);
                holder.checkBox = convertView.findViewById(R.id.main_gridView_item_cb);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String s = list.get(position);
            if (!s.equals(IMG_ADD_TAG)) {
                holder.checkBox.setVisibility(View.VISIBLE);
                holder.imageView.setImageBitmap(ImageTool.createImageThumbnail(s));
            } else {
                holder.checkBox.setVisibility(View.GONE);
                holder.imageView.setImageResource(R.drawable.app_logo);
            }
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    refreshAdapter();
                }
            });
            return convertView;
        }

        private class ViewHolder {
            ImageView imageView;
            CheckBox checkBox;
        }
    }
}
