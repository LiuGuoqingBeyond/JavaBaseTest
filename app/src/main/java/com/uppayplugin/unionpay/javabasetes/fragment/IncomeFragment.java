package com.uppayplugin.unionpay.javabasetes.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.axl.android.frameworkbase.ui.AbsBaseFragment;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * User: LiuGuoqing
 * Data: 2018/8/29 0029.
 */

public class IncomeFragment extends AbsBaseFragment {
    @BindView(R.id.et_text1)
    EditText etText1;
    @BindView(R.id.et_text2)
    EditText etText2;
    @BindView(R.id.btnTrue)
    Button btnTrue;

    @Override
    protected int setContentView() {
        return R.layout.icome_fragment_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btnTrue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTrue:
                ToastUtils.showLong(etText1.getText().toString());
                break;
        }
    }
}
