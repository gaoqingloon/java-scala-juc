package com.lolo.juc.countDownLatch;

import com.lolo.juc.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * 多线程调度和同步:
 *
 * 让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
 *
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 *
 * 解释：6个同学陆续离开教室后值班同学才可以关门。
 * 也即	秦灭6国，一统华夏
 * main主线程必须要等前面6个线程完成全部工作后，自己才能开干
 */
public class CountDownLatchDemo {

    public static void closeDoor() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t同学离开教室");
                countDownLatch.countDown(); // 执行6次
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();  // 主线程等待
        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }


    public static void main(String[] args) throws InterruptedException {

        closeDoor();

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            CountryEnum countryEnum = CountryEnum.forEachCountryEnums(i);
            assert countryEnum != null;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t国被灭");
                countDownLatch.countDown();
            }, countryEnum.getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t秦国一统华夏");

        // 枚举
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
    }
}
