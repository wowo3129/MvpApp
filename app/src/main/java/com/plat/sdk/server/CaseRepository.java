package com.plat.sdk.server;


import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ydong
 */
public class CaseRepository {

    private CaseApi caseApi = HttpHelper.getInstance().createService(CaseApi.class);

    /**
     * 功能：只针对保存AIUI的语料记录
     *
     * @param question 问题
     * @param answer   答案
     * @param scene    AIUI场景
     * @return 请求返回结果
     */
    public Flowable<CasePostSpeakBean> postSpeakAIUI(String question, String answer, String scene) {
        return caseApi.postSpeak(question, answer, scene, "SPEAK_TYPE_AIUI", "01002dae2963817b8297")
                .subscribeOn(Schedulers.newThread());
    }

    ////////////////////////////////////////////////////////////////////////

    private static CaseRepository instance;

    private CaseRepository() {
    }

    public static CaseRepository getInstance() {
        if (instance == null) {
            synchronized (CaseRepository.class) {
                if (instance == null) {
                    instance = new CaseRepository();
                }
            }
        }
        return instance;
    }
}
