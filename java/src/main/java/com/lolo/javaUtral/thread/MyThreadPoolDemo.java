package com.lolo.javaUtral.thread;

import java.util.concurrent.*;

/**
 * Array         Arrays
 * Collection    Collections
 * Executor      Executors
 *
 * 第4种获得/使用java多线程的方式，线程池
 * extends Thread;
 * implements Runnable（无返回值不会抛异常）;
 * implements Callable（有返回值会抛异常）
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()  // 9>5+3 java.util.concurrent.RejectedExecutionException
                //new ThreadPoolExecutor.CallerRunsPolicy()  // 多的任务可能回退到main线程
                //new ThreadPoolExecutor.DiscardOldestPolicy()  // 丢弃等待最久的任务
                new ThreadPoolExecutor.DiscardPolicy()  // 丢弃任务
        );

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 0; i < 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);  // 1池5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();  // 1池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();  // 1池N个处理线程

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void getCore() {
        System.out.println(Runtime.getRuntime().availableProcessors());  // 当前系统的核数
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024);
        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024);
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024);
    }
}
