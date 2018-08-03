package com.uppayplugin.unionpay.javabasetest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;
import com.sinopaylib.api.APIProcxy;
import com.sinopaylib.entity.respons.RecordCountEntity;
import com.sinopaylib.entity.respons.TradeRecordListRepModel;
import com.sinopaylib.inter.TradeRecordInter;
import com.sinopaylib.listener.TradeRecordListener;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.adapter.RecordAdapter;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetest.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetest.view.SExpandableListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TradeRecordActivity extends ToolBarActivity {
    @BindView(R.id.trade_no_record)
    RelativeLayout trade_no_record;
    @BindView(R.id.ep_record_list)
    SExpandableListView expandableListView;
    private String month;
    private PreferencesUtil prefes;
    private boolean isPull;
    private int loadCount;
    private String mobile;
    private String countryCode;
    private String sessionID;
    private TradeRecordInter tradeRecordInter;
    private ArrayList<RecordCountEntity> dataList;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isPull) {
                expandableListView.refreshComplete();
            }
            RequestTradeReocrdByMonth(month);
        }
    };
    private RecordAdapter recordAdapter;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_trade_record;
    }

    @Override
    protected void initViewsAndEvents() {
        ButterKnife.bind(this);
        prefes = new PreferencesUtil(mContext);
        trade_no_record.setVisibility(View.GONE);
        expandableListView.setVisibility(View.VISIBLE);
        mobile = prefes.readPrefs(Constant.PREFES_MOBILE);
        countryCode = prefes.readPrefs(mobile + Constant.PREFES_COUNTRYCODE);
        countryCode = TextUtils.isEmpty(countryCode) ? "86" : countryCode;
        sessionID = prefes.readPrefs(Constant.PREFES_SESSIONID);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = sdf.format(new Date());
            month = str.substring(0, 7).replace("-", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initViewDataFirstTime();
        RequestTradeReocrdByMonth(month);
    }

    private void RequestTradeReocrdByMonth(String month) {
        expandableListView.setOnChildClickListener(onChildClickListener);
        Map<String, String> map = new TreeMap<>();
        map.put("mobile", mobile);
        map.put("countryCode", countryCode);
        map.put("sessionID", sessionID);
        map.put("month", "201806");
        tradeRecordInter = APIProcxy.getInstance().getmRecord();
        tradeRecordInter.getTradeRecord(mContext,map,tradeRecordListener);
    }
    TradeRecordListener tradeRecordListener = new TradeRecordListener() {
        @Override
        public void _onNext(TradeRecordListRepModel tradeRecordListRepModel) {
            if (tradeRecordListRepModel.isOk()){
                dataList = tradeRecordListRepModel.list;
                if(null != dataList && dataList.size() >0) {
                    trade_no_record.setVisibility(View.GONE);
                    expandableListView.setVisibility(View.VISIBLE);
                    initViewData(dataList);
                } else {
                    trade_no_record.setVisibility(View.VISIBLE);
                    expandableListView.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public void _onError(String error) {
            ToastUtils.showLong(error);
        }
    };

    ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            // Toast.makeText(mContext, "-----点击了组----groupPosition" + groupPosition + "---孩子--childPosition" + childPosition, Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            try {
                /*String orderID = dataList.get(groupPosition).getDailyList().get(childPosition).getOrderID();
                if(!TextUtils.isEmpty(orderID) && orderID.toLowerCase().startsWith("c")) {
                    bundle.putString("orderID", orderID);
                    openActivity(RevokeDetailActivity.class, bundle);
                } else {
                    bundle.putString("orderID", orderID);
                    openActivity(TradeRecordDetailActivity.class, bundle);
                }*/
            } catch (Exception e) {
                Logger.e(e.getMessage());
            }
            return false;
        }
    };
    /**
     * 刷新listView 数据源
     *
     * @param dataList 数据源
     */
    public void initViewData(ArrayList<RecordCountEntity> dataList) {
        try {
            recordAdapter.setData(dataList);
            // expandableListView.expandGroup(0);
            recordAdapter.notifyDataSetChanged();
            try {
                if (null != dataList && dataList.size() > 0) {
                    for(int i = 0;i<dataList.size();i++) {
                        if(null != dataList.get(i).dailyList && dataList.get(i).dailyList.size() > 0) {
                            expandableListView.expandGroup(i);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    /**
     * 第一次加载listView视图
     */
    public void initViewDataFirstTime() {
        try {
            recordAdapter = new RecordAdapter(mContext);
            recordAdapter.setData(dataList);

            // 在设置适配器之前设置是否支持下拉刷新
            expandableListView.setLoadingMoreEnabled(false);
            expandableListView.setPullRefreshEnabled(true);
            expandableListView.setAdapter(recordAdapter);
            expandableListView.setChildClickListener(onChildClickListener);
            expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                    //返回true消耗掉组点击事件,从拦截了折叠的事件,
                    ImageView iconView = view.findViewById(R.id.iv_floder_gray);
                    if (!parent.isGroupExpanded(groupPosition)) {
                        iconView.setBackground(getResources().getDrawable(R.drawable.icon_unfold_grey));
                    } else {
                        iconView.setBackground(getResources().getDrawable(R.drawable.icon_fold_grey));
                    }
                    return false;
                }
            });

            expandableListView.setmLoadingListener(new SExpandableListView.LoadingListener() {
                @Override
                public void onLoadMore() {
                    isPull = false;
                    loadCount++;
                    Message msg = handler.obtainMessage();
                    msg.arg1 = loadCount;
                    handler.sendMessageDelayed(msg, 2000);
                }

                @Override
                public void onRefresh() {
                    isPull = true;
                    loadCount++;
                    Message msg = handler.obtainMessage();
                    msg.arg1 = loadCount;
                    handler.sendMessageDelayed(msg, 500);
                }
            });
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
