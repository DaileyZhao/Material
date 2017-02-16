package com.zcm.util.RetrofitUtil;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by xiyouquan on 17-2-16.
 */

public class RetrofitTest {
    HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
    public Retrofit builder(){
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://www.kuaidi100.com/")
                .client(new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build())
                .build();
        return retrofit;
    }
    public RetrofitApi create(){
        RetrofitApi api=builder().create(RetrofitApi.class);
        return api;
    }
}
