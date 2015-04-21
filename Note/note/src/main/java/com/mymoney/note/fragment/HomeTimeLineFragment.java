package com.mymoney.note.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.mymoney.note.R;
import com.mymoney.note.activity.MainActivity;
import com.mymoney.note.adapter.HomeTabStripFragmentPagerAdapter;

public class HomeTimeLineFragment extends BaseFragment implements OnClickListener {

	private ImageView swipeMenu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_timeline_fragment, container, false);
		swipeMenu = (ImageView) view.findViewById(R.id.swipe_menu);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new HomeTabStripFragmentPagerAdapter(getActivity().getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getActivity(),
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
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
        swipeMenu.setOnClickListener(this);


		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.swipe_menu:
				// openLeftMenu();
				MainActivity activity = (MainActivity) getActivity();
				activity.openLeftMenu();
				return;

		}

	}

}
