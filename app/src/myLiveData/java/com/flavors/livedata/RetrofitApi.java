package com.flavors.livedata;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sunfusheng on 2016/3/20.
 */
public interface RetrofitApi {

    /**
     * 请求图灵API接口，获得问答信息
     */
    @GET("api")
    Call<MessageEntity> getTuringInfo(@Query("key") String key, @Query("info") String info);

    /**
     * 请求图灵API接口，获得问答信息
     */
    @GET("api")
    Observable<MessageEntity> getTuringInfoByRxJava(@Query("key") String key, @Query("info") String info);

}
