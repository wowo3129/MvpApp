package com.flavors.livedata;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.plat.sdk.R;
import com.ydong.iflylib.SpeechManager;
import com.ydong.iflylib.listener.IRecognizerListener;
import com.ydong.iflylib.listener.ISpeakListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ydong
 */
public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVoice();
        initModel();
        observerModel();

    }

    private void initVoice() {
        SpeechManager.getInstance().initStart(this);
        SpeechManager.getInstance().setRecognizerListener(iRecognizerListener);
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
            myViewModel.getResultLiveData().setValue(result);
            requestApiByRetrofitRxJava(result);
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

    private void initModel() {
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
    }

    private void observerModel() {
        myViewModel.getMutableLiveData().observe(this, integer -> {
            Log.d("ydong", "result::" + integer);
        });
        myViewModel.getResultLiveData().observe(this, s -> {
            if ("安静".equals(s)) {
                SpeechManager.getInstance().stopSpeak();
            }
        });
    }

    /**
     * 请求图灵API接口，获得问答信息
     */
    private void requestApiByRetrofitRxJava(String info) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TulingParams.TULING_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        api.getTuringInfoByRxJava(TulingParams.TULING_KEY, info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseMessage, Throwable::printStackTrace);
    }

    /**
     * 处理获得到的问答信息
     */
    private void handleResponseMessage(MessageEntity entity) {
        if (entity == null) {
            return;
        }
        Log.d("ydong", "entity.getText()" + entity.getText());
        SpeechManager.getInstance().startSpeak(entity.getText());
        switch (entity.getCode()) {
            case TulingParams.TulingCode.URL:
                entity.setText(entity.getText() + "，点击网址查看：" + entity.getUrl());
                break;
            case TulingParams.TulingCode.NEWS:
                entity.setText(entity.getText() + "，点击查看");
                break;
            case TulingParams.TulingCode.TEXT:
                entity.setText(entity.getText() + "，点击查看");
                break;
            default:
                String text = entity.getText();
                SpeechManager.getInstance().startSpeak(text);
                break;
        }
    }


}
