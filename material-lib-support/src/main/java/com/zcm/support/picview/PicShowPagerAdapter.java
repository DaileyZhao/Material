package com.zcm.support.picview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.ArrayList;

/**
 * ViewPager Adapter
 */
public class PicShowPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> mLists = new ArrayList<>();


    public PicShowPagerAdapter(FragmentManager fm, ArrayList<String> urls) {
        super(fm);
        if (null != urls)
            for (String url : urls) {
                Fragment f = PicDetailFragment.newInstance(url);
                mLists.add(f);
            }
    }

    public Fragment getItem(int i) {
        return mLists.get(i);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public int getCount() {
        return mLists.size();
    }
}
