package com.lolo.javaUtral.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable {
    @Override
    public void run() {

    }
}

/**
 * 使用场景：线程带有返回值的问题（并发+异步）
 * 千次接口为一个批次调用，要求每个接口都有一个返回值，成功返回0000，失败返回4444
 */
class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "********come in Callable");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Thread(Runnable target, string name)  Allocates a new Thread object

        // FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        //FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread2());

        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start();  // 多次调用只用一次，除非上面new多个

        //int result02 = futureTask.get();
        System.out.println(Thread.currentThread().getName() + "**************");
        int result01 = 100;

//        while (!futureTask.isDone()) {
//
//        }

        int result02 = futureTask.get();  // 建议放在最后，要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞，直到计算完成

        System.out.println("******result: " + (result01 + result02));
    }
}
