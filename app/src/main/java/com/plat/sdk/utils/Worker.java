package com.plat.sdk.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Project Name：SDK_Test_All
 * Description ：快速线程
 *
 * @author ZouWeiLin on 2018.04.17
 */
public class Worker {

    private final int CORE_NUM = Runtime.getRuntime().availableProcessors();
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    /**
     * 多线程池
     */
    private ExecutorService threadPoolExecutor = new ThreadPoolExecutor(CORE_NUM, CORE_NUM * 2,
            5, TimeUnit.SECONDS,
            workQueue, ThreadFactoryHelper.getThreadFactor("utils multiple " + Worker.class.getSimpleName()));
    /**
     * 单线程池
     */
    private ExecutorService singleThread = new ThreadPoolExecutor(1, 1,
            5, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), ThreadFactoryHelper.getThreadFactor("utils single " + Worker.class.getSimpleName()));

    private Worker() {
        //no instance
    }

    public void executeSingle(Runnable runnable) {
        singleThread.execute(runnable);
    }

    /**
     * 休眠
     *
     * @param sleepTime 休眠时间/ms
     */
    public int sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
            return 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 使用线程池中的一个线程来执行任务
     *
     * @param runnable 任务
     */
    public void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return threadPoolExecutor.submit(callable);
    }

    public static Worker newInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final Worker INSTANCE = new Worker();
    }

    /**
     * 创建一个单线程
     *
     * @param threadName 线程名称
     * @param daemon     是否是守护线程
     * @return 线程池
     */
    public static ExecutorService createSingleThread(String threadName, boolean daemon) {
        return new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), ThreadFactoryHelper.getThreadFactor(threadName, daemon));
    }

    public static ScheduledExecutorService createScheduledThread(String name) {
        return new ScheduledThreadPoolExecutor(1, ThreadFactoryHelper.getThreadFactor(name));
    }

    public static void newSingleThread() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.NANOSECONDS, new LinkedBlockingQueue<Runnable>(), Worker.getFactory());
    }

    static ThreadFactory getFactory() {
        return r -> {
            Thread thread = new Thread(r, "123");
            return thread;
        };
    }
}
