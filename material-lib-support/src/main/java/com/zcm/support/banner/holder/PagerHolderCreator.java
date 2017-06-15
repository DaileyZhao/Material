package com.zcm.support.banner.holder;

/**
 * Created by zhouwei on 17/5/26.
 */

public interface PagerHolderCreator<VH extends PagerViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}
