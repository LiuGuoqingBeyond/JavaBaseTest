package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.bean.BankBranchBean;
import com.uppayplugin.unionpay.javabasetes.entity.response.WithDrawDetailRepModel;
import com.uppayplugin.unionpay.javabasetes.photoview.ImagPagerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImgViewActivity extends ToolBarActivity {
    @BindView(R.id.image)
    ImageView ic_launcher;

    @Override
    protected void initToolBar() {
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_img_view;
    }

    @Override
    protected void initViewsAndEvents() {
        //Imageloader初始化
        imageloaderInit();
        ButterKnife.bind(ImgViewActivity.this);
        ic_launcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.初始化图片集合
                StringBuilder newstringBuilder = new StringBuilder();
                InputStream inputStream = null;
                try {
                    inputStream = getResources().getAssets().open("ImgPath.json");
                    InputStreamReader isr = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(isr);
                    String jsonLine;
                    while ((jsonLine = reader.readLine()) != null) {
                        newstringBuilder.append(jsonLine);
                    }
                    reader.close();
                    isr.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String result = newstringBuilder.toString();
                Log.d("json", result);
                Gson gson = new Gson();
                WithDrawDetailRepModel common = gson.fromJson(result, WithDrawDetailRepModel.class);
                List<String> imgPaths = common.getData().getImgPaths();
                //传入点击的索引
                ImagPagerUtil imagPagerUtil = new ImagPagerUtil(ImgViewActivity.this, imgPaths, 2);//list:图片集合
                imagPagerUtil.show();
            }
        });
    }

    private void imageloaderInit() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
