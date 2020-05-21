package com.lolo.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShareResource {

    // 1. 属性
    private int number = 1;  // 模拟，1:A 2:B 3:C

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    // 2. 方法
    // A
    public void print5(int totalLoop) {

        lock.lock();

        try {
            // 1. 判断
            while (number != 1)
                condition1.await();

            // 2. 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\ttotalLoop: " + totalLoop);
            }

            // 3. 通知
            number = 2;  // 修改标志位
            condition2.signal();  // 1干完了，唤醒condition2，精确“打击”
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    // B
    public void print10(int totalLoop) {

        lock.lock();

        try {
            // 1. 判断
            while (number != 2)
                condition2.await();

            // 2. 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\ttotalLoop: " + totalLoop);
            }

            // 3. 通知
            number = 3;
            condition3.signal();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    // C
    public void print15(int totalLoop) {

        lock.lock();

        try {
            // 1. 判断
            while (number != 3)
                condition3.await();

            // 2. 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\ttotalLoop: " + totalLoop);
            }

            // 3. 通知
            number = 1;
            condition1.signal();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
