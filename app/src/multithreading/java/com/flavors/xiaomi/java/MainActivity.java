package com.flavors.xiaomi.java;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.plat.sdk.R;
import com.plat.sdk.utils.HandlerHelper;


/**
 * @author ydong
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {

        super.onResume();
        Toast.makeText(this, "haha", Toast.LENGTH_SHORT).show();
/*
        new Handler().postDelayed(() -> Log.d("ydong", "ydong 你好"), 1111);
        HandlerHelper.createMainHandler().postDelayed(() -> Log.d(TAG, "ydong-->" + (Looper.myLooper() == Looper.getMainLooper())), 1000);
*/

//        HandlerHelper instance = HandlerHelper.getInstance();
//        Handler handler = instance.createThreadHandler("theradname");
//        handler.postDelayed(() -> Log.d(TAG, "Thread--> " + (Looper.myLooper() == Looper.getMainLooper())), 1000);

/*        HandlerThread handlerThread = new HandlerThread("0001");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {

                    int obj = (int) msg.obj;
                    Log.d("ydong", "obj::" + obj);
                }
            }
        };
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = 1000;
        handler.sendMessage(msg);*/

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        String[] strings = {"12", "122","1234","12345"};
        myAsyncTask.execute(strings);

    }

    public static void main(String[] args) {
        String str = "09:24:09.588 D/ydong [main, processIATResult(NlpDemo.java:600)]: FINAL_RESULT---->深圳今天的天气怎么样<----【3】";
        String ls = str.substring(str.indexOf("---->") + 1, str.lastIndexOf("<----"));
        System.out.println(ls);
    }
}
