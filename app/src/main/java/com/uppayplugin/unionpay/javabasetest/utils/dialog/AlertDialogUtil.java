package com.uppayplugin.unionpay.javabasetest.utils.dialog;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetest.R;


/**
 * @description 提示Dialog
 * @author King_wangyao
 * @date 2014-5-20
 * @version 1.0.0
 *
 */
public class AlertDialogUtil extends AlertDialog{

	private int icon = R.drawable.icon_other_logo;

	private String state = getContext().getString(R.string.text_loadering);

	private String message = getContext().getString(R.string.text_loadering);

	public AlertDialogUtil(Context context) {
		super(context);
		this.setCanceledOnTouchOutside(true);
	}

	public AlertDialogUtil(Context context, int icon, String state, String message) {
		super(context);
		this.icon = icon;
		this.state = state;
		this.message = message;
		this.setCanceledOnTouchOutside(true);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.view_alert_info);

		ImageView img_icon = (ImageView) findViewById(R.id.img_icon);
		img_icon.setImageResource(this.icon);

		TextView tv_state = (TextView) findViewById(R.id.tv_state);
		tv_state.setText(this.state);

		TextView tv_message = (TextView) findViewById(R.id.tv_message);
		tv_message.setText(this.message);
		img_icon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialogUtil.this.dismiss();
			}
		});
	}

}
