package com.flavors.xiaomi.RxJavaAndRetrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApi {

    @GET("api/data/{category}/{size}/{page}")
    Call<GankBean> getData(@Path("category") String category,
                           @Path("size") String size,
                           @Path("page") String page);

}
