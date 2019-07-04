知识点：
讯飞的全双工在线语音识别+合成封装+图灵的应答+ livedata + ViewModel

参考链接：
https://blog.csdn.net/Alexwll/article/details/83302173
https://developer.android.google.cn/jetpack/

使用讯飞语音库的步骤
```
    SpeechManager 是唯一的对外接口类，通过该类管理识别 合成
    
    step1:     
    SpeechManager.getInstance().init(this);
    
    step2:
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
    
```