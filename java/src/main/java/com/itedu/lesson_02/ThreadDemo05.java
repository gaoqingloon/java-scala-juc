package com.itedu.lesson_02;

/**
 * 死锁模拟
 *
 *
 * 线程不安全问题如何解决？
 *  1.使用代码块synchronized jdk1.5并发包lock
 *      使用synchronized 代码块，每次只允许一个线程执行
 *  2.同步函数 使用this这把锁
 *  3.静态同步函数 使用 当前线程类.class这把锁
 */
public class ThreadDemo05 {

    public static void main(String[] args) throws InterruptedException {

        // 线程类定义一个实例
        ThreadTrain05 threadTrain = new ThreadTrain05();

        // 1.创建两个线程
        new Thread(threadTrain).start();

        Thread.sleep(40);
        threadTrain.flag = false;

        new Thread(threadTrain).start();
    }
}


class ThreadTrain05 implements Runnable {

    // 火车票总数
    private static int count = 100;
    private final Object obj = new Object();
    public boolean flag = true;

    @Override
    public void run() {

        // 线程1 flag为 true
        // 线程2 flag为 false
        if (flag) {
            while (count > 0) {
                // 锁 在代码执行完毕才会自动释放，让其他线程拿到锁
                // 如果flag 为 true 先拿到obj这把锁，再拿到this锁，才能执行代码
                // 如果flag 为 false 先拿到this这把锁，再拿到obj锁，才能执行代码
                synchronized (obj) {
                    show();
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
     * 同步方法中 有 同步
     * 先this锁，然后obj锁
     */
    public synchronized void show() {
        synchronized (obj) {
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
