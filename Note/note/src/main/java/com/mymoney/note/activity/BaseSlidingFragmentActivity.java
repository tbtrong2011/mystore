package com.mymoney.note.activity;

import java.lang.ref.WeakReference;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.mymoney.note.R;
import com.mymoney.note.application.UIMessage;
import com.mymoney.note.utility.Utils;

public abstract class BaseSlidingFragmentActivity extends SlidingFragmentActivity implements OnClickListener,
		IBaseMenuAction {

	public static final int DEFAULT_REQUEST_CODE = 0;

	public static final String TAG_LEFT_MENU_FRAGMENT = "BaseSlidingFragmentActivity_tag_left_menu_fragment";
	public static final String TAG_RIGHT_MENU_FRAGMENT = "BaseSlidingFragmentActivity_tag_right_menu_fragment";
	public static final String TAG_CONTENT_FRAGMENT = "BaseSlidingFragmentActivity_tag_left_content_fragment";

	// Handler class
	static class UIHandler extends Handler {
		private WeakReference<BaseSlidingFragmentActivity> mReference;

		public UIHandler(BaseSlidingFragmentActivity reference) {
			mReference = new WeakReference<BaseSlidingFragmentActivity>(reference);
		}

		@Override
		public void handleMessage(Message msg) {
			BaseSlidingFragmentActivity ref = getReference();
			if (ref != null) {
				// message is handled by application
				if (ref.handleMessage(msg))
					return;
			}
			super.handleMessage(msg);
		}

		public BaseSlidingFragmentActivity getReference() {
			if (mReference != null)
				return mReference.get();
			return null;
		}
	}

	// Handler instance
	protected UIHandler mHandler;

	// Sliding menu
	protected SlidingMenu sm;
	private boolean rightMenuEnable = false;

	// UI base widgets
	protected Dialog mProgress;

	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		// Sliding menu
		setBehindContentView(R.layout.main_left_menu);
		sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.dimen_60_120);
		sm.setMode(SlidingMenu.LEFT);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		sm.setBehindScrollScale(0f);

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

	protected final void hideKeyBoard() {
		if (this.getCurrentFocus() != null) {
			InputMethodManager in = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
			in.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	// //////////////////////////////////////////////////////////////////
	// ////////////////SLIDING MENU//////////////////////////////////////
	// //////////////////////////////////////////////////////////////////
	@Override
	public void openLeftMenu() {
		sm.toggle();
		hideKeyBoard();
	}

	@Override
	public void openRightMenu() {
		sm.showSecondaryMenu();
		hideKeyBoard();
	}

	@Override
	public void closeMenu() {
		sm.showContent();
		hideKeyBoard();
	}

	@Override
	public void setLeftMenuContent(Fragment leftFragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_left_menu, leftFragment, TAG_LEFT_MENU_FRAGMENT).commit();
		hideKeyBoard();

	}

	@Override
	public void setRightMenuContent(Fragment rightFragment) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setHomeContent(Fragment contentFragment, boolean hasRightMenu, int touchMode) {
//		getSupportFragmentManager().beginTransaction()
//				.replace(R.id.main_content, contentFragment, TAG_CONTENT_FRAGMENT).commit();
//
//		getSlidingMenu().setTouchModeAbove(touchMode);

		if (hasRightMenu) {
			setRightMenuEnable(true);
		} else {
			setRightMenuEnable(false);
		}
		hideKeyBoard();
	}

	@Override
	public Fragment getLeftFragment() {
		return getSupportFragmentManager().findFragmentByTag(TAG_LEFT_MENU_FRAGMENT);
	}

	@Override
	public Fragment getRightFragment() {
		return getSupportFragmentManager().findFragmentByTag(TAG_RIGHT_MENU_FRAGMENT);
	}

	@Override
	public Fragment getHomeFragment() {
		return getSupportFragmentManager().findFragmentByTag(TAG_CONTENT_FRAGMENT);
	}

	@Override
	public boolean isMenuShowing() {
		return sm.isMenuShowing();
	}

	@Override
	public boolean isLeftMenuShowing() {
		return sm.isMenuShowing() == true && sm.isSecondaryMenuShowing() == false;
	}

	@Override
	public boolean isRightMenuShowing() {
		return sm.isSecondaryMenuShowing();
	}

	@Override
	public void setOnOpenListener(OnOpenListener openListener) {
		sm.setOnOpenListener(openListener);
	}

	@Override
	public void setOnCloseListener(OnCloseListener closeListener) {
		sm.setOnCloseListener(closeListener);
	}

	@Override
	public void setOnOpenedListener(OnOpenedListener openedListener) {
		sm.setOnOpenedListener(openedListener);
	}

	@Override
	public void setOnClosedListener(OnClosedListener closedListener) {
		sm.setOnClosedListener(closedListener);
	}

	public void setRightMenuEnable(boolean enable) {
		rightMenuEnable = enable;
		SlidingMenu sm = getSlidingMenu();
		if (rightMenuEnable) {
			sm.setMode(SlidingMenu.LEFT_RIGHT);
		} else {
			sm.setMode(SlidingMenu.LEFT);
		}
	}

	public boolean isRightMenuEnable() {
		return rightMenuEnable;
	}

}
