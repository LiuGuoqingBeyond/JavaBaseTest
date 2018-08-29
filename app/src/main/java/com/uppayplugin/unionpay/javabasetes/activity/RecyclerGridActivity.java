package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.GridAdapter;
import com.uppayplugin.unionpay.javabasetes.entity.response.GridRepModel;
import com.uppayplugin.unionpay.javabasetes.utils.DividerGridItemDecoration;
import com.whty.xzfpos.base.AppToolBarActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class RecyclerGridActivity extends AppToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_radiu_button;
    }

    @Override
    protected void initViewsAndEvents() {
        ArrayList<GridRepModel> dataList = new ArrayList<>();
        GridAdapter gridAdapter = new GridAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration((new DividerGridItemDecoration(mContext)));
        recyclerView.setAdapter(gridAdapter);

        GridRepModel gridRepModel1 = new GridRepModel();
        gridRepModel1.setImg(getResources().getDrawable(R.drawable.icon_shouyintai));
        gridRepModel1.setName("收银台");
        dataList.add(gridRepModel1);
        GridRepModel gridRepModel2 = new GridRepModel();
        gridRepModel2.setImg(getResources().getDrawable(R.drawable.icon_zhifuguanli));
        gridRepModel2.setName("支付管理");
        dataList.add(gridRepModel2);
        GridRepModel gridRepModel3 = new GridRepModel();
        gridRepModel3.setImg(getResources().getDrawable(R.drawable.icon_jiaoyichaxun));
        gridRepModel3.setName("交易查询");
        dataList.add(gridRepModel3);
        GridRepModel gridRepModel4 = new GridRepModel();
        gridRepModel4.setImg(getResources().getDrawable(R.drawable.icon_yushouquan));
        gridRepModel4.setName("电子发票");
        dataList.add(gridRepModel4);
        GridRepModel gridRepModel5 = new GridRepModel();
        gridRepModel5.setImg(getResources().getDrawable(R.drawable.icon_yushouquan));
        gridRepModel5.setName("预授权");
        dataList.add(gridRepModel5);
        GridRepModel gridRepModel6 = new GridRepModel();
        gridRepModel6.setImg(getResources().getDrawable(R.drawable.icon_shezhi));
        gridRepModel6.setName("设置");
        dataList.add(gridRepModel6);
        GridRepModel gridRepModel7 = new GridRepModel();
        gridRepModel7.setImg(getResources().getDrawable(R.drawable.icon_guanyuwomen));
        gridRepModel7.setName("关于我们");
        dataList.add(gridRepModel7);
        GridRepModel gridRepModel8 = new GridRepModel();
        gridRepModel8.setImg(getResources().getDrawable(R.drawable.icon_guanyuwomen));
        gridRepModel8.setName("哈哈哈");
        dataList.add(gridRepModel8);
        GridRepModel gridRepModel9 = new GridRepModel();
        gridRepModel9.setImg(getResources().getDrawable(R.drawable.icon_guanyuwomen));
        gridRepModel9.setName("呵呵呵");
        dataList.add(gridRepModel9);

        gridAdapter.appendToList(dataList);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
