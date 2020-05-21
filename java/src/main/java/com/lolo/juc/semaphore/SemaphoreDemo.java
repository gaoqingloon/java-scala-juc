package com.lolo.juc.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * A counting semaphore.
 * 一个计数信号量。 在概念上，信号量维持一组许可证。
 * 信号量通常用于限制线程数，而不是访问某些（物理或逻辑）资源。
 *
 * 在信号量上我们定义两种操作：
 * acquire（获取） 当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），
 * 					要么一直等下去，直到有线程释放信号量，或超时。
 * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 *
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 *
 * 停车位（类似线程池）
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        // Semaphore(int permits, boolean fair)
        // 创建一个 Semaphore与给定数量的许可证和给定的公平设置。

        Semaphore semaphore = new Semaphore(3);  // 模拟3个停车位（信号量）

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();  // 某个线程抢到停车位，信号量-1
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "\t离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();  // 某个线程离开停车位，信号量+1
                }
            }, String.valueOf(i)).start();
        }
    }
}
/*
1	抢到车位
3	抢到车位
5	抢到车位
1	离开车位
5	离开车位
3	离开车位
4	抢到车位
2	抢到车位
6	抢到车位
4	离开车位
2	离开车位
6	离开车位

---

1	抢到车位
1	离开车位
2	抢到车位
6	抢到车位
5	抢到车位
5	离开车位
3	抢到车位
6	离开车位
4	抢到车位
2	离开车位
3	离开车位
4	离开车位
 */
