package com.lolo.juc.condition;

/**
 * 现在4个线程，可以操作初始值为零的一个变量，
 * 实现2个线程对该变量加1，2个线程对该变量减1
 *
 * 1.编程思路:  -- 上
 *   1. 线程 操作 资源类
 *   2. 高内聚(空调资源制冷和制热) 低耦合(每个人使用空调资源)
 *
 * 2.编程思路:  -- 下
 *   3. 判断(while)
 *   4. 干活
 *   5. 通知
 *
 * 3.防止多线程虚假唤醒
 *    多线程判断用while
 *
 * synchronized : lock
 * wait : await
 * notifyAll : signalAll
 */
public class ConditionSolution {

    public static void main(String[] args) {

        // 共享资源对象
        ConditionShareData shareData = new ConditionShareData();

        // +1 线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        // -1 线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(110);
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        // +1 线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(120);
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        // -1 线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(130);
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
/*
A	1
B	0
C	1
D	0
A	1
B	0
C	1
D	0
A	1
B	0
C	1
D	0
A	1
B	0
C	1
D	0
A	1
B	0
C	1
D	0
A	1
B	0
C	1
B	0
A	1
D	0
C	1
B	0
A	1
D	0
C	1
B	0
A	1
D	0
C	1
B	0
A	1
D	0
C	1
D	0
 */