package com.flavors.xiaomi.java;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;

/**
 * 功能点：RxJava2 Flowable以及背压
 * <p>
 * 介绍：背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略。
 * https://www.cnblogs.com/shwo/p/9874680.html
 */
class FlowableTest {
    public static void main(String[] args) {
        FlowableTest helloFlowable = new FlowableTest();
        helloFlowable.helloFlowable();
    }

    public void helloFlowable() {
        /*基本上和Observable一样*/
        Flowable.create((FlowableOnSubscribe<Integer>) emitter -> {
            Integer i = 0;
            while (i < 9) {
                i++;
                System.out.println("现在发送到的信号是:" + i);
                emitter.onNext(i);
            }
        }, BackpressureStrategy.ERROR).subscribe(new Subscriber<Integer>() {
            private Subscription sub;

            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
                this.sub = s;
            }

            @Override
            public void onNext(Integer integer) {
                if (integer == 4) {
                    sub.cancel();
                } else {
                    System.out.println("现在接收到的信号是:" + integer);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError()");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete()");

            }
        });
    }
}
