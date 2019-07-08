package com.plat.sdk.server;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * The interface Case api.
 *
 * @author ydong
 */
public interface CaseApi {
    /**
     * 服务器地址
     */
    String API = "http://talk.anzer.com.cn:8085/";


    /**
     * @param question  问题
     * @param answer    答案
     * @param scene     场景
     * @param speakType 对话类型
     * @param uuid      机器人唯一标识
     * @return 返回请求结果
     */
    @FormUrlEncoded
    @POST("/api/dialogHis/put")
    Flowable<CasePostSpeakBean> postSpeak(@Field("question") String question,
                                          @Field("answer") String answer,
                                          @Field("scene") String scene,
                                          @Field("speakType") String speakType,
                                          @Field("uuid") String uuid
    );

}
