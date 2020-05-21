package com.lolo.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 共享资源类
 *      属性:
 *      方法:
 *          1. 判断
 *          2. 干活
 *          3. 通知
 */
public class ConditionShareData {

    // 1. 属性
    private int number = 0;

    private Lock lock = new ReentrantLock();  // lock
    private Condition condition = lock.newCondition();  // condition(await, signalAll)

    // 2. 方法
    // +1 等于0才加
    public void increment() throws InterruptedException {

        lock.lock();

        try {
            // 1. 判断（while）
            while (number != 0)
                condition.await();

            // 2. 干活
            ++number;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            // 3. 通知
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    // -1 不等于0时才减1
    public void decrement() throws InterruptedException {

        lock.lock();

        try {
            // 1. 判断（while）
            while (number == 0)
                condition.await();

            // 2. 干活
            --number;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            // 3. 通知
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }
}
