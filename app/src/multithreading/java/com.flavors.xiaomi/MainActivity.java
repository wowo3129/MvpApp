package com.flavors.xiaomi;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.plat.sdk.R;
import com.plat.sdk.utils.HandlerHelper;


/**
 * @author ydong
 */
public class MainActivity extends Activity {
    public static final String TAG = "xiaomi::MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HandlerHelper.createMainHandler().postDelayed(() -> {
            Log.d(TAG, "" + (Looper.myLooper() == Looper.getMainLooper()));
        }, 1000);
        HandlerHelper.getInstance().createThreadHandler("theradname").postDelayed(() -> {
            Log.d(TAG, "" + (Looper.myLooper() == Looper.getMainLooper()));
        }, 1000);
    }


}
