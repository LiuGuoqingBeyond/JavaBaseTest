package com.uppayplugin.unionpay.javabasetes.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.ChooseBankAdapter;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.bean.BankBean;
import com.uppayplugin.unionpay.javabasetes.bean.BankBranchBean;
import com.uppayplugin.unionpay.javabasetes.bean.BankTestBean;
import com.uppayplugin.unionpay.javabasetes.utils.SearchView;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private List<BankTestBean> data;
    private ChooseBankAdapter mAdapter;
    private int BANKNAME = 100;
    private int BANKBRANCHNAME = 101;
    private String chooseBankStyle;
    private List<BankTestBean> list;

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
        /*String JsonData = CityJsonFileReader.getJson(this, "BankNameData.json");

        //将读出的字符串转换成JSONobject
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(getJson(JsonData,mContext));
            //获取JSONObject中的数组数据
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            Type listType = new TypeToken<List<BankTestBean>>() {
            }.getType();
            //这里的json是字符串类型 = jsonArray.toString();
            list = new Gson().fromJson(jsonArray.toString(), listType );
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        StringBuilder newstringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = getResources().getAssets().open("BankNameData.json");
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

        String result =  newstringBuilder .toString();
        Log.d("json",result);
        Gson gson = new Gson();
        BankBranchBean common = gson.fromJson(result, BankBranchBean.class);
        list = common.getData();

        /*ArrayList<BankBean.DataBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        ToastUtils.showLong(jsonBean.size()+"");*/

        //ListView数据源
//        data = TestBean.getInstance().getBankNameList();
        mAdapter.appendToList(list);

        //设置延时搜索，延时2秒
//        searchView.setWaitTime(2000);

        //设置搜索方法
        searchView.setSearchWay(new SearchView.SearchWay<BankTestBean>() {

            @Override
            public List<BankTestBean> getData() {
                //返回数据源
                return list;
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
    private void setListViewData(List<BankTestBean> list) {
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
    public static String getJson(String fileName,Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
