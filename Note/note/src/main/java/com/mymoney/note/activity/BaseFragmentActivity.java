package com.mymoney.note.activity;

import java.lang.ref.WeakReference;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.mymoney.note.R;
import com.mymoney.note.application.UIMessage;
import com.mymoney.note.utility.Utils;

public abstract class BaseFragmentActivity extends FragmentActivity {

	static class UIHandler extends Handler {
		private WeakReference<BaseFragmentActivity> mReference;

		public UIHandler(BaseFragmentActivity reference) {
			mReference = new WeakReference<BaseFragmentActivity>(reference);
		}

		@Override
		public void handleMessage(Message msg) {
			BaseFragmentActivity ref = getReference();
			if (ref != null) {
				ref.handleMessage(msg);
			}
			super.handleMessage(msg);
		}

		public BaseFragmentActivity getReference() {
			if (mReference != null)
				return mReference.get();
			return null;
		}
	}

	// Handler instance
	protected UIHandler mHandler;

	// UI base widgets
	protected Dialog mProgress;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mHandler = new UIHandler(this);
	}

	// Handle message method
	protected boolean handleMessage(Message msg) {
		switch (msg.what) {
			case UIMessage.MSG_SHOW_DIALOG_PROCESS:
				showProgressDialog();
				return true;
			case UIMessage.MSG_DISMISS_DIALOG_PROCESS:
				dismissProgressDialog();
				return true;
			case UIMessage.MSG_NETWORK_ERROR:
				Utils.showToast(this, R.string.network_error, Toast.LENGTH_SHORT);
				return true;
			case UIMessage.MSG_UNKNOW_ERROR:
				Utils.showToast(this, R.string.unknow_error, Toast.LENGTH_SHORT);
				return true;
		}
		return false;
	}

	public void showProgressDialog() {
		if (mProgress == null) {
			mProgress = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
			mProgress.setContentView(R.layout.dialog_process);
			mProgress.setCancelable(false);
		}
		mProgress.show();
	}

	public void dismissProgressDialog() {
		if (mProgress != null && mProgress.isShowing()) {
			mProgress.dismiss();
			mProgress = null;
		}
	}

	protected void navigateTo(Class<?> target) {
		Intent intent = new Intent(this, target);
		startActivity(intent);
	}

	protected void navigateTo(Intent intent) {
		if (intent != null)
			startActivity(intent);
	}

	protected void navigateTo(Class<?> target, int requestCode) {
		Intent intent = new Intent(this, target);
		startActivityForResult(intent, requestCode);
	}

	protected void navigateTo(Intent intent, int requestCode) {
		if (intent != null)
			startActivityForResult(intent, requestCode);
	}

}
