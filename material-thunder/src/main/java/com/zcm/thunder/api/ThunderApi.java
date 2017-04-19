package com.zcm.thunder.api;

import com.zcm.thunder.splash.PicBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zcm on 17-4-18.
 */

public interface ThunderApi {
    @GET("http://api.huceo.com/meinv/?key=86a28560b4dd8234233b390691884b36")
    Observable<PicBean> meinv(@Query("num") int count);
}
