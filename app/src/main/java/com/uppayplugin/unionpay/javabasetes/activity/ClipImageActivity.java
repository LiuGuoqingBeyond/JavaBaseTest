package com.uppayplugin.unionpay.javabasetes.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.personal.ClipViewLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ClipImageActivity extends AppCompatActivity {

    private ClipViewLayout clipViewLayout1,clipViewLayout2;
    private TextView btnCancel,btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_image);

        clipViewLayout1 = findViewById(R.id.clipViewLayout1);
        clipViewLayout2 = findViewById(R.id.clipViewLayout2);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOK = findViewById(R.id.bt_ok);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateUriAndReturn();
            }
        });
    }

    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndReturn() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap = clipViewLayout1.clip();
        if (zoomedCropBitmap == null) {
            Log.e("android", "zoomedCropBitmap == null");
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent();
            intent.setData(mSaveUri);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        clipViewLayout1.setVisibility(View.VISIBLE);
        clipViewLayout2.setVisibility(View.GONE);
        //设置图片资源
        clipViewLayout1.setImageSrc(getIntent().getData());
    }
}
