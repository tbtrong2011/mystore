package com.mymoney.note.fragment;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.mymoney.note.R;
import com.mymoney.note.application.UIMessage;
import com.mymoney.note.utility.Utils;

public abstract class BaseFragment extends Fragment {
	static class UIHandler extends Handler {
		private WeakReference<BaseFragment> mReference;

		public UIHandler(BaseFragment reference) {
			mReference = new WeakReference<BaseFragment>(reference);
		}

		@Override
		public void handleMessage(Message msg) {
			BaseFragment ref = getReference();
			if (ref != null) {
				ref.handleMessage(msg);
			}
			super.handleMessage(msg);
		}

		public BaseFragment getReference() {
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
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mHandler = new UIHandler(this);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
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
				Utils.showToast(this.getActivity(), R.string.network_error, Toast.LENGTH_SHORT);
				return true;
			case UIMessage.MSG_UNKNOW_ERROR:
				Utils.showToast(this.getActivity(), R.string.unknow_error, Toast.LENGTH_SHORT);
				return true;
		}
		return false;
	}

	public void showProgressDialog() {
		if (mProgress == null) {
			mProgress = new Dialog(this.getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
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

}
