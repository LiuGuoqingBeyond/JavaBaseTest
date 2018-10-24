package com.uppayplugin.unionpay.javabasetes.Interface;

import android.view.View;
import android.view.View.OnClickListener;

public class AsynViewClickOnceListener implements OnClickListener {

	private boolean onClick = false;
	private Runnable r = null;
	
	public AsynViewClickOnceListener(Runnable r) {
		super();
		this.r = r;
	}

	@Override
	public synchronized void onClick(View v) {
		try {
			if(!onClick){
				onClick = true;
				try {
					runOnUIThread();
				} catch (Exception e) {
					e.printStackTrace();
				}
				new Thread(){
					public void run(){
						try {
							if(r!=null){
								r.run();
								runOnNewThread();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							onClick = false;
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runOnUIThread(){
		
	}
	
	public void runOnNewThread(){
		
	}
	
	public void reset(){
		onClick = false;
	}
}
