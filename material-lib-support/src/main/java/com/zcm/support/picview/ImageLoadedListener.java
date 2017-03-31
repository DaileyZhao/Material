package com.zcm.support.picview;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public interface ImageLoadedListener {
    void onSuccess(ImageView imageView, Drawable drawable);

    void onError(ImageView imageView);
}
