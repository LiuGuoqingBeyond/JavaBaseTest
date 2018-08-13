package com.uppayplugin.unionpay.javabasetes.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;


public class DialogUtils extends Dialog {
    public DialogUtils(Context context) {
        super(context, R.style.dialog_style);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private View contentView;
        private String certernpositiveButtonText;
        private OnClickListener certernpositiveButtonClickListener;
        private boolean canceledOnTouchOutside;
        private boolean canceledable;

        public Builder(Context context) {
            this.context = context;
        }

        public DialogUtils.Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public DialogUtils.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public DialogUtils.Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public DialogUtils.Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public DialogUtils.Builder setContentView(View view) {
            this.contentView = view;
            return this;
        }

        public void setCanceledable(boolean canceledable) {
            this.canceledable = canceledable;
        }

        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
        }


        public DialogUtils.Builder setCerternButton(int positiveButtonText, OnClickListener listener) {
            this.certernpositiveButtonText = (String) context.getText(positiveButtonText);
            this.certernpositiveButtonClickListener = listener;
            return this;
        }

        public DialogUtils.Builder setCerternButton(String positiveButtonText, OnClickListener listener) {
            this.certernpositiveButtonText = positiveButtonText;
            this.certernpositiveButtonClickListener = listener;
            return this;
        }

        public DialogUtils create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final DialogUtils dialog = new DialogUtils(context);
            View layout = inflater.inflate(R.layout.view_pwddialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (!TextUtils.isEmpty(title)) {
                ((TextView) layout.findViewById(R.id.pwd_title)).setText(title);
            } else {
                ((TextView) layout.findViewById(R.id.pwd_title)).setVisibility(View.GONE);
            }
            if (certernpositiveButtonText != null) {
                ((Button) layout.findViewById(R.id.true_pwdhint)).setText(certernpositiveButtonText);
                if (certernpositiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.true_pwdhint)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            certernpositiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                layout.findViewById(R.id.true_pwdhint).setVisibility(View.GONE);
            }
            if (message != null) {
                ((TextView) layout.findViewById(R.id.pwd_message)).setText(message);
            } else if (contentView != null) {
                ((LinearLayout) layout.findViewById(R.id.message_layout)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.message_layout)).addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            dialog.setCancelable(canceledable);
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
