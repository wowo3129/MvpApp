package com.plat.sdk.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;

import java.util.HashMap;

/**
 *
 */
public class HandlerHelper {

    private final String DEFAULT = "DEFAULT-HANDLER";
    /**
     * 这种方式叫内存缓存
     */
    private HashMap<String, HandlerThread> mThreadMap = new HashMap<>();
    private static HandlerHelper mHandlerWorker;

    /**
     * 创建HandlerThread 的实现类,将实例根据线程名存储到对应的Map中
     *
     * @param threadName HandlerThread 的线程名称
     * @return HandlerThread
     */
    private HandlerThread createThread(String threadName) {
        synchronized (this.mThreadMap) {
            HandlerThread handlerThread;
            if ((handlerThread = mThreadMap.get(threadName)) == null) {
                (handlerThread = new HandlerThread("HT-" + (TextUtils.isEmpty(threadName) ? DEFAULT : threadName))).start();
                return this.mThreadMap.put(threadName, handlerThread);
            }
            return handlerThread;
        }
    }

    /**
     * 创建子线程HandlerThread关联的Handler类
     *
     * @param handlerThreadName 线程名
     * @return
     */
    public Handler createThreadHandler(String handlerThreadName) {
        Looper looper = this.createThread(handlerThreadName).getLooper();
        return new Handler(looper);
    }

    /**
     * 创建主线程的Handler
     *
     * @return
     */
    public static Handler createMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    public static synchronized HandlerHelper getInstance() {
        if (mHandlerWorker == null) {
            mHandlerWorker = new HandlerHelper();
        }
        return mHandlerWorker;
    }
}
