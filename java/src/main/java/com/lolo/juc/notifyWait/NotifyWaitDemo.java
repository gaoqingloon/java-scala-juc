package com.lolo.juc.notifyWait;

/**
 * 现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1
 *
 * 编程思路:
 *   1. 线程 操作 资源类
 *   2. 高内聚(空调资源制冷和制热) 低耦合(每个人使用空调资源)
 *
 *   3. 判断
 *   4. 干活
 *   5. 通知
 *
 * Object:
 *      hashCode()
 *      equals()
 *      toString()
 *      notify()
 *      wait()
 *      getClass()
 */
public class NotifyWaitDemo {

    public static void main(String[] args) {

        ShareDataProblem shareData = new ShareDataProblem();

        // +1 线程
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        // -1 线程
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
/*
A	1
B	0
A	1
B	0
A	1
B	0
A	1
B	0
A	1
B	0
A	1
B	0
A	1
B	0
A	1
B	0
A	1
B	0
A	1
B	0
 */