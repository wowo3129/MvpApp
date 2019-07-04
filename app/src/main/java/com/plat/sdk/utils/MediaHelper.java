package com.anzer.display.data.navigation;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * @author xuie
 * @date 2018/9/27
 */
public class MediaHelper implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnCompletionListener {
    private static final String TAG = "MediaHelper";
    /**
     * 播放音频控件
     */
    private MediaPlayer mediaPlayer;
    /**
     * 播放地址
     */
    private String url;

    /**
     * 播放完成和当前进度的接口
     */
    private OnMediaListener mOnMediaListener;

    /**
     * 快进和快退的时间
     */
    private final int MOVE_TIME = 15000;

    /**
     * 资源是否准备完成
     */
    private boolean isPrepare;

    private int pausePosition;

    /**
     * 初始化操作，
     */
    public MediaHelper init() {
        mediaPlayer = new MediaPlayer();
        //设置错误监听
        mediaPlayer.setOnErrorListener(this);
        //资源准备完成监听
        mediaPlayer.setOnPreparedListener(this);
        //seek监听
        mediaPlayer.setOnSeekCompleteListener(this);
        //播放完成监听
        mediaPlayer.setOnCompletionListener(this);
        return this;
    }

    /**
     * 切换播放资源
     */
    public MediaHelper changeUrl(String url) {
        //进入资源为准备状态
        isPrepare = false;
        //设置新的链接
        setUrl(url);
        //初始化
        mediaPlayer.reset();
        return this;
    }


    /**
     * 开始播放，开始是在准备结束完成后才播放的
     */
    public void start() {
        Log.d(TAG, "startMission - " + url);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 快进
     */
    public void next() {
        int curPosition = mediaPlayer.getCurrentPosition();
        if ((curPosition + MOVE_TIME) > mediaPlayer.getDuration()) {
            return;
        }
        mediaPlayer.seekTo(curPosition + MOVE_TIME);
    }

    /**
     * 快退
     */
    public void previous() {
        int curPosition = mediaPlayer.getCurrentPosition();
        if ((curPosition - MOVE_TIME) < 0) {
            return;
        }
        mediaPlayer.seekTo(curPosition - MOVE_TIME);
    }

    /**
     * 暂停
     */
    public void pause() {
        if (isPlay()) {
            mediaPlayer.pause();
            pausePosition = mediaPlayer.getCurrentPosition();
        }
    }

    /**
     * 继续播放
     */
    public void resume() {
        if (!isPlay()) {
            mediaPlayer.seekTo(pausePosition);
            mediaPlayer.start();
        }
    }

    /**
     * 手动定位播放位置，在外部滑动seekbar时调用
     */
    public void seekTo(int position) {
        if (position < 0 || position > mediaPlayer.getDuration()) {
            return;
        }
        mediaPlayer.seekTo(position);
    }


    /**
     * 停止播放
     */
    public void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 当前是否在播放
     */
    public boolean isPlay() {
        return mediaPlayer.isPlaying();
    }

    public boolean isPrepare() {
        return isPrepare;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d("mp", "播放失败: " + what + "--" + extra);
        //播放完成
        isPrepare = false;
        mp.reset();
        mOnMediaListener.onComplete(false);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //准备完成，开始播放，并修改状态
        isPrepare = true;
        mp.start();
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //播放完成
        isPrepare = false;
        mp.reset();
        mOnMediaListener.onComplete(true);
    }

    public interface OnMediaListener {
        /**
         * complete
         *
         * @param state true or false
         */
        void onComplete(boolean state);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MediaHelper setMediaListener(OnMediaListener mOnSeekListener) {
        this.mOnMediaListener = mOnSeekListener;
        return this;
    }
}