package com.plat.sdk.utils;


import java.util.concurrent.ThreadFactory;

/**
 * Project Name：AnzerPlatServiceDemo
 * Description ：
 *
 * @author ：ZouWeiLin on 2018.08.08
 */
public class ThreadFactoryHelper {

    /**
     * 创建线程工程
     *
     * @param threadName 线程名称
     * @param daemon     是否是守护进程
     * @return 线程工厂
     */
    public static ThreadFactory getThreadFactor(String threadName, boolean daemon) {
        return r -> {
            Thread thread = new Thread(r,threadName);
            thread.setDaemon(daemon);
            return thread;
        };
    }

    /**
     * 创建线程工程
     *
     * @param threadName 线程名称
     * @return 线程工厂
     */
    public static ThreadFactory getThreadFactor(String threadName) {
        return getThreadFactor(threadName, false);
    }
}
