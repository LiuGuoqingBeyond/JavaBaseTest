package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.BankCardInfo;
import com.uppayplugin.unionpay.javabasetes.adapter.NestedScrollViewAdapter;
import com.uppayplugin.unionpay.javabasetes.config.EventConstant;
import com.uppayplugin.unionpay.javabasetes.config.MessageEvent;
import com.uppayplugin.unionpay.javabasetes.presenter.BasePresenter;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.libcommon.inter.onItemClickListener;
import com.uppayplugin.unionpay.libcommon.inter.onItemLongClickListener;
import com.whty.xzfpos.base.AppToolBarActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NestedScrollViewActivity extends AppToolBarActivity {
    @BindView(R.id.rv_cycView)
    RecyclerView mReView;
    @BindView(R.id.float_button)
    FloatingActionButton mFloatingButton;

    List<BankCardInfo> integerList;
    private NestedScrollViewAdapter adapter;
    private BasePresenter presenter;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nested_scroll_view;
    }

    @Override
    protected void initViewsAndEvents() {
        presenter = new BasePresenter();
        integerList = getBankInfoList();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mReView.setLayoutManager(manager);
        adapter = new NestedScrollViewAdapter();
        adapter.appendToList(integerList);
        mReView.setAdapter(adapter);

        /*adapter.setmLongClickListener(new onItemLongClickListener<BankCardInfo>() {
            @Override
            public void onItemLongClick(BankCardInfo bankCardInfo, int position) {
                ToastUtils.showLong(position+"");
            }
        });*/
        adapter.setmClickListener(new onItemClickListener<BankCardInfo>() {
            @Override
            public void onItemClick(BankCardInfo bankCardInfo, int position) {
                ToastUtils.showLong(position+"");
            }
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**
     * 虚拟模拟数据
     *
     * @return
     */
    private List<BankCardInfo> getBankInfoList() {
        List<BankCardInfo> list = new ArrayList<BankCardInfo>();
        BankCardInfo bankCardInfo_one = new BankCardInfo();
        bankCardInfo_one.setBankName("中国银行");
        list.add(bankCardInfo_one);

        BankCardInfo bankCardInfo_two = new BankCardInfo();
        bankCardInfo_two.setBankName("集友银行");
        list.add(bankCardInfo_two);

        BankCardInfo bankCardInfo_three = new BankCardInfo();
        bankCardInfo_three.setBankName("集友银行");
        list.add(bankCardInfo_three);

        BankCardInfo bankCardInfo_four = new BankCardInfo();
        bankCardInfo_four.setBankName("集友银行");
        list.add(bankCardInfo_four);

        BankCardInfo bankCardInfo_five = new BankCardInfo();
        bankCardInfo_five.setBankName("集友银行");
        list.add(bankCardInfo_five);

        BankCardInfo bankCardInfo_six = new BankCardInfo();
        bankCardInfo_six.setBankName("集友银行");
        list.add(bankCardInfo_six);

        BankCardInfo bankCardInfo_seven = new BankCardInfo();
        bankCardInfo_seven.setBankName("集友银行");
        list.add(bankCardInfo_seven);

        BankCardInfo bankCardInfo_eight = new BankCardInfo();
        bankCardInfo_eight.setBankName("集友银行");
        list.add(bankCardInfo_eight);

        BankCardInfo bankCardInfo_night = new BankCardInfo();
        bankCardInfo_night.setBankName("集友银行");
        list.add(bankCardInfo_night);

        BankCardInfo bankCardInfo_ten = new BankCardInfo();
        bankCardInfo_ten.setBankName("集友银行");
        list.add(bankCardInfo_ten);

        BankCardInfo bankCardInfo_eleven = new BankCardInfo();
        bankCardInfo_eleven.setBankName("集友银行");
        list.add(bankCardInfo_eleven);

        BankCardInfo bankCardInfo_telve = new BankCardInfo();
        bankCardInfo_telve.setBankName("集友银行");
        list.add(bankCardInfo_telve);

        BankCardInfo bankCardInfo_thirting = new BankCardInfo();
        bankCardInfo_thirting.setBankName("集友银行");
        list.add(bankCardInfo_thirting);

        BankCardInfo bankCardInfo_fourting = new BankCardInfo();
        bankCardInfo_fourting.setBankName("集友银行");
        list.add(bankCardInfo_fourting);
        return list;
    }
    @OnClick(R.id.float_button)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.float_button:
                presenter.setCurrentPage(0);
                scrollToTop();
                break;
        }
    }

    private void scrollToTop() {
        switch (presenter.getCurrentPage()) {
            case 0:
                EventBus.getDefault().post(new MessageEvent(EventConstant.MAINSCROLLTOTOP, ""));
                if (EventConstant.MAINSCROLLTOTOP == 101){
                    mReView.setNestedScrollingEnabled(true);
                    mReView.smoothScrollToPosition(0);
                }
                break;
            case 1:
                EventBus.getDefault().post(new MessageEvent(EventConstant.KNOWLEDGESCROLLTOTOP, ""));
                break;
            case 2:
                EventBus.getDefault().post(new MessageEvent(EventConstant.PROJECTSCROLLTOTOP, ""));
                break;
        }
    }

}
