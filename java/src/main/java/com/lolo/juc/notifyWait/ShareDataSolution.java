package com.lolo.juc.notifyWait;

/**
 * 共享资源类
 *      1.属性:
 *      2.方法:
 *          1) 判断（while）
 *          2) 干活
 *          3) 通知
 */
public class ShareDataSolution {

    // 1. 属性
    private int number = 0;

    // 2. 方法（判断、干活、通知）
    // +1
    public synchronized void increment() throws InterruptedException {

        // 1) 判断(while)
        while (number != 0)  // 等于0才加，不等于0等待
            this.wait();

        // 2) 干活
        ++number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        // 3) 通知
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {

        // 1) 判断(while)
        while (number == 0)
            this.wait();

        // 2) 干活
        --number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        // 3) 通知
        this.notifyAll();
    }
}
