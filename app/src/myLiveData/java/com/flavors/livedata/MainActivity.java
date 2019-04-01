package com.flavors.livedata;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
 * @link {https://blog.csdn.net/Alexwll/article/details/83302173}
 * @link {https://developer.android.google.cn/jetpack/}
 */
public class MainActivity extends AppCompatActivity implements ISpeakListener, IRecognizerListener {

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
        SpeechManager.getInstance().setRecognizerListener(this);
        /*全局合成监听 适合音量相关UI全局显示*/
        SpeechManager.getInstance().setSpeakListener(this);
        SpeechManager.getInstance().startReco();
    }

    private void initModel() {
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
    }

    private void observerModel() {
        myViewModel.getMutableLiveData().observe(this, integer -> {
            Log.d("ydong", "result::" + integer);
        });
        myViewModel.getResultLiveData().observe(this, s -> {
            if (s.equals("安静")) {
                SpeechManager.getInstance().stopSpeak();
            }
        });
    }


    @Override
    public void onSpeakBegin(String text) {
        // TODO: 2019/3/14 开始合成
    }

    @Override
    public void onSpeakOver(String msg) {
        // TODO: 2019/3/14 合成结束
    }

    @Override
    public void onInterrupted() {
        // TODO: 2019/3/14 合成中断
    }

    @Override
    public void onVolumeChanged(int volume) {
        // TODO: 2019/3/14 音量改变回调
    }

    @Override
    public void onResult(String result) {
        // TODO: 2019/3/14 语音识别结果
        myViewModel.getResultLiveData().setValue(result);
        requestApiByRetrofit_RxJava(result);
    }

    @Override
    public void onError(String msg) {
        // TODO: 2019/3/14 语音识别错误
    }

    // 请求图灵API接口，获得问答信息
    private void requestApiByRetrofit_RxJava(String info) {
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

    // 处理获得到的问答信息
    private void handleResponseMessage(MessageEntity entity) {
        if (entity == null) return;
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
