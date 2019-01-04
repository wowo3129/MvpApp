package com.flavors.xiaomi.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;

import com.plat.sdk.R;

/**
 * @author ydong
 */
public class BackgrondAudioService extends Service implements OnCompletionListener {

    MediaPlayer mediaPlayer;
    private final IBinder basBinder = new BackgroundAudioServiceBinder();

    public class BackgroundAudioServiceBinder extends Binder {

        BackgrondAudioService getService() {
            return BackgrondAudioService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return basBinder;
    }

    public void haveFun() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 500);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.deep);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
}








