package com.itedu.lesson_02;

/**
 * 模拟线程不安去问题
 *
 * 什么是线程不安全？
 *  当多个线程同时操作同一个共享的全局变量，可能会受到其他线程的干扰，会发生数据冲突问题
 *
 * 线程不安全问题如何解决？
 *  1.使用代码块synchronized jdk1.5并发包lock
 *      使用synchronized 代码块，每次只允许一个线程执行
 */
public class ThreadDemo {

    public static void main(String[] args) {

        // 线程类定义一个实例
        ThreadTrain threadTrain = new ThreadTrain();

        // 1.创建两个线程
        new Thread(threadTrain).start();
        new Thread(threadTrain).start();
    }
}


class ThreadTrain implements Runnable {

    // 火车票总数
    private int count = 100;
    private final Object obj = new Object();

    @Override
    public void run() {
        while (count > 0) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (obj) {  // obj 是一把锁
                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + ", 出售第" + (100 - count + 1) + "张票");
                    count--;
                }
            }
        }

/*        // 这么做就变成了单线程
        synchronized (obj) {
            while (count > 0) {
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + ", 出售第" + (100 - count + 1) + "张票");
                    count--;
                }
            }
        }*/

    }
}
