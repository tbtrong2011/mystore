package com.mymoney.note.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ListAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.mymoney.note.R;
import com.mymoney.note.fragment.MoneyFragment;
import com.mymoney.note.fragment.NoteFragment;
import com.mymoney.note.fragment.PageFragment;

import java.util.ArrayList;

public class HomeTabStripFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    private ArrayList<Object> mItems;
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
    private int tabIcons[] = {R.drawable.ic_home, R.drawable.ic_smile, R.drawable.ic_sad};


    public HomeTabStripFragmentPagerAdapter(FragmentManager fm,ArrayList<Object> items) {
        super(fm);
        mItems = items;
    }

//    public HomeTabStripFragmentPagerAdapter(FragmentManager fm) {
//        super(fm);
//    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Fragment getItem(int position) {
        if(mItems.get(position) instanceof MoneyFragment){
            return (MoneyFragment) mItems.get(position);
        } else if(mItems.get(position) instanceof NoteFragment){
            return (NoteFragment) mItems.get(position);
        } else {
            return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public int getPageIconResId(int position) {
//        return tabIcons[position];

        if(mItems.get(position) instanceof MoneyFragment){
            return ((MoneyFragment) mItems.get(position)).getResIcon();
        } else if(mItems.get(position) instanceof NoteFragment){
            return ((NoteFragment) mItems.get(position)).getResIcon();
        } else {
            return 0;
        }
    }
}
