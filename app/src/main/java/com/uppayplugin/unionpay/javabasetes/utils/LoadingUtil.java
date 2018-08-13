package com.uppayplugin.unionpay.javabasetes.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;


/**
 * @description 加载中Dialog
 * @author King_wangyao
 * @date 2014-5-20
 * @version 1.0.0
 *
 */
public class LoadingUtil extends AlertDialog {

	private String message = getContext().getString(R.string.text_loadering);

	private boolean cancelButton = false;

	public LoadingUtil(Context context) {
		super(context);
		this.setCancelable(true);
	}

	public LoadingUtil(Context context, String message) {
		super(context);
		this.message = message;
		this.setCancelable(true);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.view_loading);

		TextView message = (TextView) findViewById(R.id.loading_message);
		message.setText(this.message);

		Button button = (Button) findViewById(R.id.loading_button);
		button.setOnClickListener(v -> dismiss());
		if (!isCancelButton()) {
			button.setVisibility(View.GONE);
		}
	}

	public boolean isCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(boolean cancelButton) {
		this.cancelButton = cancelButton;
	}

}
