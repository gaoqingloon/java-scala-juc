package com.itedu.lesson_02;

/**
 * 使用同步函数（同步方法）
 *  - 同步函数使用的是this锁
 *
 * 什么是线程不安全？
 *  当多个线程同时操作同一个共享的全局变量，可能会受到其他线程的干扰，会发生数据冲突问题
 *
 * 线程不安全问题如何解决？
 *  1.使用代码块synchronized jdk1.5并发包lock
 *      使用synchronized 代码块，每次只允许一个线程执行
 */
public class ThreadDemo02 {

    public static void main(String[] args) {

        // 线程类定义一个实例
        ThreadTrain02 threadTrain = new ThreadTrain02();

        // 1.创建两个线程
        new Thread(threadTrain).start();
        new Thread(threadTrain).start();
    }
}


class ThreadTrain02 implements Runnable {

    // 火车票总数
    private int count = 100;

    @Override
    public void run() {
        while (count > 0) {
            show();
        }

        // 使用同步方法，等价于使用this锁
/*        synchronized (this) {
            if (count > 0) {
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ", 出售第" + (100 - count + 1) + "张票");
                count--;
            }
        }*/
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
