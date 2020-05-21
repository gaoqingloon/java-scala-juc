package com.itedu.lesson_03.Lock_02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 第一个线程写入(input)用户，另一个线程取读取(out)用户.实现读一个，写一个操作。
 */
public class ThreadDemo02 {

    public static void main(String[] args) {

        Res res = new Res();
        InputThread inputThread = new InputThread(res);
        Thread outThread = new Thread(new OutThread(res));
        inputThread.start();
        outThread.start();

    }
}


/**
 * 共享资源
 */
class Res {
    public String name;
    public String sex;

    // 添加一个flag标记，false写 true读
    public boolean flag = false;

    // 在资源类中定义一把锁
    public Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
}


/**
 * 写入线程
 */
class InputThread extends Thread {

    private final Res res;

    InputThread(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {

            // 把要执行的代码放入到try...catch.. 中，防止死锁
            try {
                // 获取锁的资源
                res.lock.lock();

                if (res.flag) {
                    try {
                        res.condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count == 0) {
                    res.name = "小明";
                    res.sex = "男";
                } else {
                    res.name = "小红";
                    res.sex = "女";
                }
                // 实现奇数和偶数
                count = (count + 1) % 2;

                res.flag = true;
                res.condition.signal();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                // 释放锁的资源
                res.lock.unlock();
            }
        }
    }
}


/**
 * 读取线程
 */
class OutThread implements Runnable {

    private final Res res;

    OutThread(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            try {
                res.lock.lock();
                if (!res.flag) {
                    res.condition.await();
                }
                System.out.println(res.name + "---" + res.sex);
                res.flag = false;
                res.condition.signal();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                res.lock.unlock();
            }
        }
    }
}
