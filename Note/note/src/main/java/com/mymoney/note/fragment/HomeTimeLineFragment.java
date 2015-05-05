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

import java.util.ArrayList;

public class HomeTimeLineFragment extends BaseFragment implements OnClickListener {

	private ImageView swipeMenu;
    private PagerSlidingTabStrip tabsStrip;
    private HomeTabStripFragmentPagerAdapter mAdapterPager;
    private ArrayList<Object> mArrayData = new ArrayList<Object>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_timeline_fragment, container, false);
        swipeMenu = (ImageView) view.findViewById(R.id.swipe_menu);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);


        mArrayData.add(MoneyFragment.newInstance(R.drawable.ic_smile));
        mArrayData.add(NoteFragment.newInstance(R.drawable.ic_sad));

        mAdapterPager = new HomeTabStripFragmentPagerAdapter(getActivity().getSupportFragmentManager(),mArrayData);

        viewPager.setAdapter(mAdapterPager);
        tabsStrip.setViewPager(viewPager);

        swipeMenu.setOnClickListener(this);
        createEventTabStrip();

		return view;
	}

    private void createEventTabStrip(){
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getActivity(),
                        "Selected page : " + position, Toast.LENGTH_SHORT).show();
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
				MainActivity activity = (MainActivity) getActivity();
				activity.openLeftMenu();
				return;

		}

	}

}
