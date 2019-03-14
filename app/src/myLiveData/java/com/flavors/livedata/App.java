package com.flavors.livedata;

import com.ydong.iflylib.SpeechManager;
import com.plat.sdk.MainApplication;

public class App extends MainApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        SpeechManager.getInstance().init(this);
    }
}
