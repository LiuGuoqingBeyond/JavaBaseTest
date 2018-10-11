package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtil;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.whty.xzfpos.base.AppToolBarActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JudgeActivity extends ToolBarActivity {
    @BindView(R.id.et_id)
    EditText et_id;
    @BindView(R.id.btn_id)
    Button btn_id;

    @BindView(R.id.et_card)
    EditText et_card;
    @BindView(R.id.btn_card)
    Button btn_card;

    @BindView(R.id.et_str)
    EditText mEditText;
    @BindView(R.id.btn_str)
    Button btn_str;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_judge;
    }

    @Override
    protected void initViewsAndEvents() {
        ButterKnife.bind(JudgeActivity.this);
//        setEditTextInputSpeChat(et_str);
        mEditText.addTextChangedListener(new TextWatcher() {
            private int cou = 0;
            int selectionEnd = 0;
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                cou = before + count;
                String editable = mEditText.getText().toString();
                String str = stringFilter(editable); //过滤特殊字符
                if (!editable.equals(str)) {
                    mEditText.setText(str);
                }
                mEditText.setSelection(mEditText.length());
                cou = mEditText.length();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (cou > mMaxLenth) {
                    selectionEnd = mEditText.getSelectionEnd();
                    s.delete(mMaxLenth, selectionEnd);
                }
            }
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @OnClick({R.id.btn_id, R.id.btn_card, R.id.btn_str})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_id:
                //验证身份证
                if (!TextUtils.isEmpty(et_id.getText().toString())) {
                    if (!isLegalId(et_id.getText().toString())) {
                        ToastUtils.showLong("请输入合法的身份证号");
                    } else {
                        ToastUtils.showLong("这是正确的身份证号");
                    }
                }
                break;
            case R.id.btn_card:
                //验证银行卡
                if (!TextUtils.isEmpty(et_card.getText().toString())) {
                    if (!checkBankCard(et_card.getText().toString())) {
                        ToastUtils.showLong("请输入正确的银行卡号");
                    } else {
                        ToastUtils.showLong("这是正确的银行卡号");
                    }
                }
                break;
            case R.id.btn_str:
                //校验字符串里面是否含特殊字符
                /*if (!TextUtils.isEmpty(et_str.getText().toString())) {
                    setEditTextInputSpeChat(et_str);
                }*/
                break;
        }
    }

    /**
     * 验证输入的身份证号是否合法
     */
    public static boolean isLegalId(String id) {
        if (id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText EditText输入框
     */
    public static void setEditTextInputSpeChat(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】\"‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
    int mMaxLenth = 2;//设置允许输入的字符长度
    public static String stringFilter(String str)throws PatternSyntaxException{
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】\"‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }
}
