package com.flavors.xiaomi.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.plat.sdk.R;

/**
 * @author ydong
 */
public class AudioServiceActivity extends Activity implements OnClickListener {

    Button startPlayButton;
    Button stopPlayButton;
    Button havaFunButton;
    Intent playServiceIntent;

    private BackgrondAudioService baService;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_service);

        startPlayButton = findViewById(R.id.StartPlaybackButton);
        stopPlayButton = findViewById(R.id.StopPlaybackButton);
        havaFunButton = findViewById(R.id.HavaFunButton);

        startPlayButton.setOnClickListener(this);
        stopPlayButton.setOnClickListener(this);
        havaFunButton.setOnClickListener(this);

        playServiceIntent = new Intent(this, BackgrondAudioService.class);

        serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                baService = null;
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                baService = ((BackgrondAudioService.BackgroundAudioServiceBinder) service).getService();
            }
        };
    }

    @Override
    public void onClick(View v) {

        if (v == startPlayButton) {
            startService(playServiceIntent);
            bindService(playServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else if (v == stopPlayButton) {
            unbindService(serviceConnection);
            stopService(playServiceIntent);
        } else if (v == havaFunButton) {
            baService.haveFun();
        }
    }


}
