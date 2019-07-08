package com.plat.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.plat.sdk.server.CaseRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * @author ydong
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestServer();
    }

    /**
     * 封装了网络请求的帮助类 和Rxjava调度
     */
    @SuppressLint("CheckResult")
    private void requestServer() {
        CaseRepository.getInstance().postSpeakAIUI("question", "answer", "scene")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(casePostSpeakBean -> {
                            Log.d("SaveSpeakData", "onResult:保存对话记录成功" + casePostSpeakBean.toString());
                        },
                        throwable -> {
                            Log.e("SaveSpeakData", "onResult:保存对话记录失败" + throwable.getMessage());
                        });
    }


}
