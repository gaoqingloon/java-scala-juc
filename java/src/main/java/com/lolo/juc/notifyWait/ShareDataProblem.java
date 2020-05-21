package com.lolo.juc.notifyWait;

/**
 * 资源类（高内聚）
 */
class ShareDataProblem {

    private int number = 0;

    // +1
    public synchronized void increment() throws InterruptedException {

        // 1. 判断(应该使用while)
        if (number != 0)
            this.wait();

        // 2. 干活
        ++number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        // 3. 通知
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {

        // 1. 判断
        if (number == 0)
            this.wait();

        // 2. 干活
        --number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        // 3. 通知
        this.notifyAll();
    }
}

