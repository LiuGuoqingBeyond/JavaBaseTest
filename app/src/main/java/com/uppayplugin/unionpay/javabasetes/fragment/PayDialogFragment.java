package com.uppayplugin.unionpay.javabasetes.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.PwdEditText;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetes.utils.keyboard.KeyboardUtil;
import com.uppayplugin.unionpay.javabasetes.utils.keyboard.MyKeyBoardView;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/3/25.
 */

@SuppressLint("ValidFragment")
public class PayDialogFragment extends DialogFragment implements PwdEditText.OnTextInputListener, View.OnClickListener {

    private static final String TAG = "PayDialogFragment";
    private final Activity activity;
    private EditText editText;
    private KeyboardUtil keyboardUtil;
    private Keyboard       mKeyboardNumber;//数字键盘
    private MyKeyBoardView mKeyboardView;
    private EditText       mEditText;
    private EditText et_input;
    private View view;
    private Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public PayDialogFragment(Activity activity){
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        //去掉dialog的标题，需要在setContentView()之前
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        view = inflater.inflate(R.layout.activity_key_board, null);
        mKeyboardNumber = new Keyboard(getActivity(), R.xml.keyboardnumber);
        mKeyboardView = view.findViewById(R.id.keyboard_view);
        button = view.findViewById(R.id.btn_commit);
        et_input = view.findViewById(R.id.et_input);
        button.setOnClickListener(this);

        keyboardUtil = new KeyboardUtil(activity);
        hideSystemSofeKeyboard(getActivity().getApplicationContext(), et_input);
        showSoftKeyboard();
//        keyboardUtil.attachTo(et_input);
        et_input.setFocusable(true);
        et_input.setFocusableInTouchMode(true);
        et_input.requestFocus();
        /*Button exitImgView = view.findViewById(R.id.btn_cancel);
        exitImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayDialogFragment.this.dismiss();
            }
        });
        *//*final PwdEditText editText = view.findViewById(R.id.et_input);
        editText.setOnTextInputListener(this);*//*
        editText = view.findViewById(R.id.et_input);
//        disableShowSoftInput();
        PwdKeyboardView keyboardView = view.findViewById(R.id.key_board);
        keyboardView.setOnKeyListener(new PwdKeyboardView.OnKeyListener() {
            @Override
            public void onInput(String text) {
                Log.d(TAG, "onInput: text = " + text);
                editText.append(text);
                String content = editText.getText().toString();
                Log.d(TAG, "onInput: content = " + content);
            }

            @Override
            public void onDelete() {
                Log.d(TAG, "onDelete: ");
                String content = editText.getText().toString();
                if (content.length() > 0) {
                    editText.setText(content.substring(0, content.length() - 1));
                }
            }
        });*/
        return view;
    }

    private void hideSystemSofeKeyboard(Context context, EditText editText) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
        // 如果软键盘已经显示，则隐藏
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void showSoftKeyboard() {
        if (mKeyboardNumber == null) {
            mKeyboardNumber = new Keyboard(getActivity(), R.xml.keyboardnumber);
        }
        if (mKeyboardView == null) {
            mKeyboardView = view.findViewById(R.id.keyboard_view);
        }
        /*if (mIfRandom) {
            randomKeyboardNumber();
        } else {*/
            mKeyboardView.setKeyboard(mKeyboardNumber);
//        }
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setVisibility(View.VISIBLE);
        mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);
    }

    private KeyboardView.OnKeyboardActionListener mOnKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = et_input.getText();
            int start = et_input.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 隐藏键盘
                hideKeyboard();
                /*if (mOnCancelClick != null) {
                    mOnCancelClick.onCancellClick();
                }*/
            } else if (primaryCode == Keyboard.KEYCODE_DONE) {// 隐藏键盘
                hideKeyboard();
                /*if (mOnOkClick != null) {
                    mOnOkClick.onOkClick();
                }*/
            } else {
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    private void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.windowAnimations = R.style.DialogFragmentAnimation;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置dialog的位置在底部
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
        //去掉dialog默认的padding
//        window.getDecorView().setPadding(0, 0, 0, 0);

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    @Override
    public void onComplete(String result) {
        Log.d(TAG, "onComplete: result = " + result);
        Toast.makeText(getContext(), "input complete : " + result, Toast.LENGTH_SHORT).show();
    }

    /**
     * 禁止Edittext弹出软件盘，光标依然正常显示。
     */
    public void disableShowSoftInput() {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onClick(View view) {
        ToastUtils.showLong(et_input.getText().toString());
    }
}
