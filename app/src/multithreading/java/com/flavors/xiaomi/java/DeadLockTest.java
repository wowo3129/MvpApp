package com.flavors.xiaomi.java;

/**
 * 解释：在main方法中，实例化了两个实现了Runnable接口的DeadLockTest对象test1和test2，test1的flag等于1，
 * 所以在thread1线程执行的时候执行的是run()方法后半部分的代码，test2的flag等于2，
 * 所以在thread2线程启动的时候执行的是run()方法前半部分的代码，
 * 此时，出现了下列现象：thread1线程占有了o1对象并等待o2对象，而thread2线程占有了o2对象并等待o1对象，
 * 而o1和o2又被这俩个线程所共享，所以就出现了死锁的问题了。
 * <p>
 * 解释：死锁的意思其实就是相互等待。一个生活中的简单例子：我们去包子铺吃包子。
 * 客户坚持：先吃包子，后付钱。
 * 卖家坚持：先付钱以后，才能吃包子。
 * </p>
 *
 * @author ydong
 * {@code 死锁代码案例：https://blog.csdn.net/zll793027848/article/details/8670546}
 * {@code 多线程-死锁代码展示 https://blog.csdn.net/lw_power/article/details/52012761}
 */
public class DeadLockTest implements Runnable {
    private int flag;
    /**
     * 静态的对象，被DeadLockTest的所有实例对象所公用
     */
    static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {

        System.out.println(flag);
        if (flag == 0) {
            synchronized (o1) {
                System.out.println("test2线程占用了资源 o1");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("test2线程等待资源 o2的释放");
                synchronized (o2) {
                }
            }
        }
        if (flag == 1) {
            synchronized (o2) {
                System.out.println("test1线程占用了资源 o2");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("test1线程等待资源 o1的释放");
                synchronized (o1) {
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLockTest test1 = new DeadLockTest();
        DeadLockTest test2 = new DeadLockTest();
        test1.flag = 1;
        test2.flag = 0;
        Thread thread1 = new Thread(test1);
        Thread thread2 = new Thread(test2);
        thread1.start();
        thread2.start();
    }
}




