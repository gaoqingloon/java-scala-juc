package com.itedu.lesson_03;

/**
 * 停止线程
 *      1. 使用flag标识
 *      2. 使用interrupt方法中断线程。 线程在阻塞状态时使用
 */
public class StopThreadDemo02 {

    public static void main(String[] args) {

        StopThread02 stopThread1 = new StopThread02();
        StopThread02 stopThread2 = new StopThread02();
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

                //stopThread1.stopThread();
                //stopThread2.stopThread();

                stopThread1.interrupt();  // 中断线程，在wait处出现异常，此时再使用标识停止
                stopThread2.interrupt();
            }
        }
    }
}


/**
 * 一般在使用多线程，大多数都会使用 for while
 */
class StopThread02 extends Thread {

    // flag为true 线程一直运行状态 false 停止线程
    public boolean flag = true;

    @Override
    public synchronized void run() {
        while (flag) {
            try {
                wait();  // 线程处于阻塞状态
            } catch (InterruptedException e) {
                //e.printStackTrace();
                stopThread();  // 停止线程
            }
            System.out.println(Thread.currentThread().getName() + "---我是子线程");
        }
    }

    public void stopThread() {
        System.out.println(getName() + "---线程被停止了");
        flag = false;
    }
}
