package com.uppayplugin.unionpay.javabasetes.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.config.Constant;
import com.uppayplugin.unionpay.javabasetes.entity.response.CountyCodeListRepModel;
import com.uppayplugin.unionpay.javabasetes.entity.response.CountyCodeModel;
import com.uppayplugin.unionpay.javabasetes.utils.JSONUtil;
import com.uppayplugin.unionpay.javabasetes.utils.PayUtils;
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetes.utils.PublicMethodUtils;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtil;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetes.utils.net.HTTPSRequestUtils;
import com.uppayplugin.unionpay.javabasetes.utils.net.NetUtil;
import com.uppayplugin.unionpay.libcommon.rsa.RSACoder;
import com.whty.xzfpos.base.AppToolBarActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.uppayplugin.unionpay.javabasetes.config.Constant.privateKey;

public class TagFlowActivity extends AppToolBarActivity {
    @BindView(R.id.flow_search)
    TagFlowLayout mFlowSearch;
    @BindView(R.id.article_toolbar)
    Toolbar mArticleToolbar;
    private ArrayList<CountyCodeModel>  hotList;
    private PreferencesUtil prefs;

    @Override
    protected void initToolBar() {
        setSupportActionBar(mArticleToolbar);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tag_flow;
    }

    @Override
    protected void initViewsAndEvents() {
        prefs = new PreferencesUtil(mContext);
//        initData();
        hotList = new ArrayList<>();
        hotList = new Gson().fromJson("{\"status\":\"0\",\"msg\":\"Success\",\"list\":[{\"countryCode\":\"60\",\"countryLog\":\"MAS\",\"chnDesc\":\"马来西亚\",\"engDesc\":\"Malaysia\",\"fonDesc\":\"馬來西亞\"},{\"countryCode\":\"62\",\"countryLog\":\"INA\",\"chnDesc\":\"印度尼西亚\",\"engDesc\":\"Indonesia\",\"fonDesc\":\"印度尼西亞\"},{\"countryCode\":\"63\",\"countryLog\":\"PH\",\"chnDesc\":\"菲律宾\",\"engDesc\":\"Philippines\",\"fonDesc\":\"菲律賓\"},{\"countryCode\":\"65\",\"countryLog\":\"SG\",\"chnDesc\":\"新加坡\",\"engDesc\":\"Singapore\",\"fonDesc\":\"新加坡\"},{\"countryCode\":\"66\",\"countryLog\":\"TH\",\"chnDesc\":\"泰国\",\"engDesc\":\"Thailand\",\"fonDesc\":\"泰國\"},{\"countryCode\":\"84\",\"countryLog\":\"VN\",\"chnDesc\":\"越南\",\"engDesc\":\"Vietnam\",\"fonDesc\":\"越南\"},{\"countryCode\":\"852\",\"countryLog\":\"HK\",\"chnDesc\":\"香港\",\"engDesc\":\"Hongkong\",\"fonDesc\":\"香港\"},{\"countryCode\":\"853\",\"countryLog\":\"MO\",\"chnDesc\":\"澳门\",\"engDesc\":\"Macao\",\"fonDesc\":\"澳門\"},{\"countryCode\":\"855\",\"countryLog\":\"KHM\",\"chnDesc\":\"柬埔寨\",\"engDesc\":\"Cambodia\",\"fonDesc\":\"柬埔寨\"},{\"countryCode\":\"86\",\"countryLog\":\"CN\",\"chnDesc\":\"中国\",\"engDesc\":\"China\",\"fonDesc\":\"中國\"},{\"countryCode\":\"95\",\"countryLog\":\"MM\",\"chnDesc\":\"缅甸\",\"engDesc\":\"Myanmar\",\"fonDesc\":\"緬甸\"}]}", CountyCodeListRepModel.class).list;
        initFlowLayout();
    }

    private void initFlowLayout() {
        TagAdapter<CountyCodeModel> tagAdapter = new TagAdapter<CountyCodeModel>(hotList) {
            @Override
            public View getView(FlowLayout parent, int position, CountyCodeModel countyCodeModel) {
                TextView text = (TextView) getLayoutInflater().inflate(R.layout.item_flow_layout, null);
                String name = countyCodeModel.chnDesc;
                text.setText(name);
                text.setTextColor(PublicMethodUtils.randomColor());
                return text;
            }
        };
        mFlowSearch.setAdapter(tagAdapter);
        mFlowSearch.setOnTagClickListener((view, position1, parent1) -> {
            /*String name = hotList.get(position1).chnDesc;
            if (!historyList.contains(name)) {
                historyList.add(name);
                mPresenter.saveSearchHistory(context, historyList);
            }*/
            ToastUtils.showLong("点了"+position1);
            return true;
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    protected void initData() {
        if (NetUtil.checkNet(this)) {
            try {
                Map<String, String> map = new TreeMap<>();
                map.put("txnType", "35");
                String str = PayUtils.joinMapValue(map, '&');
                map.put("signature", RSACoder.sign(str.getBytes(), privateKey).replaceAll("\n\r", ""));
                String result = new Gson().toJson(map);
                Logger.e("result" + result);

                HTTPSRequestUtils.getJson(Constant.HTTP_PATH, result)
                        .compose(bindUntilEvent(ActivityEvent.DESTROY))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            Logger.e("s" + s);
                            JSONObject jsonObject = new JSONObject(s);
                            Map<String, Object> jsonMap = JSONUtil.jsonToMap(jsonObject);
                            final String status = (jsonMap.get("status") != null ? jsonMap.get("status") : "").toString();
                            final String msg = (jsonMap.get("msg") != null ? jsonMap.get("msg") : "").toString();
                            if ("0".equals(status)) {
                                ArrayList<String> list = new ArrayList<String>();
                                List<Map<String, Object>> listMap = JSONUtil.getJsonData(jsonObject, "list");
                                for (Map<String, Object> map1 : listMap) {
                                    list.add(map1.get("countryCode").toString());
                                }
                            }
                            Logger.e("countryCodeList_save:" + s);
                            prefs.writePrefs(Constant.PREFES_CODELIST, s);

                            /*Intent intents = new Intent(WelcomeActivity.this, LoginActivity.class);
                            startActivity(intents);
                            finish();*/
                        }, error -> {
                            Logger.d(error.getMessage());
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_article_share:
                ToastUtils.showLong("点击了分享");
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "来自WanAndroid 【" + "一个简单仿微信朋友圈的图片查看器 PhotoViewer" + "】" + "http://www.wanandroid.com/blog/show/2294");
                startActivity(Intent.createChooser(shareIntent, "分享"));
                break;
            case R.id.menu_article_collect:
                ToastUtils.showLong("点击了收藏");
                /*LogUtil.e("id = " + articleId + "is = " + isCollect);
                if ((Boolean) SharedPreferenceUtil.get(context, Constant.ISLOGIN, Constant.FALSE)) {
                    if (isCollect) {
                        mPresenter.cancelCollectArticle(articleId);
                    } else {
                        mPresenter.collectArticle(articleId);
                    }
                } else {
                    ToastUtil.show(activity, getString(R.string.please_login));
                    JumpUtil.overlay(activity, LoginActivity.class);
                }*/
                break;
            case R.id.menu_article_browser:
                ToastUtils.showLong("点击了浏览器");
                Uri uri = Uri.parse("http://www.wanandroid.com/blog/show/2294");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * 让菜单同时显示图标和文字
     *
     * @param featureId Feature id
     * @param menu      Menu
     * @return menu if opened
     */
    @SuppressLint("PrivateApi")
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
