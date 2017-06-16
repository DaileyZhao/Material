package com.zcm.support.imageselect;

import android.view.View;

/**
 * Created by zcm on 17-6-16.
 */

public interface ImageLoaderStrategy {
    void showImage(View view,String url,ImageLoaderOptions options);
    void showImage(View view,int drawableId,ImageLoaderOptions options);
}
