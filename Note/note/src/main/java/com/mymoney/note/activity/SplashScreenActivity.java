package com.mymoney.note.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.Window;

import com.mymoney.note.R;
import com.mymoney.note.application.Constant;
import com.mymoney.note.system.Device;

public class SplashScreenActivity extends BaseFragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_sreen_activity);

		// View content = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
		new Device(this);

		mHandler.postDelayed(new SplashRunnable(), Constant.DEFAULT_POST_DELAY_RUNABLE);
	}

	private class SplashRunnable implements Runnable {
		@Override
		public void run() {
			navigateTo(MainActivity.class);
			finish();
			return;
		}
	}

	// Handle message method
	@Override
	protected boolean handleMessage(Message msg) {
		return super.handleMessage(msg);
	}
}
