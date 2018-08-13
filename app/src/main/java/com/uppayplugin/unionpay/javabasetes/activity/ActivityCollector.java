package com.uppayplugin.unionpay.javabasetes.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
	public static List<Activity> activityes=new ArrayList<Activity>();
	public static void addActivity(Activity activity){
		activityes.add(activity);
	}
	public static void removeActivity(Activity activity){
		activityes.remove(activity);
	}
	public static void finsdhAll(){
		for(Activity activity:activityes){
			if(!activity.isFinishing()){
				activity.finish();
			}
		}
	}
}
