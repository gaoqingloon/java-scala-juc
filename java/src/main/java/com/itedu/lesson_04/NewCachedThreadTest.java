package com.itedu.lesson_04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 推荐使用
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，
 * 可灵活回收空闲线程，若无可回收，则新建线程
 */
public class NewCachedThreadTest {

    public static void main(String[] args) {

        // 创建可缓存线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 执行execute 表示创建了线程，类似start
        for (int i = 0; i < 10; i++) {

            final int index = i;
            //index++;

            cachedThreadPool.execute(new Runnable() {
                /**
                 * 内部类使用外部的变量，必须是final修饰的
                 */
                @Override
                public void run() {
//                    try {
//                        Thread.sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(Thread.currentThread().getName() + "---" + index);
                }
            });

            if (index == 9) {
                // 关闭线程池
                cachedThreadPool.shutdown();
            }
        }

        /*
        pool-1-thread-1---0
        pool-1-thread-2---1
        pool-1-thread-3---2
        pool-1-thread-4---3
        pool-1-thread-3---4
        pool-1-thread-4---5
        pool-1-thread-1---6
        pool-1-thread-2---7
        pool-1-thread-1---9
        pool-1-thread-5---8
         */
    }
}
