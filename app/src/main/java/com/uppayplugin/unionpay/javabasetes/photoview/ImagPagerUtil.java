package com.uppayplugin.unionpay.javabasetes.photoview;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.BitMapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mine on 2015/9/24.
 */
public class ImagPagerUtil {
    private List<String> mPicList;
    private Activity mActivity;
    private Dialog dialog;
    private LazyViewPager mViewPager;
    private LinearLayout mLL_progress;
    private TextView tv_loadingmsg;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private TextView tv_img_current_index;
    private TextView tv_img_count;
    private TextView tv_content;
    private int curPosition = 1;

    public ImagPagerUtil(Activity activity, List<String> mPicList, int position) {
        this.mPicList = mPicList;
        this.mActivity = activity;
        this.curPosition = position;
        imageLoader = ImageLoader.getInstance();
        setOptions();
        init();
    }

    public ImagPagerUtil(Activity activity, String[] picarr, int position) {
        mPicList = new ArrayList<>();
        for (int i = 0; i < picarr.length; i++) {
            mPicList.add(picarr[i]);
        }
        this.mActivity = activity;
        this.curPosition = position;
        imageLoader = ImageLoader.getInstance();
        setOptions();
        init();
    }

    /**
     * 设置图片下方的文字
     *
     * @param str
     */
    public void setContentText(String str) {
        if (!TextUtils.isEmpty(str)) {
            tv_content.setText(str);
        }
    }

    public void show() {
        dialog.show();
    }

    private void init() {
        dialog = new Dialog(mActivity, R.style.fullDialog);
        RelativeLayout contentView = (RelativeLayout) View.inflate(mActivity, R.layout.view_dialogpager_img, null);
        mViewPager = getView(contentView, R.id.view_pager);
        mLL_progress = getView(contentView, R.id.vdi_ll_progress);
        tv_loadingmsg = getView(contentView, R.id.tv_loadingmsg);
        tv_img_current_index = getView(contentView, R.id.tv_img_current_index);
        tv_img_count = getView(contentView, R.id.tv_img_count);
//        tv_content = getView(contentView, R.id.tv_content);
        dialog.setContentView(contentView);

        tv_img_count.setText(mPicList.size() + "");
        tv_img_current_index.setText((curPosition + 1) + "");

        int size = mPicList.size();
        ArrayList<ImageView> imageViews = new ArrayList<>();
        PhotoView imageView = new PhotoView(mActivity);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        //如果不需要点击图片关闭的需求，可以去掉这个点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        for (int i = 0; i < size; i++) {
            imageViews.add(imageView);
        }
        initViewPager(imageViews);
    }

    private void initViewPager(ArrayList<ImageView> list) {
        mViewPager.setOnPageChangeListener(new LazyViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                curPosition = position;
                tv_img_current_index.setText("" + (curPosition + 1));
            }
        });

        MyImagPagerAdapter myImagPagerAdapter = new MyImagPagerAdapter(list);
        mViewPager.setAdapter(myImagPagerAdapter);
        mViewPager.setCurrentItem(curPosition);
    }

    class MyImagPagerAdapter extends PagerAdapter {
        ArrayList<ImageView> mList;

        public MyImagPagerAdapter(ArrayList<ImageView> mList) {
            this.mList = mList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mList.get(curPosition);
            showPic(imageView, mPicList.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public int getCount() {
            if (null == mList || mList.size() <= 0) {
                return 0;
            }
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    protected void setOptions() {
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    private void showPic(ImageView imageView, String url) {
        imageView.setImageBitmap(null);
        /*imageLoader.displayImage(url, imageView, options, animateFirstListener, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {
                float temp = (float) current / total;
                int progress = (int) (temp * 100);
                if (null != tv_loadingmsg) {
                    tv_loadingmsg.setText(progress + "%");
                }
            }
        });*/
        Bitmap bitmap = BitMapUtils.stringtoBitmap(url);
        Glide.with(mActivity).load(bitmap).into(imageView);

        dialog.show();
    }

    private class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            mLL_progress.setVisibility(View.GONE);
            tv_loadingmsg.setText("");
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                imageView.setImageBitmap(loadedImage);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static final <E extends View> E getView(View parent, int id) {
        try {
            return (E) parent.findViewById(id);
        } catch (ClassCastException ex) {
            Log.e("ImagPageUtil", "Could not cast View to concrete class \n" + ex.getMessage());
            throw ex;
        }
    }
}
