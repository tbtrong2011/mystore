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

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//
//        setContentView(R.layout.main_timeline_fragment);
//        // Get the ViewPager and set it's PagerAdapter so that it can display items
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//
//        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));
//
//        // Give the PagerSlidingTabStrip the ViewPager
//        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
//        // Attach the view pager to the tab strip
//        tabsStrip.setViewPager(viewPager);
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }

}
