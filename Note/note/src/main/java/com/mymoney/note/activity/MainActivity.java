package com.mymoney.note.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.mymoney.note.R;
import com.mymoney.note.fragment.HomeTimeLineFragment;

public class MainActivity extends BaseSlidingFragmentActivity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_content);

		setHomeContent(new HomeTimeLineFragment(), false, SlidingMenu.TOUCHMODE_MARGIN);

	}

	@Override
	public void onClick(View v) {

	}

}
