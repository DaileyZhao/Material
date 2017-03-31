package com.zcm.support.picview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.zcm.support.R;
import com.zcm.support.base.BaseActivity;
import com.zcm.support.mvp.IPresenter;

import java.util.ArrayList;

/**
 * Created by zcm on 17-3-31.
 */

public class PicShowActivity extends BaseActivity {
    private ViewPager viewPager;
    private PicShowPagerAdapter mAdapter;
    private ArrayList<String> mLists = new ArrayList<>();
    private int index = 0;

    public final static String KEY_URL = "KEY_URL";
    public final static String KEY_INDEX = "KEY_INDEX";
    public final static String KEY_URLS = "KEY_URLS";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getIntent() && null != getIntent().getExtras()) {
            if (null != getIntent().getExtras().getStringArrayList(KEY_URLS)
                    && 0 < getIntent().getExtras().getStringArrayList(KEY_URLS).size()) {
                mLists.addAll(getIntent().getExtras().getStringArrayList(KEY_URLS));
            } else if (null != getIntent().getExtras().getString(KEY_URL)) {
                mLists.add(getIntent().getExtras().getString(KEY_URL));
            } else {
                finish();
                return;
            }

            if (-1 != getIntent().getIntExtra(KEY_INDEX, -1)) {
                index = getIntent().getIntExtra(KEY_INDEX, -1);
                if (index < 0 || index >= mLists.size()) {
                    index = -1;
                }
            }
        } else {
            finish();
        }

        setContentView(R.layout.activity_images_show);
        setActivityTitle((index + 1) + "/" + mLists.size());
        viewPager = (ViewPager) findViewById(R.id.vp);
        mAdapter = new PicShowPagerAdapter(getSupportFragmentManager(), mLists);
        viewPager.setOffscreenPageLimit(mLists.size());
        viewPager.setAdapter(mAdapter);
        //set current index
        if (-1 != index) {
            viewPager.setCurrentItem(index);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setActivityTitle((position + 1) + "/" + mLists.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
}
