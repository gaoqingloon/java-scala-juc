package com.itedu.lesson_01;

/**
 * java 单继承 多实现
 */
public class ThreadDemo03 {

    public static void main(String[] args) {

        // 定义一个类，实现Runnable接口，重写run方法
        System.out.println("创建线程开始 main");
        CreateRunnable createRunnable = new CreateRunnable();
        Thread thread = new Thread(createRunnable);
        thread.start();
        System.out.println("创建线程结束 main");
        for (int i = 0; i < 100; i++) {
            System.out.println("main() i: " + i);
        }
    }
}


class CreateRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("run() i: " + i);
        }
    }
}
