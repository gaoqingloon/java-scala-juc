package com.itedu.lesson_02;

/**
 * 静态同步函数 使用的是 当前线程类.class
 *
 * 什么是线程不安全？
 *  当多个线程同时操作同一个共享的全局变量，可能会受到其他线程的干扰，会发生数据冲突问题
 *
 * 线程不安全问题如何解决？
 *  1.使用代码块synchronized jdk1.5并发包lock
 *      使用synchronized 代码块，每次只允许一个线程执行
 *  2.同步函数 使用this这把锁
 *  3.静态同步函数 使用 当前线程类.class这把锁
 */
public class ThreadDemo04 {

    public static void main(String[] args) throws InterruptedException {

        // 线程类定义一个实例
        ThreadTrain04 threadTrain = new ThreadTrain04();

        // 1.创建两个线程
        new Thread(threadTrain).start();

        Thread.sleep(40);
        threadTrain.flag = false;

        new Thread(threadTrain).start();
    }
}


class ThreadTrain04 implements Runnable {

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
                // 静态方法 不使用this锁，会出现101张票
                synchronized (ThreadTrain04.class) {
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
     * static  如果修饰的方法，类名.方法
     * 当class文件也就是字节码文件被加载时，才会被初始化。 static 方法区 永久区
     */
    public static synchronized void show() {
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
