package com.uppayplugin.unionpay.javabasetes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.ChooseBankAdapter;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.bean.BankBean;
import com.uppayplugin.unionpay.javabasetes.bean.BankTestBean;
import com.uppayplugin.unionpay.javabasetes.bean.TestBean;
import com.uppayplugin.unionpay.javabasetes.utils.SearchView;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetes.utils.file.CityJsonFileReader;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BankCardActivity extends ToolBarActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private List<BankBean.DataBean> data;
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

        //  获取json数据
        String JsonData = CityJsonFileReader.getJson(this, "BankNameData.json");
        ArrayList<BankBean.DataBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        ToastUtils.showLong(jsonBean.size()+"");

        //ListView数据源
//        data = TestBean.getInstance().getBankNameList();
        mAdapter.appendToList(jsonBean);

        //设置延时搜索，延时2秒
//        searchView.setWaitTime(2000);

        //设置搜索方法
        searchView.setSearchWay(new SearchView.SearchWay<BankBean.DataBean>() {

            @Override
            public List<BankBean.DataBean> getData() {
                //返回数据源
                return data;
            }

            @Override
            public boolean matchItem(BankBean.DataBean item, String s) {
                //如果串item中包含串s，则匹配
                return item.getBankName().contains(s);
            }

            @Override
            public void update(List<BankBean.DataBean> resultList) {
                //更新ListView的数据
                setListViewData(resultList);
            }
        });
        mAdapter.setmListener((v, bankTestBean, position) -> {
            if (chooseBankStyle.equals("0")) {
                Intent intent = new Intent();
                intent.putExtra("bankName", bankTestBean.getBankName());
                setResult(BANKNAME, intent);
                finish();
            } else {
                Intent intent = new Intent();
                intent.putExtra("bankBranchName", bankTestBean.getBankName());
                setResult(BANKBRANCHNAME, intent);
                finish();
            }
        });
    }

    /**
     * 设置ListView的内容
     */
    private void setListViewData(List<BankBean.DataBean> list) {
        //先清除之前的在设置新的
        mAdapter.clear();
        mAdapter.appendToList(list);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    public ArrayList<BankBean.DataBean> parseData(String result) {//Gson 解析
        ArrayList<BankBean.DataBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                BankBean.DataBean entity = gson.fromJson(data.optJSONObject(i).toString(), BankBean.DataBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}
