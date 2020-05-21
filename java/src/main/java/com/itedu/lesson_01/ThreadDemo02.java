package com.itedu.lesson_01;

/**
 * 创建多线程第一种继承Thread 类 重写run方法
 * 2019-6-29 17:11:59
 */
public class ThreadDemo02 {

    public static void main(String[] args) {

        System.out.println("创建线程开始");
        // 1. 定义一个类，继承Thread类 重写run方法
        // 2. 如何启动线程
        CreateThread createThread = new CreateThread();
        createThread.start();
        // 启动一个线程 是调用start方法 不是run方法，调用run方法相当于主线程执行
        // 注意：使用多线程情况，代码不会从上往下进行执行，会同时并行执行
        //createThread.run();
        System.out.println("线程已经开始启动 main");
        for (int i = 0; i < 100; i++) {
            System.out.println("main() i: " + i);
        }
    }
}


class CreateThread extends Thread {

    /**
     * run方法执行 需要线程执行的任务，代码
     */
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("run() i: " + i);
        }
    }
}

/*
 * 好问题：
 *      CPU只是快速切换线程，并不是真正意义的同时执行，
 *      电脑本身的单位时间执行的效率没有提高，那么是怎样提高效率的？
 *
 * 是的，但是我们使用多线程的目的是为了提高程序“效率”，执行程序的总时间变短，（多个小弟一起干活）
 *      一个程序中可以有多个不同的执行路径在并行的执行
 * “三个木桶重叠，三个木桶分开放”
 *
 * 真正意义的并行：多个核心
 */
