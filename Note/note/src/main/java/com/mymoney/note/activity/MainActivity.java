package com.mymoney.note.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.mymoney.note.R;
import com.mymoney.note.adapter.HomeTabStripFragmentPagerAdapter;
import com.mymoney.note.fragment.MoneyFragment;
import com.mymoney.note.fragment.NoteFragment;

import java.util.ArrayList;

public class MainActivity extends BaseSlidingFragmentActivity implements OnClickListener{

	private ImageView swipeMenu;
	private PagerSlidingTabStrip tabsStrip;
	private HomeTabStripFragmentPagerAdapter mAdapterPager;
	private ArrayList<Object> mArrayData = new ArrayList<Object>();



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		swipeMenu = (ImageView) findViewById(R.id.swipe_menu);
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);


		mArrayData.add(MoneyFragment.newInstance(R.drawable.ic_smile));
		mArrayData.add(NoteFragment.newInstance(R.drawable.ic_sad));

		mAdapterPager = new HomeTabStripFragmentPagerAdapter(getSupportFragmentManager(),mArrayData);

		viewPager.setAdapter(mAdapterPager);
		tabsStrip.setViewPager(viewPager);

		swipeMenu.setOnClickListener(this);
		createEventTabStrip();



	}

	private void createEventTabStrip(){
		tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			// This method will be invoked when a new page becomes selected.
			@Override
			public void onPageSelected(int position) {

			}

			// This method will be invoked when the current page is scrolled
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// Code goes here
			}

			// Called when the scroll state changes:
			// SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
			@Override
			public void onPageScrollStateChanged(int state) {
				// Code goes here
			}
		});
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.swipe_menu:
				// openLeftMenu();
//				MainActivity activity = (MainActivity) this;
				openLeftMenu();
				return;

		}

	}

}
