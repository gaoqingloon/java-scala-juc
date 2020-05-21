package com.itedu.lesson_03;

/**
 * 线程分为两种，一种是守护线程（后台线程），一种是用户线程（前台线程）
 * 主线程或jvm进程挂了，守护进程也会被停止。gc其实也是守护线程
 * 2019-6-30 16:37:42
 */
public class ThreadDemo03 {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("我是子线程, I: " + i);
                }
            }
        });

        // 设置子线程为守护线程，此时主线程停止，子线程也停止
        thread.setDaemon(true);
        // 子线程
        thread.start();

        // 主线程
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是主线程, I: " + i);
        }
        System.out.println("主线程执行完毕");
    }
}
