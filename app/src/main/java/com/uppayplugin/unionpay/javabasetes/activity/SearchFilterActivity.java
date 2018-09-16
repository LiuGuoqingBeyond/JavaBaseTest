package com.uppayplugin.unionpay.javabasetes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.RadioGroup;
import com.whty.xzfpos.base.AppBaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFilterActivity extends AppBaseActivity {
    @BindView(R.id.view_no)
    View view_no;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;

    @BindView(R.id.rg_merQuery)
    RadioGroup rg_merQuery;
    @BindView(R.id.rg_tradeQuery)
    RadioGroup rg_tradeQuery;
    @BindView(R.id.rg_withdraw)
    RadioGroup rg_withdraw;

    @BindView(R.id.radio_button1)
    RadioButton radio_button1;
    @BindView(R.id.radio_button2)
    RadioButton radio_button2;
    @BindView(R.id.radio_button3)
    RadioButton radio_button3;
    @BindView(R.id.radio_button4)
    RadioButton radio_button4;
    @BindView(R.id.radio_button5)
    RadioButton radio_button5;
    @BindView(R.id.radio_button6)
    RadioButton radio_button6;
    @BindView(R.id.radio_button7)
    RadioButton radio_button7;
    @BindView(R.id.radio_button8)
    RadioButton radio_button8;
    @BindView(R.id.radio_button9)
    RadioButton radio_button9;
    @BindView(R.id.radio_button10)
    RadioButton radio_button10;
    @BindView(R.id.radio_button11)
    RadioButton radio_button11;
    @BindView(R.id.radio_button12)
    RadioButton radio_button12;

    @BindView(R.id.rl_status)
    RelativeLayout rl_status;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_status)
    TextView tv_status;

    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.btn_reset)
    Button btn_reset;
    @BindView(R.id.btn_commit)
    Button btn_commit;

    private Calendar startDate;
    private String starTime;
    private TimePickerView pvTime;
    private String state;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_search_filter;
    }

    @Override
    protected void initViewsAndEvents() {
        ButterKnife.bind(this);
        initDate();
        //此处需要传相应显示/隐藏的区域：5.2商户筛选、6.1分润查询筛选、7交易查询筛选、8.1交易调单筛选、8.2提现调单筛选，同时会发送两个色块对应的数值替换
        String type = "mer";
        visibility(type);
        radioGroupChecked();
    }

    private void radioGroupChecked() {
        rg_merQuery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button1:
                        setMerVisibles(radio_button1, radio_button2, radio_button3, radio_button4);
                        state = radio_button1.getText().toString();
                        break;
                    case R.id.radio_button2:
                        setMerVisibles(radio_button2, radio_button1, radio_button3, radio_button4);
                        state = radio_button2.getText().toString();
                        break;
                    case R.id.radio_button3:
                        setMerVisibles(radio_button3, radio_button1, radio_button2, radio_button4);
                        state = radio_button3.getText().toString();
                        break;
                    case R.id.radio_button4:
                        setMerVisibles(radio_button4, radio_button1, radio_button2, radio_button3);
                        state = radio_button4.getText().toString();
                        break;
                }
            }
        });
        rg_tradeQuery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button5:
                        setBenefitVisibles(radio_button5, radio_button6, radio_button7, radio_button8, radio_button9);
                        break;
                    case R.id.radio_button6:
                        setBenefitVisibles(radio_button6, radio_button5, radio_button7, radio_button8, radio_button9);
                        break;
                    case R.id.radio_button7:
                        setBenefitVisibles(radio_button7, radio_button6, radio_button5, radio_button8, radio_button9);
                        break;
                    case R.id.radio_button8:
                        setBenefitVisibles(radio_button8, radio_button6, radio_button7, radio_button5, radio_button9);
                        break;
                    case R.id.radio_button9:
                        setBenefitVisibles(radio_button9, radio_button6, radio_button7, radio_button8, radio_button5);
                        break;
                }
            }
        });
        rg_withdraw.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button10:
                        setParagraphVisibles(radio_button10, radio_button11, radio_button12);
                        break;
                    case R.id.radio_button11:
                        setParagraphVisibles(radio_button11, radio_button10, radio_button12);
                        break;
                    case R.id.radio_button12:
                        setParagraphVisibles(radio_button12, radio_button11, radio_button10);
                        break;
                }
            }
        });
    }

    private void setParagraphVisibles(RadioButton radio_button10, RadioButton radio_button11, RadioButton radio_button12) {
        radio_button10.setBackgroundResource(R.drawable.goods_attr_selected_shape);
        radio_button10.setTextColor(getResources().getColor(R.color.colorPrimary));
        radio_button11.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button11.setTextColor(getResources().getColor(R.color.alpha_80_black));
        radio_button12.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button12.setTextColor(getResources().getColor(R.color.alpha_80_black));
    }

    private void setBenefitVisibles(RadioButton radio_button5, RadioButton radio_button6, RadioButton radio_button7, RadioButton radio_button8, RadioButton radio_button9) {
        radio_button5.setBackgroundResource(R.drawable.goods_attr_selected_shape);
        radio_button5.setTextColor(getResources().getColor(R.color.colorPrimary));
        radio_button6.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button6.setTextColor(getResources().getColor(R.color.alpha_80_black));
        radio_button7.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button7.setTextColor(getResources().getColor(R.color.alpha_80_black));
        radio_button8.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button8.setTextColor(getResources().getColor(R.color.alpha_80_black));
        radio_button9.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button9.setTextColor(getResources().getColor(R.color.alpha_80_black));
    }

    private void setMerVisibles(RadioButton radio_button1, RadioButton radio_button2, RadioButton radio_button3, RadioButton radio_button4) {
        radio_button1.setBackgroundResource(R.drawable.goods_attr_selected_shape);
        radio_button1.setTextColor(getResources().getColor(R.color.colorPrimary));
        radio_button2.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button2.setTextColor(getResources().getColor(R.color.alpha_80_black));
        radio_button3.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button3.setTextColor(getResources().getColor(R.color.alpha_80_black));
        radio_button4.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button4.setTextColor(getResources().getColor(R.color.alpha_80_black));
    }

    private void visibility(String type) {
        if (type.equals("mer")) {
            rg_merQuery.setVisibility(View.VISIBLE);
            radio_button1.setChecked(true);
            radio_button1.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            radio_button1.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else if (type.equals("benefit")) {
            //分润，都得隐藏
            /*rg_merQuery.setVisibility(View.VISIBLE);
            radio_button1.setChecked(true);
            radio_button1.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            radio_button1.setTextColor(getResources().getColor(R.color.colorPrimary));*/
        } else if (type.equals("transaction")) {
            rg_tradeQuery.setVisibility(View.VISIBLE);
            radio_button5.setChecked(true);
            radio_button5.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            radio_button5.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else if (type.equals("trade")) {
            //交易出款，全部隐藏
            /*rg_merQuery.setVisibility(View.VISIBLE);
            radio_button1.setChecked(true);
            radio_button1.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            radio_button1.setTextColor(getResources().getColor(R.color.colorPrimary));*/
        } else if (type.equals("paragraph")) {
            //交易出款，全部隐藏
            rg_withdraw.setVisibility(View.VISIBLE);
            radio_button10.setChecked(true);
            radio_button10.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            radio_button10.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void initDate() {
        //系统时间
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDate = s.format(new Date());  //当前日期
        String time = curDate.substring(0, 10);
        startTime.setText(time);
        endTime.setText(time);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @OnClick({R.id.view_no, R.id.start_time, R.id.end_time, R.id.tv_cancel, R.id.btn_reset, R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_no:
                finish();
                break;
            case R.id.start_time:
                openPickerView("start");
                break;
            case R.id.end_time:
                openPickerView("end");
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.btn_reset:
                initDate();
                rbReset();
                break;
            case R.id.btn_commit:
                checkCommit();
                break;
        }
    }

    private void rbReset() {
        radio_button1.setChecked(true);
        radio_button5.setChecked(true);
        radio_button10.setChecked(true);

        setMerVisibles(radio_button1, radio_button2, radio_button3, radio_button4);
        setBenefitVisibles(radio_button5, radio_button6, radio_button7, radio_button8, radio_button9);
        setParagraphVisibles(radio_button10, radio_button11, radio_button12);
    }

    private void checkCommit() {
        int start = Integer.parseInt(startTime.getText().toString().replace("-", ""));
        int end = Integer.parseInt(endTime.getText().toString().replace("-", ""));
        if (start > end) {
            showToast(getString(R.string.filter_time));
        } else {
            Intent intent = new Intent();
            intent.putExtra("state", state);
            setResult(105, intent);
            finish();
        }
    }

    private void openPickerView(String type) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(2010, 0, 1);

        pvTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (type.equals("start")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(date);
                    String time = str.substring(0, 10);
                    startTime.setText(time);
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(date);
                    String time = str.substring(0, 10);
                    endTime.setText(time);
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .setRangDate(startDate, endDate)
                .setDate(endDate)
                .setCancelColor(R.color.colorAccent)//设置取消按钮字体颜色
                .setTitleText("请选择日期")//主题
                .setSubmitText("哈哈")//设置确定按钮颜色
                .setSubmitColor(R.color.red)
                .build();
        pvTime.show();
    }
}
