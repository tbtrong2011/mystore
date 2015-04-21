package com.mymoney.note.activity;

import android.support.v4.app.Fragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;

public interface IBaseMenuAction {
	public void openLeftMenu();

	public void openRightMenu();

	public void closeMenu();

	public void setLeftMenuContent(Fragment leftFragment);

	public void setRightMenuContent(Fragment rightFragment);

	public void setHomeContent(Fragment contentFragment, boolean hasRightMenu, int touchMode);

	public Fragment getLeftFragment();

	public Fragment getRightFragment();

	public Fragment getHomeFragment();

	public boolean isMenuShowing();

	public boolean isLeftMenuShowing();

	public boolean isRightMenuShowing();

	public void setOnOpenListener(OnOpenListener openListener);

	public void setOnCloseListener(OnCloseListener closeListener);

	public void setOnOpenedListener(OnOpenedListener openedListener);

	public void setOnClosedListener(OnClosedListener closedListener);
}
