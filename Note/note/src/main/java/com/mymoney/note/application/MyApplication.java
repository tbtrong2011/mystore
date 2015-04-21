package com.mymoney.note.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	public static MyApplication instance;
	private Context mContext = null;

	public void setContext(Context C) {
		this.mContext = C;
	}

	public Context getContext() {
		return this.mContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		instance = this;
		mContext = getApplicationContext();

	}
}
