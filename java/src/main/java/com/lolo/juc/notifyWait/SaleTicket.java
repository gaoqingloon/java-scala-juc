package com.lolo.juc.notifyWait;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 资源类(高内聚)
 */
class Ticket {

    private int number = 30;  // 模拟初始化30张票

    /**
     * public synchronized void sale() 保证同一时间只有一个人可以操作
     * Lock implementations provide more extensive locking operations
     * than can be obtained using synchronized methods and statements.
     */
    private Lock lock = new ReentrantLock();  // List<Int> list = new ArrayList<>();

    // 比如说空调的制冷/制热功能（高内聚）
    // 对外暴露出接口
    public void sale() {

        lock.lock();  // 使用时加锁
        try {
            if (number > 0)
                System.out.println(Thread.currentThread().getName() + "\t卖出第:" + (number--) + "\t还剩下:" + number);
        } finally {
            lock.unlock();  // 使用完释放锁
        }
    }
}


/**
 * 卖票程序复习线程知识，3个售货员 卖 30张票
 */
public class SaleTicket {

    /**
     * 编程思路:
     * 1. 线程 操作 资源类
     * 2. 高内聚(空调资源制冷和制热) 低耦合(每个人使用空调资源)
     */
    public static void main(String[] args) {

//        Thread t1 = new Thread(); t1.start();
//        Thread t2 = new Thread(); t2.start();
//        Thread t3 = new Thread(); t3.start();

        Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "C").start();
    }
}

/*
A	卖出第:30	还剩下:29
A	卖出第:29	还剩下:28
A	卖出第:28	还剩下:27
A	卖出第:27	还剩下:26
A	卖出第:26	还剩下:25
A	卖出第:25	还剩下:24
A	卖出第:24	还剩下:23
A	卖出第:23	还剩下:22
A	卖出第:22	还剩下:21
A	卖出第:21	还剩下:20
A	卖出第:20	还剩下:19
A	卖出第:19	还剩下:18
A	卖出第:18	还剩下:17
A	卖出第:17	还剩下:16
A	卖出第:16	还剩下:15
A	卖出第:15	还剩下:14
A	卖出第:14	还剩下:13
A	卖出第:13	还剩下:12
A	卖出第:12	还剩下:11
A	卖出第:11	还剩下:10
A	卖出第:10	还剩下:9
A	卖出第:9	还剩下:8
A	卖出第:8	还剩下:7
A	卖出第:7	还剩下:6
A	卖出第:6	还剩下:5
A	卖出第:5	还剩下:4
A	卖出第:4	还剩下:3
A	卖出第:3	还剩下:2
A	卖出第:2	还剩下:1
A	卖出第:1	还剩下:0
 */