package com.lolo.juc.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * A synchronization aid that allows a set of threads to all wait
 * for each other to reach a common barrier point.
 *
 * CyclicBarrier
 * 的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有
 * 被屏障拦截的线程才会继续干活。
 * 线程进入屏障通过CyclicBarrier的await()方法。
 *
 * 集齐7颗龙珠就可以召唤神龙
 */
public class CyclicBarrierDemo {

    private static final int NUMBER = 7;

    public static void main(String[] args) {

        // CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("---召唤神龙");
        });

        for (int i = 1; i <= NUMBER; i++) {
            final int tmpI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第: " + tmpI);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
/*
2	收集到第: 2
1	收集到第: 1
5	收集到第: 5
4	收集到第: 4
6	收集到第: 6
3	收集到第: 3
7	收集到第: 7
---召唤神龙
 */
