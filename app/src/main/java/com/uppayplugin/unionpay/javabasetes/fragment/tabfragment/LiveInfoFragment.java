package com.uppayplugin.unionpay.javabasetes.fragment.tabfragment;

import com.axl.android.frameworkbase.ui.AbsBaseFragment;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.R;

/**
 * User: LiuGuoqing
 * Data: 2018/8/20 0020.
 */

public class LiveInfoFragment extends AbsBaseFragment {
    @Override
    protected int setContentView() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initView() {
        Logger.e("打印了2");
    }

    @Override
    protected void initData() {

    }
}
