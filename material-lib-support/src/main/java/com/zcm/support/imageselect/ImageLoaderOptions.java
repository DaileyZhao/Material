package com.zcm.support.imageselect;

import com.bumptech.glide.request.animation.ViewPropertyAnimation;

/**
 * Created by zcm on 17-6-16.
 */

public class ImageLoaderOptions {
    private int placeHolder = -1; //当没有成功加载的时候显示的图片
    private ImageReSize size = null; //重新设定容器宽高
    private int errorDrawable = -1;  //加载错误的时候显示的drawable
    private boolean isCrossFade = false; //是否渐变平滑的显示图片
    private boolean isSkipMemoryCache = false; //是否跳过内存缓存
    private ViewPropertyAnimation.Animator animator = null; // 图片加载动画


    private ImageLoaderOptions(ImageReSize resize, int placeHolder, int errorDrawable, boolean isCrossFade, boolean isSkipMemoryCache, ViewPropertyAnimation.Animator animator) {
        this.placeHolder = placeHolder;
        this.size = resize;
        this.errorDrawable = errorDrawable;
        this.isCrossFade = isCrossFade;
        this.isSkipMemoryCache = isSkipMemoryCache;
        this.animator = animator;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public ImageReSize getSize() {
        return size;
    }

    public void setSize(ImageReSize size) {
        this.size = size;
    }

    public int getErrorDrawable() {
        return errorDrawable;
    }

    public void setErrorDrawable(int errorDrawable) {
        this.errorDrawable = errorDrawable;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public void setCrossFade(boolean crossFade) {
        isCrossFade = crossFade;
    }

    public boolean isSkipMemoryCache() {
        return isSkipMemoryCache;
    }

    public void setSkipMemoryCache(boolean skipMemoryCache) {
        isSkipMemoryCache = skipMemoryCache;
    }

    public ViewPropertyAnimation.Animator getAnimator() {
        return animator;
    }

    public void setAnimator(ViewPropertyAnimation.Animator animator) {
        this.animator = animator;
    }

    public static class Builder {
        private int placeHolder = -1;
        private ImageReSize size = null;
        private int errorDrawable = -1;
        private boolean isCrossFade = false;
        private boolean isSkipMemoryCache = false;
        private ViewPropertyAnimation.Animator animator = null;

        public Builder() {
        }

        public Builder setPlaceHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder setResize(int reWidth, int reHeight) {
            size = new ImageReSize(reWidth, reHeight);
            return this;
        }

        public Builder setErrorDrawable(int errorDrawable) {
            this.errorDrawable = errorDrawable;
            return this;
        }

        public Builder setCrossFade(boolean isCrossFade) {
            this.isCrossFade = isCrossFade;
            return this;
        }

        public Builder setSkipMemoryCache(boolean isSkipMemoryCache) {
            this.isSkipMemoryCache = isSkipMemoryCache;
            return this;
        }

        public Builder setAnimator(ViewPropertyAnimation.Animator animator) {
            this.animator = animator;
            return this;
        }

        public ImageLoaderOptions build() {
            return new ImageLoaderOptions(size, placeHolder, errorDrawable, isCrossFade, isSkipMemoryCache, animator);
        }
    }

    public static class ImageReSize {
        int reWidth = 0;
        int reHeight = 0;

        public ImageReSize(int reWidth, int reHeight) {
            if (reHeight <= 0) {
                reHeight = 0;
            }
            if (reWidth <= 0) {
                reWidth = 0;
            }
            this.reHeight = reHeight;
            this.reWidth = reWidth;

        }

    }
}
