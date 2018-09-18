package com.uppayplugin.unionpay.javabasetes.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.GridImageAdapter;
import com.uppayplugin.unionpay.javabasetes.entity.LoginRequestModel;
import com.uppayplugin.unionpay.javabasetes.entity.auth.ImageTypeEnum;
import com.uppayplugin.unionpay.javabasetes.entity.request.LoginReqModel;
import com.uppayplugin.unionpay.javabasetes.entity.response.LoginRepModel;
import com.uppayplugin.unionpay.javabasetes.utils.FullyGridLayoutManager;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetes.utils.mapbean.TransMapToBeanUtils;
import com.whty.xzfpos.base.AppToolBarActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

import static com.uppayplugin.unionpay.javabasetes.utils.PayUtils.trimToEmpty;

public class ChooseSignActivity extends AppToolBarActivity {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.img_view)
    ImageView imgView;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    private GridImageAdapter adapter;
    private int themeId;
    private int chooseMode = PictureMimeType.ofImage();
    private List<LocalMedia> selectList = new ArrayList<>();
    protected PictureSelectionConfig config;
    private String cameraPath;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private List<String> photos;
    private Bitmap bitmap;
    private List<String> photoList;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_choose_sign;
    }

    @Override
    protected void initViewsAndEvents() {
        themeId = R.style.picture_QQ_style;
        FullyGridLayoutManager manager = new FullyGridLayoutManager(ChooseSignActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(ChooseSignActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(9);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            PictureSelector.create(ChooseSignActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(ChooseSignActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(ChooseSignActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        photos = new ArrayList<>();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < selectList.size(); i++) {
                    Logger.d("photo数组=="+selectList.get(i).getCompressPath().toString());
                    bitmap = BitmapFactory.decodeFile(selectList.get(i).getCompressPath().toString());
//                    imgView.setImageBitmap(bitmap);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Logger.e("图片大小="+bitmap.getRowBytes() * bitmap.getHeight());
                    }
                }
                photoList = new ArrayList<>();
                for(LocalMedia s:selectList){
                    photoList.add(s.getCompressPath());
                }
                for (int i = 0; i < photoList.size(); i++) {
                    Logger.e("photoList"+photoList.get(i).toString());
                }
                //保存图片
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).start();

                /*TreeMap map = new TreeMap<String, String>();
                map.put("account", "dlhe");
                map.put("password", "123123");
                map.put("sign", getMd5String(map, "XxJ2A2S_01D9LJ99AD9"));
                LoginRequestModel.getloginRequest((LoginReqModel) TransMapToBeanUtils.mapToBean(map, LoginReqModel.class))
                        .subscribe(new ProgressSubscriber<LoginRepModel>(mContext) {
                            @Override
                            protected void _onNext(LoginRepModel loginRepModel) {
                                showToast(loginRepModel.toString());
                            }

                            @Override
                            protected void _onError(String message) {
                            }
                        });*/
            }
        });
    }

    /**
     * List转String
     *
     * @param mList
     * @return
     */
    public static String listToString(List<String> mList) {
        String convertedListStr = "";
        if (null != mList && mList.size() > 0) {
            String[] mListArray = mList.toArray(new String[mList.size()]);
            for (int i = 0; i < mListArray.length; i++) {
                if (i < mListArray.length - 1) {
                    convertedListStr += mListArray[i] + ",";
                } else {
                    convertedListStr += mListArray[i];
                }
            }
            return convertedListStr;
        } else return "List is null!!!";
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            uploadImage();
        }

    };

    public String getMd5String(Map<String, String> params, String signature) {
        StringBuilder buffer = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!first) {
                buffer.append("&");
            }
            first = false;

            buffer.append(entry.getKey());
            buffer.append('=');
            if (entry.getValue() != null) {
                buffer.append(trimToEmpty(entry.getValue()));
            }
        }
        return getMD5(buffer.toString() + "&" + signature).toUpperCase(Locale.getDefault());
    }

    public static String getMD5(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHashString(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    ToastUtils.showLong(selectList.size() + "");
                    for (LocalMedia media : selectList) {
                        photos.add(media.getCompressPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    /**
     * start to camera、preview、crop
     */
    public void startOpenCamera() {
        // 单独拍照
        PictureSelector.create(ChooseSignActivity.this)
                .openCamera(chooseMode)// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(themeId)// 主题样式设置 具体参考 values/styles
                .maxSelectNum(9)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
//                .previewVideo(cb_preview_video.isChecked())// 是否可预览视频
//                .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
//                .isCamera(cb_isCamera.isChecked())// 是否显示拍照按钮
//                .enableCrop(cb_crop.isChecked())// 是否裁剪
                .compress(true)// 是否压缩
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
//                .isGif(cb_isGif.isChecked())// 是否显示gif图片
//                .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
//                .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
//                .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//                .openClickSound(cb_voice.isChecked())// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
                .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .cropCompressQuality(60)// 裁剪压缩质量 默认为100
//                .minimumCompressSize(100)// 小于100kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()////显示多少秒以内的视频or音频也可适用
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    /**
     * 生成uri
     *
     * @param cameraFile
     * @return
     */
    private Uri parUri(File cameraFile) {
        Uri imageUri;
        String authority = getPackageName() + ".provider";
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            //通过FileProvider创建一个content类型的Uri
            imageUri = FileProvider.getUriForFile(mContext, authority, cameraFile);
        } else {
            imageUri = Uri.fromFile(cameraFile);
        }
        return imageUri;
    }

    /**
     * 上传照片信息
     */
    public void uploadImage() {
        try {
//            this.imageTypeEnum = imageTypeEnums;
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
//                        gotoCamera();
                        startOpenCamera();
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

    private void gotoPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(ChooseSignActivity.this)
                .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)//默认多选。
//                        .selectionMode(cb_choose_mode.isChecked() ?
//                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
//                        .previewVideo(cb_preview_video.isChecked())// 是否可预览视频
//                        .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
//                        .isCamera(cb_isCamera.isChecked())// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
//                        .enableCrop(cb_crop.isChecked())// 是否裁剪
                .compress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                        .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
//                        .isGif(cb_isGif.isChecked())// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
//                        .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
//                        .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                        .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//                        .openClickSound(cb_voice.isChecked())// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
                //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .cropCompressQuality(20)// 裁剪压缩质量 默认100
//                .minimumCompressSize(100)// 小于100kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled(true) // 裁剪是否可旋转图片
                //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
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
                                /*setImageValue(bitmap);
                                //此处后面可以将bitMap转为二进制上传后台网络
                                uploadImageToWeb(bitmap);*/
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

                                /*setImageValue(bitmap);
                                //此处后面可以将bitMap转为二进制上传后台网络
                                uploadImageToWeb(bitmap);*/
                            }
                        }
                    }
                });
    }
}
