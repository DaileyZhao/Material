package com.zcm.thunder.banner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcm.support.banner.BannerView;
import com.zcm.support.banner.holder.PagerHolderCreator;
import com.zcm.support.banner.holder.PagerViewHolder;
import com.zcm.support.imageselect.ImageLoaderManager;
import com.zcm.support.imageselect.ImageLoaderOptions;
import com.zcm.thunder.R;
import com.zcm.thunder.THBaseActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zcm on 17-6-15.
 */

public class BannerTestAct extends THBaseActivity<IBannerView,BannerPresenter> implements IBannerView {

    @BindView(R.id.test_bannerView)
    BannerView test_banner_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        test_banner_view.setBannerPageClickListener(new BannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                showToast("Click "+position);
            }
        });
        test_banner_view.setDelayedTime(3000);
        test_banner_view.setDuration(500);
        mPresenter.loadData();
    }

    @Override
    protected BannerPresenter createPresenter() {
        return new BannerPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        test_banner_view.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        test_banner_view.pause();
    }

    @Override
    public void getPicList(List<String> list) {
        test_banner_view.setIndicatorRes(R.drawable.dot_unselect_image,R.drawable.dot_select_image);
        test_banner_view.setPages(list, new PagerHolderCreator() {
            @Override
            public PagerViewHolder createViewHolder() {
                return new ViewImageHolder();
            }
        });
        test_banner_view.start();
    }
    public static class ViewImageHolder implements PagerViewHolder<String> {
        private ImageView mImageView;
        private TextView mTextView;
        @Override
        public View createView(Context context) {
            // 返回ViewPager 页面展示的布局
            View view = LayoutInflater.from(context).inflate(R.layout.view_pager_item,null);
            mImageView = (ImageView) view.findViewById(R.id.viewPager_item_image);
            mTextView = (TextView) view.findViewById(R.id.item_desc);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            // 自己绑定数据，灵活度很大
            ImageLoaderOptions options=new ImageLoaderOptions.Builder()
                    .setCrossFade(true)
                    .setSkipMemoryCache(false)
                    .setPlaceHolder(com.zcm.support.R.drawable.error_picture)
                    .setErrorDrawable(com.zcm.support.R.drawable.error_picture)
                    .build();
            ImageLoaderManager.getSingleton().showImage(mImageView,data,options);
        }
    }
}
