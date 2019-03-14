package com.ydong.iflylib.listener;

import android.os.Bundle;

import com.ydong.iflylib.Logger;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by kermitye
 * Date: 2018/5/23 19:05
 * Desc:
 */
public abstract class SpeakListener implements SynthesizerListener,ISpeakListener {
    @Override
    public void onSpeakBegin() {
        Logger.error("开始播放");
    }

    @Override
    public void onSpeakPaused() {
        Logger.error("暂停播放");
    }

    @Override
    public void onSpeakResumed() {
        Logger.error("继续播放");
    }

    @Override
    public void onBufferProgress(int percent, int beginPos, int endPos,
                                 String info) {
        // 合成进度
//            mPercentForBuffering = percent;
        Logger.error("缓冲进度为" + percent + "%");
    }

    @Override
    public void onSpeakProgress(int percent, int beginPos, int endPos) {
        // 播放进度
//            mPercentForPlaying = percent;
        Logger.error("播放进度为" + percent + "%");
    }

    @Override
    public void onCompleted(SpeechError error) {
        if (error == null) {
//            Logger.error("播放完成");
            onSpeakOver("");
        } else if (error != null) {
            Logger.error(error.getPlainDescription(true));
            onSpeakOver(error.getPlainDescription(true));
        }

    }

    @Override
    public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
        // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
        // 若使用本地能力，会话id为null
        //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
        //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
        //		Log.d(TAG, "session id =" + sid);
        //	}

        /**
         * 被打断
         */
        if (eventType == 21002) {
            onInterrupted();
                /*if (SpeechHandler.this.lastSpeakListener != null) {
                    SpeechHandler.this.lastSpeakListener.onInterrupted();
                }

                if (SpeechHandler.this.mSpeakListenerTemp != null) {
                    SpeechHandler.this.mSpeakListenerTemp.onInterrupted();
                }*/
        }
    }
}
