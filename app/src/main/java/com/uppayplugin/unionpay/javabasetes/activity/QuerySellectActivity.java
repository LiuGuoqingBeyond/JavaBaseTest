package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.testdemolib.Impl.QuerySellectImpl;
import com.example.testdemolib.Interface.QuerySellectInterface;
import com.example.testdemolib.Listener.QuerySellectListener;
import com.example.testdemolib.entity.respons.QrCodePayInfoResponseModel;
import com.example.testdemolib.entity.respons.TradeRecordAllRespone;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.TransactionRecordsAdapter;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.config.Constant;
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

public class QuerySellectActivity extends ToolBarActivity {
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.trade_record_refreshLayout)
    SmartRefreshLayout tradeRecordRefreshLayout;
    private String mobile = "";
    private String countryCode = "";
    private String sessionID = "";
    private String userKey = "";
    private PreferencesUtil prefes;
    private boolean isNotLoadAll = false;
    private boolean isRefresh = true;
    private int beginNum = 1;
    TransactionRecordsAdapter mRecordsAdapter;
    private QuerySellectInterface querySellectInterface;
    private List<QrCodePayInfoResponseModel> models;
    private LinkedHashMap<String, TransactionRecordsAdapter.HeaderModel> mTitles;
    StickyRecyclerHeadersDecoration mRecyclerHeadersDecoration;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_query_sellect;
    }

    @Override
    protected void initViewsAndEvents() {
        prefes = new PreferencesUtil(this);
        mobile = prefes.readPrefs(Constant.PREFES_MOBILE);
        countryCode = prefes.readPrefs(Constant.PREFES_COUNTRYCODE);
        sessionID = prefes.readPrefs(Constant.PREFES_SESSIONID);
        userKey = prefes.readPrefs(Constant.PREFES_IMEI_CODE);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        mRecordsAdapter = new TransactionRecordsAdapter();
        rvList.setAdapter(mRecordsAdapter);
        mRecyclerHeadersDecoration = new StickyRecyclerHeadersDecoration(mRecordsAdapter);

        inintRefresh();
        //先是查询总条目,只有当点击筛选时才走按月查询功能
        RequestAllMonth();
    }

    private void RequestAllMonth() {
        querySellectInterface = new QuerySellectImpl();
        Map<String, String> map = new TreeMap<>();
        map.put("mobile", mobile);
        map.put("countryCode", countryCode);
        map.put("sessionID", sessionID);
        map.put("startDate", "new");
        map.put("userKey", userKey);
        map.put("orderId", "");
        map.put("beginNum", beginNum + "");
        map.put("version", "version2.1");
        // zhanghuan 暂时先改为　100 条
        map.put("queryRows", "20");
        querySellectInterface.getMessage(mContext,map,querySellectListener);
    }
    QuerySellectListener querySellectListener = new QuerySellectListener() {
        @Override
        public void _onNext(TradeRecordAllRespone tradeRecordAllRespone) {
            transformData(tradeRecordAllRespone);
        }

        @Override
        public void _onError(String error) {

        }
    };

    private void transformData(TradeRecordAllRespone tradeRecordAllRespone) {
        models = new ArrayList<>();
        mTitles = mRecordsAdapter.getStickHeader();
        String lastKeyIndex = "";
        TransactionRecordsAdapter.HeaderModel lastHeaderModel = null;
        for (String s1 : mTitles.keySet()) {
            lastKeyIndex = s1;
            lastHeaderModel = mTitles.get(s1);
        }

        boolean isAppend = lastHeaderModel != null;

        int lastIndex = TextUtils.isEmpty(lastKeyIndex) ? 0 : Integer.valueOf(lastKeyIndex);
        int index = 0;
        String lastData = isAppend ? lastHeaderModel.date : "";
        int currentDataSize = isAppend ? Integer.valueOf(lastHeaderModel.orderNum) : 0;
        boolean isFirstAdd = mRecordsAdapter.getItemCount() == 0;
        for (QrCodePayInfoResponseModel bigList : tradeRecordAllRespone.getList()) {
            /*
             * 参数一、头部显示的位置         这个需要根据上月所有条数加载完了之后才显示下月该头部的位置
             * 参数二、头部此时的时间截取     下次显示头部时月份的时间
             * 参数三、头部当月的交易笔数     每次加载未完，下次加载，累加笔数
             */
            String date = bigList.getOrderTime().substring(0, 4) + "-" + bigList.getOrderTime().substring(4, 6);

            if (isFirstAdd) {
                lastData = date;
            }

            if (TextUtils.equals(lastData, date)) {
                currentDataSize++;
            }

            isFirstAdd = false;

            if (!TextUtils.equals(lastData, date) || index == tradeRecordAllRespone.getList().size() - 1) {
                lastHeaderModel = new TransactionRecordsAdapter.HeaderModel(lastData
                        , String.valueOf(currentDataSize));
                mTitles.put(String.valueOf(lastIndex), lastHeaderModel);
                lastIndex = lastIndex + currentDataSize;
                currentDataSize = 1;
                lastData = date;
            }


            index++;

            models.add(bigList);
        }


        /*mRecordsAdapter.setListener((v, qrCodePayInfoResponseModel, position) -> {
            if (NetUtil.checkNet(mContext)) {
                try {
                    requestOrderDetailInfo(qrCodePayInfoResponseModel.getOrderId(), time, String.valueOf(beginNum));
                } catch (Exception e) {
                    Logger.e(e.getMessage());
                }
            }
        });*/

        rvList.removeItemDecoration(mRecyclerHeadersDecoration);
        rvList.addItemDecoration(mRecyclerHeadersDecoration);
        mRecordsAdapter.setStickHeader(mTitles);
        mRecordsAdapter.appendToList(models);
        mRecyclerHeadersDecoration.invalidateHeaders();
        setVisiable();
    }

    /**
     * 上拉加载更多相关逻辑
     */
    private void inintRefresh() {
        tradeRecordRefreshLayout.setOnRefreshListener(refreshlayout -> {
            isRefresh = true;
            mRecordsAdapter.clear();
            mRecordsAdapter.getStickHeader().clear();
            if (!isNotLoadAll) {
                beginNum = 1;
                RequestAllMonth();
            } else {
                beginNum = 1;
//                RequestMonth(time);//先注掉
            }
            refreshlayout.finishRefresh(1500);
        });

        tradeRecordRefreshLayout.setOnLoadmoreListener(refreshlayout -> {
            isRefresh = false;
            if (!isNotLoadAll) {
                beginNum++;
                RequestAllMonth();
                refreshlayout.finishLoadmore(1500);
            } else {
                beginNum++;
//                RequestMonth(time);//先注掉
                refreshlayout.finishLoadmore(1500);
            }

        });
        tradeRecordRefreshLayout.setEnableLoadmore(true);
        tradeRecordRefreshLayout.setRefreshHeader(new WaterDropHeader(this));
        tradeRecordRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**
     * 设置隐藏
     */
    private void setVisiable() {
        if (mRecordsAdapter.getItemCount() < 1) {
//            tradeNoRecord.setVisibility(View.VISIBLE);
            tradeRecordRefreshLayout.setVisibility(View.GONE);
        } else {
//            tradeNoRecord.setVisibility(View.GONE);
            tradeRecordRefreshLayout.setVisibility(View.VISIBLE);

        }
    }
}
