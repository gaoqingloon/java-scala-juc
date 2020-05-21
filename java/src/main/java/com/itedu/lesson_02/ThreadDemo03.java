package com.itedu.lesson_02;

/**
 * 证明：同步方法 使用的是this锁
 *  - 同步代码块使用this锁
 *  - 同步方法
 *  两者都使用，若相同
 *
 * 什么是线程不安全？
 *  当多个线程同时操作同一个共享的全局变量，可能会受到其他线程的干扰，会发生数据冲突问题
 *
 * 线程不安全问题如何解决？
 *  1.使用代码块synchronized jdk1.5并发包lock
 *      使用synchronized 代码块，每次只允许一个线程执行
 */
public class ThreadDemo03 {

    public static void main(String[] args) throws InterruptedException {

        // 线程类定义一个实例
        ThreadTrain03 threadTrain = new ThreadTrain03();

        // 1.创建两个线程
        new Thread(threadTrain).start();

        Thread.sleep(40);
        threadTrain.flag = false;

        new Thread(threadTrain).start();
    }
}


class ThreadTrain03 implements Runnable {

    // 火车票总数
    private int count = 100;
    private final Object obj = new Object();
    public boolean flag = true;

    @Override
    public void run() {

        // 线程1 flag为 true
        // 线程2 flag为 false
        if (flag) {
            while (count > 0) {
                // 使用this锁，若使用obj 会出现101张票
                synchronized (this) {
                    if (count > 0) {
                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ", 出售第" + (100 - count + 1) + "张票");
                        count--;
                    }
                }
            }
        }
        else {
            while (count > 0) {
                show();
            }
        }
    }

    /**
     * 在方法上面添加 synchronized ，此时方法变成 同步方法
     */
    public synchronized void show() {
        if (count > 0) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", 出售第" + (100 - count + 1) + "张票");
            count--;
        }
    }
}
