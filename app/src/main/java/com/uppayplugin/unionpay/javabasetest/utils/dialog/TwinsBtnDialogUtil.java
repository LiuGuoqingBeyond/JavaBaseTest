package com.uppayplugin.unionpay.javabasetest.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetest.R;


public class TwinsBtnDialogUtil extends Dialog {
    public TwinsBtnDialogUtil(Context context) {
        super(context, R.style.dialog_style);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private View contentView;
        private String certernpositiveButtonText;
        private OnClickListener certernpositiveButtonClickListener;

        private String certernpositiveTwoButtonText;
        private OnClickListener certernpositiveTwoButtonClickListener;
        private boolean canceledOnTouchOutside;

        public Builder(Context context) {
            this.context = context;
        }

        public TwinsBtnDialogUtil.Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public TwinsBtnDialogUtil.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public TwinsBtnDialogUtil.Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public TwinsBtnDialogUtil.Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public TwinsBtnDialogUtil.Builder setContentView(View view) {
            this.contentView = view;
            return this;
        }

        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
        }


        public TwinsBtnDialogUtil.Builder setCerternButton(int positiveButtonText, OnClickListener listener) {
            this.certernpositiveButtonText = (String) context.getText(positiveButtonText);
            this.certernpositiveButtonClickListener = listener;
            return this;
        }

        public TwinsBtnDialogUtil.Builder setCerternButton(String positiveButtonText, OnClickListener listener) {
            //这个是一个按钮
            this.certernpositiveButtonText = positiveButtonText;
            this.certernpositiveButtonClickListener = listener;
            return this;
        }

        public TwinsBtnDialogUtil.Builder setTwoButton(String positiveButtonText, OnClickListener listener) {
            //这个是两个按钮
            this.certernpositiveTwoButtonText = positiveButtonText;
            this.certernpositiveTwoButtonClickListener = listener;
            return this;
        }

        public TwinsBtnDialogUtil create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final TwinsBtnDialogUtil dialog = new TwinsBtnDialogUtil(context);
            View layout = inflater.inflate(R.layout.twins_dialog_activity, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (!TextUtils.isEmpty(title)) {
                ((TextView) layout.findViewById(R.id.pwd_title)).setText(title);
            } else {
                ((TextView) layout.findViewById(R.id.pwd_title)).setVisibility(View.GONE);
            }


            //左边按钮
            if (!TextUtils.isEmpty(certernpositiveButtonText) && TextUtils.isEmpty(certernpositiveTwoButtonText)) {
                ((Button) layout.findViewById(R.id.true_left)).setText(certernpositiveButtonText);
                if (certernpositiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.true_left)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            certernpositiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
                layout.findViewById(R.id.true_pwdhint).setVisibility(View.GONE);
                ((View) layout.findViewById(R.id.view)).setVisibility(View.GONE);
            }
            //两边都按钮
            if (!TextUtils.isEmpty(certernpositiveTwoButtonText) && !TextUtils.isEmpty(certernpositiveButtonText)) {
                ((Button) layout.findViewById(R.id.true_pwdhint)).setText(certernpositiveTwoButtonText);
                ((Button) layout.findViewById(R.id.true_left)).setText(certernpositiveButtonText);

                if (certernpositiveTwoButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.true_pwdhint)).setOnClickListener(v -> certernpositiveTwoButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE));
                }
                if (certernpositiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.true_left)).setOnClickListener(v -> certernpositiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE));
                }
            }

            if (message != null) {
                ((TextView) layout.findViewById(R.id.pwd_message)).setText(message);
            }
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
