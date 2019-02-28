package com.uppayplugin.unionpay.javabasetes.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.scaner.CaptureActivityHandler;
import com.zbar.lib.CameraManager;
import com.zbar.lib.InactivityTimer;

import java.io.IOException;

public class NewScanActivity extends BaseActivity implements SurfaceHolder.Callback, View.OnClickListener {
    private RelativeLayout mCropLayout = null;
    private RelativeLayout mContainer = null;
    private static final float BEEP_VOLUME = 0.50f;
    private boolean hasSurface;
    private boolean playBeep;
    private int x = 0;
    private int y = 0;
    private int cropWidth = 0;
    private int cropHeight = 0;
    private CaptureActivityHandler handler;
    private MediaPlayer mediaPlayer;
    private InactivityTimer inactivityTimer;
    private boolean vibrate;
    private ImageView imgLight;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_scan);
        findView();
        initData();
        initView();
    }

    @Override
    protected void findView() {
        mContainer = findViewById(R.id.capture_containter);
        mCropLayout = findViewById(R.id.capture_crop_layout);
        imgLight = findViewById(R.id.img_light);
        imgLight.setOnClickListener(this);
        //请求Camera权限 与 文件读写 权限
        if (ContextCompat.checkSelfPermission(NewScanActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(NewScanActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NewScanActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        CameraManager.init(getApplication());//初始化 CameraManager
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
    @SuppressWarnings("deprecation")
    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = findViewById(R.id.capture_preview);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);//Camera初始化
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }
    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }
    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);

            Point point = CameraManager.get().getCameraResolution();
            int width = point.y;
            int height = point.x;
            int x = mCropLayout.getLeft() * width / mContainer.getWidth();
            int y = mCropLayout.getTop() * height / mContainer.getHeight();
            int cropWidth = mCropLayout.getWidth() * width
                    / mContainer.getWidth();
            int cropHeight = mCropLayout.getHeight() * height
                    / mContainer.getHeight();
            setX(x);
            setY(y);
            setCropWidth(cropWidth);
            setCropHeight(cropHeight);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(NewScanActivity.this);
        }
    }
    //----------------------------------------------------------------------------------------------解析结果 及 后续处理 start
    private String mResult;

    public void handleDecode(String result) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        this.mResult = result;
        Toast.makeText(NewScanActivity.this, result, Toast.LENGTH_SHORT).show();
        if (null != handler) {
            handler.sendEmptyMessage(R.id.restart_preview);
        }
    }
    //==============================================================================================解析结果 及 后续处理 end


    //----------------------------------------------------------------------------------------------扫描成功之后的振动与声音提示 start
    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.seekTo(0);
                }
            });

            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }
    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCropWidth() {
        return cropWidth;
    }

    public void setCropWidth(int cropWidth) {
        this.cropWidth = cropWidth;
    }

    public int getCropHeight() {
        return cropHeight;
    }

    public void setCropHeight(int cropHeight) {
        this.cropHeight = cropHeight;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(surfaceHolder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        hasSurface = false;
    }
    public Handler getHandler() {
        return handler;
    }

    private void light() {
        if (flag) {
            flag = false;
            // 开闪光灯
            CameraManager.get().openLight();
        } else {
            flag = true;
            // 关闪光灯
            CameraManager.get().offLight();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_light:
                light();
                break;
        }
    }
}
