package com.uppayplugin.unionpay.javabasetes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.ChooseBankAdapter;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.bean.BankTestBean;
import com.uppayplugin.unionpay.javabasetes.bean.TestBean;
import com.uppayplugin.unionpay.javabasetes.utils.SearchView;

import java.util.List;

import butterknife.BindView;

public class BankCardActivity extends ToolBarActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private List<BankTestBean> data;
    private ChooseBankAdapter mAdapter;
    private int BANKNAME = 100;
    private int BANKBRANCHNAME = 101;
    private String chooseBankStyle;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_bank_card;
    }

    @Override
    protected void initViewsAndEvents() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ChooseBankAdapter();
        mRecyclerView.setAdapter(mAdapter);

        tvCancel.setOnClickListener(view -> finish());

        //ListView数据源
        data = TestBean.getInstance().getBankNameList();
        mAdapter.appendToList(data);

        //设置延时搜索，延时2秒
//        searchView.setWaitTime(2000);

        //设置搜索方法
        searchView.setSearchWay(new SearchView.SearchWay<BankTestBean>() {

            @Override
            public List<BankTestBean> getData() {
                //返回数据源
                return data;
            }

            @Override
            public boolean matchItem(BankTestBean item, String s) {
                //如果串item中包含串s，则匹配
                return item.getBankName().contains(s);
            }

            @Override
            public void update(List<BankTestBean> resultList) {
                //更新ListView的数据
                setListViewData(resultList);
            }
        });
        mAdapter.setmListener((v, bankTestBean, position) -> {
            if (chooseBankStyle.equals("0")) {
                Intent intent = new Intent();
                intent.putExtra("bankName", bankTestBean.bankName);
                setResult(BANKNAME, intent);
                finish();
            } else {
                Intent intent = new Intent();
                intent.putExtra("bankBranchName", bankTestBean.bankName);
                setResult(BANKBRANCHNAME, intent);
                finish();
            }
        });
    }

    /**
     * 设置ListView的内容
     */
    private void setListViewData(List<BankTestBean> list) {
        //先清除之前的在设置新的
        mAdapter.clear();
        mAdapter.appendToList(list);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
