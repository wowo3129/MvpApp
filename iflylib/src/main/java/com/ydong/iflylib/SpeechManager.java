package com.ydong.iflylib;

import android.content.Context;

import com.ydong.iflylib.listener.IRecognizerListener;
import com.ydong.iflylib.listener.ISpeakListener;
import com.ydong.iflylib.listener.SpeakListener;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by kermitye
 * Date: 2018/5/23 14:50
 * Desc: 讯飞语音管理类
 *
 * @author ydong
 */
public class SpeechManager {

    private SpeechManager() {

    }

    private static class SingletonHolder {
        public static final SpeechManager INSTANCE = new SpeechManager();
    }

    public static SpeechManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 在application 中初始化语音基础模块
     *
     * @param context
     */
    public void init(Context context) {
        SpeechUtility.createUtility(context, "appid=" + context.getString(R.string.appid));
    }

    /**
     * 初始化语音识别和合成模块
     *
     * @param context
     */
    public void initStart(Context context) {
        RecognizerHelper.getInstance().init(context);
        TtsHelper.getInstance().init(context);
    }

    //=====================语音听写=================================

    public void setRecognizerListener(IRecognizerListener listener) {
        RecognizerHelper.getInstance().setIRecognizerListener(listener);
    }


    public void startReco() {
        RecognizerHelper.getInstance().startRecognizer();
    }

    public void stopReco() {
        RecognizerHelper.getInstance().stopRecognizer();
    }

    public void destoryReco() {
        RecognizerHelper.getInstance().destory();
    }


    //======================语音合成=======================================

    public void setSpeakListener(final ISpeakListener listener) {
        TtsHelper.getInstance().setSpeakListener(new SpeakListener() {
            @Override
            public void onSpeakBegin(String text) {
                listener.onSpeakBegin(text);
            }

            @Override
            public void onSpeakOver(String msg) {
                listener.onSpeakOver(msg);
            }

            @Override
            public void onInterrupted() {
                listener.onInterrupted();
            }
        });
    }

    /**
     * 开水合成
     *
     * @param text     合成的文字
     * @param listener 合成结束后的回调
     */
    public void startSpeak(String text, final ISpeakListener listener) {
        TtsHelper.getInstance().startSpeak(text, new SpeakListener() {
            @Override
            public void onSpeakBegin(String text) {
                listener.onSpeakBegin(text);
            }

            @Override
            public void onSpeakOver(String msg) {
                listener.onSpeakOver(msg);
            }

            @Override
            public void onInterrupted() {
                listener.onInterrupted();
            }
        });
    }

    /**
     * 开始合成
     *
     * @param text 待合成文字
     */
    public void startSpeak(String text) {
        TtsHelper.getInstance().startSpeak(text);
    }

    /**
     * @return 是否正在合成的状态
     */
    public boolean isSpeaking() {
        return TtsHelper.getInstance().isSpeaking();
    }

    /**
     * 停止合成
     */
    public void stopSpeak() {
        TtsHelper.getInstance().stopSpeak();
    }

    /**
     * 退出时销毁合成对象
     */
    public void destorySpeak() {
        TtsHelper.getInstance().destory();
    }

    /**
     * 销毁识别和合成
     */
    public void release() {
        destoryReco();
        destorySpeak();
    }


}
