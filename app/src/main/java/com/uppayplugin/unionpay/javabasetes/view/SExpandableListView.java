package com.uppayplugin.unionpay.javabasetes.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;


/**
 * 支持下拉刷新和加载更多的ExpandaleListview
 * <p>
 * Created by feilong.guo on 16/11/25.
 */

public class SExpandableListView extends ExpandableListView implements AbsListView.OnScrollListener {
    /**
     * 是否支持下拉刷新
     */
    private boolean pullRefreshEnabled = true;
    /**
     * 是否支持加载更多
     */
    private boolean loadingMoreEnabled = true;
    private ArrowRefreshHeader mRefreshHeader;

    private LoadingListener mLoadingListener;
    private View loadMoreView;
    private int lastY;
    private boolean isNoMore;
    private ProgressBar loadMorePb;
    private TextView loadMoreDesc;

    private Context mContext;

    private OnChildClickListener mOnChildClickListener;

    public SExpandableListView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public SExpandableListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
    }

    public SExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initSE(context);
        setOnScrollListener(this);
    }

    private void initSE(Context context) {
        /**
         * 这里是footer的填充,注意指定他的父亲为当前的listview,
         * 这里footer不用指定layoutparem是因为footer 在填充的时候已经指定了他的父view
         */
        this.mContext = context;
        loadMoreView = LayoutInflater.from(context).inflate(R.layout.item_footer_view, this, false);
        loadMorePb = (ProgressBar) loadMoreView.findViewById(R.id.pb_loading);
        loadMoreDesc = (TextView) loadMoreView.findViewById(R.id.tv_loadmore_desc);
    }

    public void setChildClickListener(OnChildClickListener onChildClickListener) {
        this.mOnChildClickListener = onChildClickListener;
    }

    @Override
    public void setAdapter(ExpandableListAdapter adapter) {
        if (pullRefreshEnabled) {
            mRefreshHeader = new ArrowRefreshHeader(getContext());
            mRefreshHeader.setProgressStyle(ProgressStyle.LineScale);
            /**
             * 注意一定要指定header的layoutparasm为 AbsListView,以为 header是 new出来的他默认的 layoutparm是当前header的类型,
             * 如果我们不修改的话会报错 类型转化异常,
             */
            mRefreshHeader.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            addHeaderView(mRefreshHeader);
        }
        if (loadingMoreEnabled) {
            addFooterView(loadMoreView);
        }
        super.setAdapter(adapter);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = (int) (ev.getY() - lastY);
                lastY = (int) ev.getY();
                setOnChildClickListener(mOnChildClickListener);
                //下拉刷新的条件,可刷新 并且 可见的position是0
                if (pullRefreshEnabled && getFirstVisiblePosition() == 0) {
                    mRefreshHeader.onMove(deltaY / 3);
                    if (mRefreshHeader.getVisibleHeight() > 0 && mRefreshHeader.getState() < ArrowRefreshHeader.STATE_REFRESHING) {
                        setOnChildClickListener(null);
                        return false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                reSetHeader();
                break;
            default:
                reSetHeader();
                break;


        }
        return super.onTouchEvent(ev);
    }

    /**
     * 重置header
     */
    private void reSetHeader() {
        lastY = -1; // reset
        if (pullRefreshEnabled) {
            if (mRefreshHeader.releaseAction()) {
                if (mLoadingListener != null) {
                    mLoadingListener.onRefresh();
                }
            }
        }
    }


    /**
     * 没有更多了
     *
     * @param noMore
     */
    public void setNoMore(boolean noMore) {
        this.isNoMore = noMore;
        noMoreAction();
    }

    private void noMoreAction() {
        loadMorePb.setVisibility(GONE);
        loadMoreDesc.setText(mContext.getString(R.string.nomore_loading));
    }

    public void loadMoreComp() {
        loadMorePb.setVisibility(GONE);
        loadMoreDesc.setText(mContext.getString(R.string.nomore_loading));
    }

    private void loadMoreAction() {
        loadMorePb.setVisibility(VISIBLE);
        loadMoreDesc.setText(mContext.getString(R.string.listview_loading));
        if (mLoadingListener != null) {
            mLoadingListener.onLoadMore();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case OnScrollListener.SCROLL_STATE_IDLE:
                if (loadingMoreEnabled && getLastVisiblePosition() == getAdapter().getCount() - 1) {
                    if (isNoMore) {
                        noMoreAction();
                    } else {
                        loadMoreAction();
                    }
                }
                break;
            case OnScrollListener.SCROLL_STATE_FLING:
                break;
            case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    /**
     * 加载更多回调
     */
    public interface LoadingListener {
        void onLoadMore();

        void onRefresh();
    }


    public void setmLoadingListener(LoadingListener mLoadingListener) {
        this.mLoadingListener = mLoadingListener;
    }

    /**
     * 设置是否支持下拉刷新
     *
     * @param pullRefreshEnabled
     */
    public void setPullRefreshEnabled(boolean pullRefreshEnabled) {
        this.pullRefreshEnabled = pullRefreshEnabled;
    }

    /**
     * 设置是否支持加载更多
     *
     * @param loadingMoreEnabled
     */
    public void setLoadingMoreEnabled(boolean loadingMoreEnabled) {
        this.loadingMoreEnabled = loadingMoreEnabled;
    }


    public void refreshComplete() {
        mRefreshHeader.refreshComplete();
    }
}
