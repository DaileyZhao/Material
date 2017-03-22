package com.zcm.util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zcm on 17-2-16.
 */

public interface RetrofitApi {
    @GET("query")
    Call<String> getPostDetail(@Query("type") String type,@Query("postid") int postid);
}
