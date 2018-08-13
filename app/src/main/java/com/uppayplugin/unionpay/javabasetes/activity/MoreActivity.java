package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.uppayplugin.unionpay.javabasetes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreActivity extends BaseActivity {

    @BindView(R.id.btn_title_left)
    Button btnTitleLeft;
    @BindView(R.id.tv_title_text)
    TextView tvTitleText;
    @BindView(R.id.btn_title_right)
    Button btnTitleRight;
    @BindView(R.id.image_help)
    ImageView imageHelp;
    @BindView(R.id.more_help)
    RelativeLayout moreHelp;
    @BindView(R.id.image_opinion)
    ImageView imageOpinion;
    @BindView(R.id.more_feedback)
    RelativeLayout moreFeedback;
    @BindView(R.id.image_about)
    ImageView imageAbout;
    @BindView(R.id.more_about)
    RelativeLayout moreAbout;
    @BindView(R.id.image_update)
    ImageView imageUpdate;
    @BindView(R.id.more_update)
    RelativeLayout moreUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void initData() {
        tvTitleText.setText(getString(R.string.more_title));

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.more_help, R.id.more_feedback, R.id.more_about, R.id.more_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_help:
                break;
            case R.id.more_feedback:
                break;
            case R.id.more_about:
                break;
            case R.id.more_update:
                break;
        }
    }
}
