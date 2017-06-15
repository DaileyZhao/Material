package com.zcm.thunder.banner;

import com.zcm.support.mvp.IBaseView;

import java.util.List;

/**
 * Created by zcm on 17-6-15.
 */

public interface IBannerView extends IBaseView {
    void getPicList(List<String> list);
}
