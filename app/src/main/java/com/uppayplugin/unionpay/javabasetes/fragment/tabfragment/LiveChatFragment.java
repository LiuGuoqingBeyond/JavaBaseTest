package com.uppayplugin.unionpay.javabasetes.fragment.tabfragment;

import com.axl.android.frameworkbase.ui.AbsBaseFragment;
import com.axl.android.frameworkbase.ui.BaseFragment;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.R;

/**
 * User: LiuGuoqing
 * Data: 2018/8/20 0020.
 */

public class LiveChatFragment extends AbsBaseFragment {
    @Override
    protected int setContentView() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Logger.e("打印了1");
    }
}
