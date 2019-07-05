package com.flavors.ifly;

import com.ydong.iflylib.SpeechManager;
import com.plat.sdk.MainApplication;

/**
 * @author ydong
 */
public class App extends MainApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        SpeechManager.getInstance().init(this);
    }
}
