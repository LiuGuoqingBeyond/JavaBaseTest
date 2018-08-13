package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.testdemolib.Impl.CardListImpl;
import com.example.testdemolib.entity.respons.BankCardInfo;
import com.example.testdemolib.entity.respons.QueryCardListResponseModel;
import com.example.testdemolib.Interface.CardListInterface;
import com.example.testdemolib.Listener.CardListListener;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.BankListAdapter;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.config.Constant;
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetes.view.NewLineaGreyrLayoutManager;
import com.uppayplugin.unionpay.libcommon.des.DESCoder;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

public class CardListActivity extends ToolBarActivity implements SwipeItemClickListener {
    @BindView(R.id.list_internationalcard)
    SwipeMenuRecyclerView listInternationalcard;
    private PreferencesUtil prefs;
    private BankListAdapter bankInteradapter;
    private String randomKey;
    private String key;
    private String mobile;
    private String countryCode;
    private String sessionID;
    private String userKey;
    private CardListInterface cardListInterface;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_card_list;
    }

    @Override
    protected void initViewsAndEvents() {
        prefs = new PreferencesUtil(mContext);
        mobile = prefs.readPrefs(Constant.PREFES_MOBILE);
        countryCode = prefs.readPrefs(Constant.PREFES_COUNTRYCODE);
        sessionID = prefs.readPrefs(Constant.PREFES_SESSIONID);
        randomKey = prefs.readPrefs(Constant.PREFES_RANDOMKEY);
        key = prefs.readPrefs(Constant.PREFES_KEY);
        userKey = prefs.readPrefs(Constant.PREFES_IMEI_CODE);

        listInternationalcard.setLayoutManager(new LinearLayoutManager(mContext));
        listInternationalcard.addItemDecoration(new NewLineaGreyrLayoutManager(0, 20));
        listInternationalcard.setSwipeItemClickListener(this);
        listInternationalcard.setSwipeMenuCreator(mSwipeMenuCreator);
        try {
            bankInteradapter = new BankListAdapter(DESCoder.decryptMode(key, randomKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        listInternationalcard.setAdapter(bankInteradapter);
        querycardlist();
    }

    private void querycardlist() {
        cardListInterface = new CardListImpl();
        Map<String, String> map = new TreeMap<>();
        map.put("mobile", mobile);
        map.put("countryCode", countryCode);
        map.put("cardType", "0");
        map.put("version", "version2.1");
        map.put("userKey", userKey);
        map.put("sessionID", sessionID);

        cardListInterface.getMessage(mContext, map, cardListListener);
    }

    CardListListener cardListListener = new CardListListener() {
        @Override
        public void _onNext(QueryCardListResponseModel queryCardListResponseModel) {
            ArrayList<BankCardInfo> list = queryCardListResponseModel.getList();
            bankInteradapter.appendToList(list);
        }

        @Override
        public void _onError(String error) {
            ToastUtils.showLong(error);
        }
    };

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    public void onItemClick(View itemView, int position) {

    }

    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator mSwipeMenuCreator = (swipeLeftMenu, swipeRightMenu, viewType) -> {
    };
}
