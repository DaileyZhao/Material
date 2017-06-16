package com.zcm.support.imageselect;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * Created by zcm on 17-6-16.
 */

public class GlideImageLoader implements ImageLoaderStrategy {
    @Override
    public void showImage(View view, String url, ImageLoaderOptions options) {
        if (view instanceof ImageView){
            ImageView imageView= (ImageView) view;
            //装配基本的参数
            DrawableTypeRequest dtr = Glide.with(imageView.getContext()).load(url);
            //装配附加参数
            loadOptions(dtr, options).into(imageView);
        }
    }

    @Override
    public void showImage(View view, int drawableId, ImageLoaderOptions options) {
        if (view instanceof ImageView) {
            ImageView imageView= (ImageView) view;
            DrawableTypeRequest dtr = Glide.with(imageView.getContext()).load(drawableId);
            loadOptions(dtr, options).into(imageView);
        }

    }
    //这个方法用来装载由外部设置的参数
    private DrawableTypeRequest loadOptions(DrawableTypeRequest dtr, ImageLoaderOptions options){
        if (options==null) {
            return dtr;
        }
        if (options.getPlaceHolder()!=-1) {
            dtr.placeholder(options.getPlaceHolder());
        }
        if (options.getErrorDrawable()!=-1){
            dtr.error(options.getErrorDrawable());
        }
        if (options.isCrossFade()) {
            dtr.crossFade();
        }
        if (options.isSkipMemoryCache()){
            dtr.skipMemoryCache(options.isSkipMemoryCache());
        }
        if (options.getAnimator()!=null) {
            dtr.animate(options.getAnimator());
        }
        if (options.getSize()!=null) {
            dtr.override(options.getSize().reWidth,options.getSize().reHeight);
        }
        return dtr;
    }

}
