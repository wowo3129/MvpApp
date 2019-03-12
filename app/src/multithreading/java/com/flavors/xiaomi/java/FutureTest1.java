package com.flavors.xiaomi.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author ydong
 */
public class FutureTest1 {

    public static void main(String[] args) {
        /**
         *新建异步任务
         */
        WorkerRunnable workerRunnable = new WorkerRunnable() {
            /**
             * @return 返回异步任务的执行结果
             */
            @Override
            public Integer call() {
                int i = 0;
                for (; i < 10; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "_" + i);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return i;
            }
        };

        FutureTask<Integer> future = new FutureTask<Integer>(workerRunnable) {
            // 异步任务执行完成，回调
            @Override
            protected void done() {
                try {
                    System.out.println("future.done():" + get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };


        // 创建线程池（使用了预定义的配置）
        threadExec(future);
        try {
            Thread.sleep(600);
            // 可以取消异步任务
            future.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // 阻塞，等待异步任务执行完毕-获取异步任务的返回值
            System.out.println("future.get():" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 任务丢到可缓冲线程池里执行
     *
     * @param future
     */
    private static void threadExec(FutureTask<Integer> future) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(future);
    }

    /**
     * 异步任务
     */
    interface WorkerRunnable extends Callable<Integer> {
    }

}

