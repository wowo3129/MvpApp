package com.flavors.ifly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.plat.sdk.R;
import com.ydong.iflylib.SpeechManager;
import com.ydong.iflylib.listener.IRecognizerListener;
import com.ydong.iflylib.listener.ISpeakListener;

/**
 * @author ydong
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVoice();

    }

    private void initVoice() {
        SpeechManager.getInstance().initStart(this);
        SpeechManager.getInstance().setRecognizerListener(iRecognizerListener);
        /*全局显示对话记录回调*/
        SpeechManager.getInstance().setSpeakListener(iSpeakListener);
        SpeechManager.getInstance().startReco();
    }

    /**
     * 语音识别回调
     */
    IRecognizerListener iRecognizerListener = new IRecognizerListener() {
        @Override
        public void onVolumeChanged(int volume) {

        }

        @Override
        public void onResult(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            SpeechManager.getInstance().startSpeak(result);
        }

        @Override
        public void onError(String msg) {

        }
    };

    /**
     * 语音合成回调
     */
    ISpeakListener iSpeakListener = new ISpeakListener() {
        @Override
        public void onSpeakBegin(String text) {

        }

        @Override
        public void onSpeakOver(String msg) {

        }

        @Override
        public void onInterrupted() {

        }
    };


}
