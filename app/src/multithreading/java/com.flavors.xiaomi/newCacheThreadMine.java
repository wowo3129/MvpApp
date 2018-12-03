package com.flavors.xiaomi;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class newCacheThreadMine {

    public static void thread1() {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> {
                Log.d("ydong", "thread --> " + Thread.currentThread().getName() + "i= " + finalI);
            });
        }
    }

}
