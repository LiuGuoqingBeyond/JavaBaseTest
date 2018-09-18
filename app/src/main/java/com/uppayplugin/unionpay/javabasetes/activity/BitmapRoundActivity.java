package com.uppayplugin.unionpay.javabasetes.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BitmapRoundActivity extends ToolBarActivity {
    @BindView(R.id.img1)
    ImageView mImgRectRound;
    @BindView(R.id.img2)
    ImageView mImgRound;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_bitmap_round;
    }

    @Override
    protected void initViewsAndEvents() {
        ButterKnife.bind(this);
        rectRoundBitmap();
        roundBitmap();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    private void rectRoundBitmap() {
        //得到资源文件的BitMap
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.app_logo);
        //创建RoundedBitmapDrawable对象
        RoundedBitmapDrawable roundImg = RoundedBitmapDrawableFactory.create(getResources(), image);
        //抗锯齿
        roundImg.setAntiAlias(true);
        //设置圆角半径
        roundImg.setCornerRadius(50);
        //设置显示图片
        mImgRectRound.setImageDrawable(roundImg);
    }

    private void roundBitmap() {
        //如果是圆的时候，我们应该把bitmap图片进行剪切成正方形， 然后再设置圆角半径为正方形边长的一半即可
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.app_logo);
        Bitmap bitmap = null;
        //将长方形图片裁剪成正方形图片
        if (image.getWidth() == image.getHeight()) {
            bitmap = Bitmap.createBitmap(image, image.getWidth() / 2 - image.getHeight() / 2, 0, image.getHeight(), image.getHeight());
        } else {
            bitmap = Bitmap.createBitmap(image, 0, image.getHeight() / 2 - image.getWidth() / 2, image.getWidth(), image.getWidth());
        }
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        //圆角半径为正方形边长的一半
        roundedBitmapDrawable.setCornerRadius(bitmap.getWidth() / 2);
        //抗锯齿
        roundedBitmapDrawable.setAntiAlias(true);
        mImgRound.setImageDrawable(roundedBitmapDrawable);
    }
}
