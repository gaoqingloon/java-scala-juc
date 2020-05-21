package com.itedu.lesson_03;

/**
 * 停止线程
 *      1. 使用flag标识
 */
public class StopThreadDemo {

    public static void main(String[] args) {

        StopThread stopThread1 = new StopThread();
        StopThread stopThread2 = new StopThread();
        stopThread1.start();
        stopThread2.start();

        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main..." + i);

            if (i == 29) {
                stopThread1.stopThread();
                stopThread2.stopThread();
            }
        }
    }
}


/**
 * 一般在使用多线程，大多数都会使用 for while
 */
class StopThread extends Thread {

    // flag为true 线程一直运行状态 false 停止线程
    public boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + "---我是子线程");
        }
    }

    public void stopThread() {
        System.out.println(getName() + "---线程被停止了");
        flag = false;
    }
}
