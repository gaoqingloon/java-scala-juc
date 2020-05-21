package com.itedu.lesson_03;

import jdk.internal.util.xml.impl.Input;

/**
 * 第一个线程写入(input)用户，另一个线程取读取(out)用户.实现读一个，写一个操作。
 */
public class ThreadDemo01 {

    public static void main(String[] args) {
        Res res = new Res();
        InputThread inputThread = new InputThread(res);
        Thread outThread = new Thread(new OutThread(res));
        inputThread.start();
        outThread.start();
        /*
        ...
        小明---女
        小明---男
        小明---男
        小红---女
        小红---男
        小红---男
        ...
         */
    }
}


/**
 * 共享资源
 */
class Res {
    public String name;
    public String sex;
    // 添加一个flag标记，false写 true读
    // flag 为false
    public boolean flag = false;
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
            // 加锁
            synchronized (res) {
                if (res.flag) {
                    // 当前线程等待；wait() 类似于sleep 可以让当前线程，从运行状态变为休眠状态
                    // wait 使用在多线程之间同步 和 synchronized 一起使用
                    // wait可以释放锁，sleep不能释放锁
                    try {
                        res.wait();
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

                res.flag = true;  // 写完之后标记为true
                res.notify();  // 和wait一起使用，写完之后唤醒（阻塞状态到运行状态）另一个线程
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
            synchronized (res) {
                if (!res.flag) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 一个线程写入之后可能另一个线程一直打印，两个线程不影响
                System.out.println(res.name + "---" + res.sex);

                res.flag = false;
                res.notify();
            }
        }
    }
}
