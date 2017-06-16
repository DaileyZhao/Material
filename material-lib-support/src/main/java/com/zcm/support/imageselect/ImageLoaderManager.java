package com.zcm.support.imageselect;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by zcm on 17-6-16.
 */

public class ImageLoaderManager implements ImageLoaderStrategy{
    private static ImageLoaderManager instance;
    private ImageLoaderStrategy imageLoader;
    private ImageLoaderManager(){
        imageLoader=new GlideImageLoader();
    }
    public static ImageLoaderManager getSingleton(){
        if (instance==null) {
            instance=new ImageLoaderManager();
        }
        return instance;
    }
    public void setImageLoader(ImageLoaderStrategy imageLoader){
        if (imageLoader!=null){
            this.imageLoader=imageLoader;
        }
    }
    @Override
    public void showImage(@NonNull View mView, @NonNull String mUrl, @Nullable ImageLoaderOptions options) {

        imageLoader.showImage(mView,mUrl,options);
    }


    @Override
    public void showImage(@NonNull  View mView, @NonNull int mDraeable, @Nullable ImageLoaderOptions options) {
        imageLoader.showImage(mView,mDraeable,options);
    }
}
